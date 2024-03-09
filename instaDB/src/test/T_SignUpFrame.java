package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class T_SignUpFrame extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextArea introduceArea;
    private JTextField birthField;
    private JPasswordField passwordField;
    private JTextField sexField;
    private JTextField telcomField;
    private JLabel profileImgLabel;
    private JButton chooseImageButton;
    private Connection connection;

    private byte[] userProfileImage;

    public T_SignUpFrame() {
        setTitle("회원가입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2, 5, 5));
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

        profileImgLabel = new JLabel("프로필 이미지:");
        panel.add(profileImgLabel);

        chooseImageButton = new JButton("이미지 선택");
        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 파일 선택 다이얼로그를 띄워 이미지 파일 선택
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    // 선택한 이미지를 JLabel에 표시
                    displayImage(selectedFile);

                    try {
                        // 선택한 이미지를 바이트 배열로 읽어오기
                        userProfileImage = readFileToByteArray(selectedFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        panel.add(chooseImageButton);

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

        JButton signUpButton = new JButton("회원가입");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String introduce = introduceArea.getText();
                String birth = birthField.getText();
                String password = new String(passwordField.getPassword());
                String sex = sexField.getText();
                String telcom = telcomField.getText();

                try {
                    if (addUserToDatabase(name, email, introduce, birth, userProfileImage, password, sex, telcom)) {
                        JOptionPane.showMessageDialog(null, "회원가입 성공!");
                        dispose(); // SignUpFrame 닫기
                        //new LoginFrame(); // LoginFrame 실행
                    } else {
                        JOptionPane.showMessageDialog(null, "회원가입 실패. 다시 시도해주세요.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(signUpButton);

        JButton backButton = new JButton("돌아가기");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // SignUpFrame 닫기
                //new LoginFrame(); // LoginFrame 실행
            }
        });
        panel.add(backButton);

        add(panel);
        setVisible(true);

        // 데이터베이스 연결
        String url = "jdbc:mysql://localhost:3306/instadb";
        String userName = "root";
        String dbPassword = "12345";

        try {
            connection = DriverManager.getConnection(url, userName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayImage(File selectedFile) {
        ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profileImgLabel.setIcon(new ImageIcon(image));
    }

    private byte[] readFileToByteArray(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return data;
    }

    private boolean addUserToDatabase(String name, String email, String introduce, String birth, byte[] profileImage, String password, String sex, String telcom) throws SQLException {
        String query = "INSERT INTO User (name, email, introduce, birth, profile_img, password, sex, telcom) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, introduce);
        preparedStatement.setString(4, birth);
        preparedStatement.setBytes(5, profileImage);
        preparedStatement.setString(6, password);
        preparedStatement.setString(7, sex);
        preparedStatement.setString(8, telcom);

        int rowsAffected = preparedStatement.executeUpdate();

        preparedStatement.close();

        return rowsAffected > 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new T_SignUpFrame();
            }
        });
    }
}
