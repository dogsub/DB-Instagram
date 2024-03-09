/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FollowPage extends JFrame {
    private JLabel followStatusLabel;
    private JButton followButton;
    private JButton unfollowButton;
    private Connection connection;

    public FollowPage(int loggedInUserId, int searchedUserId, Connection connection) {
        this.connection = connection;

        setTitle("Follow Page");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        followStatusLabel = new JLabel("팔로우 상태: ");
        panel.add(followStatusLabel, BorderLayout.NORTH);

        followButton = new JButton("Follow");
        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 팔로우 기능 구현
                updateFollowStatus(loggedInUserId, searchedUserId, 1);
            }
        });

        unfollowButton = new JButton("Unfollow");
        unfollowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 언팔로우 기능 구현
                updateFollowStatus(loggedInUserId, searchedUserId, 0);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(followButton);
        buttonPanel.add(unfollowButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void updateFollowStatus(int loggedInUserId, int searchedUserId, int followStatus) {
        try {
            String query = "REPLACE INTO Follow (who_id, whom_id, bool) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, loggedInUserId);
            preparedStatement.setInt(2, searchedUserId);
            preparedStatement.setInt(3, followStatus);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            // 업데이트 후 팔로우 상태 메시지 갱신
            updateFollowStatusLabel(followStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateFollowStatusLabel(int followStatus) {
        if (followStatus == 1) {
            followStatusLabel.setText("팔로우 상태: 팔로우 중");
        } else {
            followStatusLabel.setText("팔로우 상태: 언팔로우");
        }
    }

    public static void main(String[] args) {
        // FollowPage는 MainFrame에서 호출될 것이므로 main 메서드는 필요 없을 수도 있습니다.
        SwingUtilities.invokeLater(() -> {
            new FollowPage(1, 2, null); // loggedInUserId와 searchedUserId는 예시값입니다.
        });
    }
}
*/
