import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class FollowPage extends JFrame {

    private JLabel followStatusLabel;
    private JButton followButton;
    private JButton unfollowButton;
    private Connection connection;
    private int loggedInUserId;
    private int searchedUserId;
    private JList<String> followerList;
    private JList<String> followingList;


    public FollowPage(int loggedInUserId, int searchedUserId, Connection connection) {
        this.loggedInUserId = loggedInUserId;
        this.searchedUserId = searchedUserId;

        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/instaDB2";
            String userName = "root";
            String dbPassword = "12345";

            try {
                this.connection = DriverManager.getConnection(url, userName, dbPassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            this.connection = connection;
        }

        setTitle("MySQLgram");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        followStatusLabel = new JLabel("팔로우 상태: ");
        panel.add(followStatusLabel, BorderLayout.NORTH);

        followButton = new JButton("Follow");
        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFollowStatus(loggedInUserId, searchedUserId, 1);
            }

        });

        unfollowButton = new JButton("Unfollow");
        unfollowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFollowStatus(loggedInUserId, searchedUserId, 0);
            }

        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(followButton);
        buttonPanel.add(unfollowButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        followerList = new JList<>();
        followingList = new JList<>();
        updateFollowerFollowingLists();

        JPanel listPanel = new JPanel(new GridLayout(1, 2));
        listPanel.add(new JScrollPane(followerList));
        listPanel.add(new JScrollPane(followingList));
        panel.add(listPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    // connection을 닫지 않도록 주석 처리
                    // if (connection != null && !connection.isClosed()) {
                    //     connection.close();
                    // }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private int getFollowStatus(int loggedInUserId, int searchedUserId) {
        int followStatus = 0; // 기본적으로 언팔로우 상태로 초기화

        try {
            String query = "SELECT follow_status FROM Follow WHERE who_id=? AND whom_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, loggedInUserId);
                preparedStatement.setInt(2, searchedUserId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        followStatus = resultSet.getInt("follow_status");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return followStatus;
    }
    private void updateFollowerFollowingLists() {
        String followerQuery = "SELECT who_id FROM Follow WHERE whom_id=?";
        String followingQuery = "SELECT whom_id FROM Follow WHERE who_id=?";

        int followStatus = getFollowStatus(loggedInUserId, searchedUserId);
        updateFollowStatusLabel(followStatus);


        DefaultListModel<String> followerListModel = new DefaultListModel<>();
        DefaultListModel<String> followingListModel = new DefaultListModel<>();

        try {
            try (PreparedStatement followerStatement = connection.prepareStatement(followerQuery)) {
                followerStatement.setInt(1, searchedUserId);
                try (ResultSet followerResult = followerStatement.executeQuery()) {
                    while (followerResult.next()) {
                        int followerId = followerResult.getInt("who_id");
                        String followerName = getUserNameById(followerId);
                        followerListModel.addElement(followerName);
                    }
                }
            }

            try (PreparedStatement followingStatement = connection.prepareStatement(followingQuery)) {
                followingStatement.setInt(1, searchedUserId);
                try (ResultSet followingResult = followingStatement.executeQuery()) {
                    while (followingResult.next()) {
                        int followingId = followingResult.getInt("whom_id");
                        String followingName = getUserNameById(followingId);
                        followingListModel.addElement(followingName);
                    }
                }
            }

            followerList.setModel(followerListModel);
            followingList.setModel(followingListModel);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getUserNameById(int userId) {
        String userName = "";

        try {
            String query = "SELECT name FROM User WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userName = resultSet.getString("name");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userName;
    }

    private void updateFollowStatus(int loggedInUserId, int searchedUserId, int followStatus) {
        try {
            String query;
            if (followStatus == 1) {
                query = "INSERT INTO Follow (who_id, whom_id, follow_status) VALUES (?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE follow_status=?";
            } else {
                query = "DELETE FROM Follow WHERE who_id=? AND whom_id=?";
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                if (followStatus == 1) {
                    preparedStatement.setInt(1, loggedInUserId);
                    preparedStatement.setInt(2, searchedUserId);
                    preparedStatement.setInt(3, followStatus);
                    preparedStatement.setInt(4, followStatus);
                } else {
                    preparedStatement.setInt(1, loggedInUserId);
                    preparedStatement.setInt(2, searchedUserId);
                }

                preparedStatement.executeUpdate();
            }

            updateFollowerFollowingLists();
            updateFollowStatusLabel(followStatus);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateFollowStatusLabel(int followStatus) {
        SwingUtilities.invokeLater(() -> {
            if (followStatus == 1) {
                followStatusLabel.setText("팔로우 상태: 팔로우 중");
            } else {
                followStatusLabel.setText("팔로우 상태: 언팔로우");
            }
        });

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/instaDB2", "root", "12345");
                new FollowPage(1, 2, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
