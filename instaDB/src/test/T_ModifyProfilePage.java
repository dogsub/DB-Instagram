/*
package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;


public class T_ModifyProfilePage extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextArea introduceArea;
    private JTextField birthField;
    private JPasswordField passwordField;
    private JTextField sexField;
    private JTextField telcomField;
    private JButton selectImageButton; // New button to select an image
    private JLabel imageLabel; // New label to display selected image
    private byte[] profileImageBytes; // Store profile image as bytes
    private String username;
    private Connection connection;
    private String originalTelcom;

    public T_ModifyProfilePage(String username) {
        this.username = username;
        setTitle("프로필 수정");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("이름:");
        panel.add(nameLabel);

        nameField = new JTextField();
        panel.add(nameField);

        JLabel emailLabel = new JLabel("이메일:");
        panel.add(emailLabel);

        emailField = new JTextField();
        panel.add(emailField);

        JLabel introduceLabel = new JLabel("소개:");
        panel.add(introduceLabel);

        introduceArea = new JTextArea();
        panel.add(new JScrollPane(introduceArea));

        JLabel birthLabel = new JLabel("생년월일:");
        panel.add(birthLabel);

        birthField = new JTextField();
        panel.add(birthField);

        JLabel passwordLabel = new JLabel("비밀번호:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        JLabel sexLabel = new JLabel("성별(여자는 0, 남자는 1):");
        panel.add(sexLabel);

        sexField = new JTextField();
        panel.add(sexField);

        JLabel telcomLabel = new JLabel("전화번호:");
        panel.add(telcomLabel);

        telcomField = new JTextField();
        panel.add(telcomField);

        selectImageButton = new JButton("이미지 선택");
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        // Read the selected image as bytes
                        profileImageBytes = Files.readAllBytes(fileChooser.getSelectedFile().toPath());

                        // Display the selected image
                        ImageIcon imageIcon = new ImageIcon(profileImageBytes);
                        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(image);
                        imageLabel.setIcon(imageIcon);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        panel.add(selectImageButton);

        imageLabel = new JLabel();
        panel.add(imageLabel);

        JButton loadButton = new JButton("정보 불러오기");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUserInfo();
            }
        });
        panel.add(loadButton);

        JButton updateButton = new JButton("프로필 수정");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserProfile();
                T_ProfilePage profilePage = new T_ProfilePage(username);
                profilePage.setVisible(true);
                dispose();
            }
        });
        panel.add(updateButton);

        JButton cancelButton = new JButton("취소");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                T_ProfilePage profilePage = new T_ProfilePage(username);
                profilePage.setVisible(true);
                dispose();
            }
        });
        panel.add(cancelButton);

        add(panel);
        setVisible(true);

        String url = "jdbc:mysql://localhost:3306/testinsta";
        String userName = "root";
        String dbPassword = "12345";

        try {
            connection = DriverManager.getConnection(url, userName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadUserInfo() {
        try {
            String query = "SELECT * FROM user WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nameField.setText(resultSet.getString("name"));
                emailField.setText(resultSet.getString("email"));
                introduceArea.setText(resultSet.getString("introduce"));
                birthField.setText(resultSet.getString("birth"));
                sexField.setText(resultSet.getString("sex"));
                telcomField.setText(resultSet.getString("telcom"));
                originalTelcom = resultSet.getString("telcom");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateUserProfile() {
        try {
            String query = "UPDATE user SET name=?, email=?, introduce=?, birth=?, profile_img=?, password=?, sex=?, telcom=? WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setString(2, emailField.getText());
            preparedStatement.setString(3, introduceArea.getText());
            preparedStatement.setString(4, birthField.getText());

            // Update the profile image if profileImageBytes is not null
            if (profileImageBytes != null) {
                preparedStatement.setBinaryStream(5, new ByteArrayInputStream(profileImageBytes), profileImageBytes.length);
            } else {
                preparedStatement.setNull(5, Types.BLOB);
            }

            preparedStatement.setString(6, new String(passwordField.getPassword()));
            preparedStatement.setString(7, sexField.getText());
            preparedStatement.setString(8, telcomField.getText());
            preparedStatement.setString(9, username);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "프로필 수정 완료!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "프로필 수정 실패. 다시 시도해주세요.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new T_ModifyProfilePage("1111");
        });
    }
}
*/
