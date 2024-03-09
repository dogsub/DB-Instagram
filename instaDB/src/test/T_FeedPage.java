/*
package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

public class T_FeedPage extends JFrame {
    private JTextArea feedTextArea;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;
    private Connection connection;
    private int loggedInUserId = 1;

    public T_FeedPage() {
        // 기본 생성자 구현
        initializeUI();
        initDatabaseConnection();
        displayUserFeed();
    }

    // 다른 생성자 오버로딩하여 사용자 ID를 받도록 설정
    public FeedPage(int userId) {
        loggedInUserId = userId;
        initializeUI();
        initDatabaseConnection();
        displayUserFeed();
    }


    private void initializeUI() {
        setTitle("Feed Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        feedTextArea = new JTextArea(20, 50);
        feedTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(feedTextArea);

        createButton = new JButton("Create Feed");
        updateButton = new JButton("Update Feed");
        deleteButton = new JButton("Delete Feed");
        backButton = new JButton("Back to Profile");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 현재 FeedPage 창을 닫음
                openProfilePage(loggedInUserId); // ProfilePage로 돌아가는 메서드 호출
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = JOptionPane.showInputDialog("Enter feed content:");
                createFeed(content);
                displayUserFeed();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFeedContent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFeed();
            }
        });


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initDatabaseConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/testinsta";
            String userName = "root";
            String dbPassword = "1234";

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void openProfilePage(int userId) {
        T_ProfilePage profilePage = new T_ProfilePage(getUsernameFromId(userId)); // ProfilePage 생성
        profilePage.setVisible(true);
    }
    private String getUsernameFromId(int userId) {
        String username = "";

        String url = "jdbc:mysql://localhost:3306/testinsta";
        String userName = "root";
        String dbPassword = "1234";

        try (Connection connection = DriverManager.getConnection(url, userName, dbPassword)) {
            String query = "SELECT name FROM User WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                username = resultSet.getString("name");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username;
    }

    //    private String getUsernameFromId(int userId) {
//        String username = "SELECT name FROM User WHERE id = userId;";
//        // 여기서 userId를 사용하여 데이터베이스에서 해당하는 사용자의 이름을 가져옴
//        // 예: SELECT name FROM User WHERE id = userId;
//        // 가져온 사용자 이름을 username에 할당
//        return username;
//    }
//    private void createFeed(String content) {
//        try {
//
//            String query = "INSERT INTO Feed (user_id, content, time) VALUES (?, ?, NOW())";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, loggedInUserId);
//            preparedStatement.setString(2, content);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
    private void createFeedWithImage(String content, File imageFile) {
        try {
            FileInputStream fis = new FileInputStream(imageFile);

            String query = "INSERT INTO Feed (user_id, content, feed_img, time) VALUES (?, ?, ?, NOW())";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, loggedInUserId);
            preparedStatement.setString(2, content);
            preparedStatement.setBinaryStream(3, fis, (int) imageFile.length());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFeed(String content) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File selectedImage = fileChooser.getSelectedFile();

        if (selectedImage != null) {
            createFeedWithImage(content, selectedImage);
            displayUserFeed();
        } else {
            // 사용자가 이미지를 선택하지 않은 경우에 대한 처리
            JOptionPane.showMessageDialog(null, "No image selected.");
        }
    }
    // 사용자가 피드를 업데이트하고자 할 때 실행되는 메서드
    private void updateFeedContent() {
        String feedIdInput = JOptionPane.showInputDialog("Enter Feed ID to update:");
        if (feedIdInput != null && !feedIdInput.isEmpty()) {
            try {
                int feedIdToUpdate = Integer.parseInt(feedIdInput);

                // 특정 피드 아이디의 content를 가져온 후 수정
                String newContent = JOptionPane.showInputDialog("Enter new content for Feed ID " + feedIdToUpdate + ":");
                if (newContent != null && !newContent.isEmpty()) {
                    updateFeed(feedIdToUpdate, newContent);
                    displayUserFeed(); // 업데이트 후 피드 다시 표시
                } else {
                    // 사용자가 취소 또는 빈 입력을 했을 때 처리할 내용을 여기에 추가하세요.
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Feed ID.");
            }
        }
    }

    // 특정 피드 ID에 해당하는 content를 업데이트하는 메서드
    private void updateFeed(int feedId, String newContent) {
        try {
            String query = "UPDATE Feed SET content = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newContent);
            preparedStatement.setInt(2, feedId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteFeed() {
        String feedIdInput = JOptionPane.showInputDialog("Enter Feed ID to delete:");
        if (feedIdInput != null && !feedIdInput.isEmpty()) {
            try {
                int feedIdToDelete = Integer.parseInt(feedIdInput);
                boolean deletionSuccessful = deleteFeedFromDatabase(feedIdToDelete);

                if (deletionSuccessful) {
                    JOptionPane.showMessageDialog(null, "Feed deleted successfully!");
                    displayUserFeed(); // 피드를 다시 표시하여 변경 사항을 반영
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete feed. Please check the Feed ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Feed ID.");
            }
        }
    }

    // 특정 피드 ID에 해당하는 피드를 데이터베이스에서 삭제하는 메서드
    private boolean deleteFeedFromDatabase(int feedId) {
        try {
            String query = "DELETE FROM Feed WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, feedId);
            int deletedRows = preparedStatement.executeUpdate();

            return deletedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private void displayUserFeed() {
        try {
            String query = "SELECT * FROM Feed WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, loggedInUserId);
            ResultSet resultSet = preparedStatement.executeQuery();

            JPanel feedPanel = new JPanel();
            feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));

            while (resultSet.next()) {
                int feedId = resultSet.getInt("id");
                String content = resultSet.getString("content");
                Timestamp timestamp = resultSet.getTimestamp("time");
                byte[] imageData = resultSet.getBytes("feed_img");

                JPanel singleFeedPanel = new JPanel();
                singleFeedPanel.setLayout(new BoxLayout(singleFeedPanel, BoxLayout.Y_AXIS));

                JLabel contentLabel = new JLabel("Feed ID: " + feedId + "\nContent: " + content + "\nTime: " + timestamp.toString() + "\n\n");
                singleFeedPanel.add(contentLabel);

                // 이미지 표시
                if (imageData != null) {
                    ImageIcon imageIcon = new ImageIcon(imageData);
                    JLabel imageLabel = new JLabel(imageIcon);
                    singleFeedPanel.add(imageLabel);
                }

                feedPanel.add(singleFeedPanel);
            }

            JScrollPane scrollPane = new JScrollPane(feedPanel);
            add(scrollPane, BorderLayout.CENTER);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new T_FeedPage();
        });
    }
}
*/
