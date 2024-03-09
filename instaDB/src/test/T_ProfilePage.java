/*
package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class T_ProfilePage extends JFrame {
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JTextArea introduceArea;
    private JLabel profileImageLabel;

    public T_ProfilePage(String username) {

        setTitle("프로필 페이지");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        usernameLabel = new JLabel();
        panel.add(usernameLabel);

        emailLabel = new JLabel();
        panel.add(emailLabel);

        introduceArea = new JTextArea();
        introduceArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(introduceArea);
        panel.add(scrollPane);

        profileImageLabel = new JLabel();
        panel.add(profileImageLabel);

        JButton modifyProfileButton = new JButton("프로필 수정");
        modifyProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 프로필 페이지 닫기
                openModifyProfilePage(username); // ModifyProfilePage 실행
            }
        });
        panel.add(modifyProfileButton);

        JButton backButton = new JButton("피드로 돌아가기");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 프로필 페이지 닫기
                openMainFrame(username); // MainFrame 실행
            }
        });
        panel.add(backButton);

        // 피드 관리 버튼 추가
        JButton feedManageButton = new JButton("피드 관리");
        feedManageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 프로필 페이지 닫기
                openFeedPage(username); // FeedPage 실행
            }
        });
        panel.add(feedManageButton); // 패널에 피드 관리 버튼 추가

        add(panel);
        setVisible(true);

        // Database Connection
        String url = "jdbc:mysql://localhost:3306/testinsta";
        String userName = "root";
        String dbPassword = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, userName, dbPassword);
            // 사용자 정보를 가져와 UI에 표시
            loadUserInfo(connection, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadUserInfo(Connection connection, String username) {
        try {
            String query = "SELECT name, email, introduce, profile_img FROM User WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String introduce = resultSet.getString("introduce");
                byte[] profileImageBytes = resultSet.getBytes("profile_img");

                // UI 업데이트
                usernameLabel.setText("사용자 이름: " + name);
                emailLabel.setText("이메일: " + email);
                introduceArea.setText(introduce);
                displayProfileImage(profileImageBytes);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayProfileImage(byte[] profileImageBytes) {
        ImageIcon profileImageIcon = new ImageIcon(profileImageBytes);
        Image profileImage = profileImageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        profileImageIcon = new ImageIcon(profileImage);
        profileImageLabel.setIcon(profileImageIcon);
    }

    private void openModifyProfilePage(String username) {
        T_ModifyProfilePage modifyProfilePage = new T_ModifyProfilePage(username);
        modifyProfilePage.setVisible(true);
    }

    private void openMainFrame(String username) {
        T_MainFrame mainFrame = new T_MainFrame(username);
        mainFrame.setVisible(true);
    }
    private void openFeedPage(String username) {
        int userId = getUserIdFromDatabase(username); // 사용자 이름으로부터 ID를 가져옴
        if (userId != -1) {
            T_FeedPage feedPage = new T_FeedPage(userId); // FeedPage 생성하여 해당 사용자 ID를 전달
            feedPage.setVisible(true);
        } else {
            System.out.println("사용자 ID를 가져오는 데 문제가 발생했습니다.");
            // 사용자 ID를 가져오지 못한 경우 예외 처리
        }
    }

    private int getUserIdFromDatabase(String username) {
        int userId = -1; // 기본값 -1로 설정

        String url = "jdbc:mysql://localhost:3306/testinsta";
        String userName = "root";
        String dbPassword = "1234";

        try (Connection connection = DriverManager.getConnection(url, userName, dbPassword)) {
            String query = "SELECT id FROM User WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new T_ProfilePage("사용자 이름");
        });
    }
}*/
