import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import function.*;



public class SignUpFrame extends JFrame {
    private CustomTextField nameField;
    private CustomTextField emailField;
    private CustomTextField introduceArea;
    private CustomTextField birthField;
    private CustomTextField sexField;
    private JPasswordField passwordField;
    private CustomTextField telcomField;
    private JLabel profileImgLabel;
    private RoundedButton chooseImageButton;
    private Connection connection;

    private byte[] userProfileImage;

    public SignUpFrame() {
        setTitle("MySQLgram");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(365, 586);
        setLocationRelativeTo(null);


        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.setBackgroundImage("src/png/배경.png");
        //imagePanel.setBackground(Color.white);
        setContentPane(imagePanel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // Make it transparent
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        ImageIcon instagramWatermarkIcon = new ImageIcon("src/png/Instagram_logo_wordmark_logotype.png");
        Image instagramWatermarkImage = instagramWatermarkIcon.getImage();
        Image resizedImage = instagramWatermarkImage.getScaledInstance(200, 65, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);


        imageLabel.setBorder(BorderFactory.createEmptyBorder(40,60,30,60));
        imageLabel.setBackground(Color.WHITE);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(imageLabel);

        JLabel borderLabel= new JLabel();
        borderLabel.setBorder(BorderFactory.createEmptyBorder(5,0,20,0));
        borderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel);

        // 사용자명 입력 창
        nameField = new CustomTextField(50, "Username");
        panel.add(nameField);
        JLabel borderLabel1= new JLabel();
        borderLabel1.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel1);
        emailField = new CustomTextField(50, "Email");
        panel.add(emailField);
        JLabel borderLabel2= new JLabel();
        borderLabel2.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel2);
        introduceArea = new CustomTextField(50, "Introduction");
        panel.add(introduceArea);
        JLabel borderLabel3= new JLabel();
        borderLabel3.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel3);
        birthField = new CustomTextField(50, "Birth");
        panel.add(birthField);
        JLabel borderLabel4= new JLabel();
        borderLabel4.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel4);

        JLabel borderLabel12 = new JLabel();
        borderLabel12.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel12.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel12);

        profileImgLabel = new JLabel();


        chooseImageButton = new RoundedButton("이미지 선택");
        chooseImageButton.setMaximumSize(new Dimension(290,40));
        chooseImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseImageButton.setBackground(Color.white);
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
                    setSize(365,626);

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
        panel.add(profileImgLabel);

        JLabel borderLabel5= new JLabel();
        borderLabel5.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel5.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel5);

        passwordField = new RoundJPasswordField(12);
        passwordField.setFont(new Font("SANS_SERIF",Font.BOLD,12));
        passwordField.setMaximumSize(new Dimension(290, 40));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        Border passWordBorder = BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(0, 10, 0, 0 )

        );
        passwordField.setBorder(passWordBorder);


        // Echo character를 공백 문자로 설정하여 내용을 표시하도록 함
        passwordField.setEchoChar((char) 0);

        // 포커스가 없을 때 비밀번호 힌트를 표시하도록 함
        passwordField.setText("PassWord");
        passwordField.setForeground(Color.GRAY);
        Color light_gray = new Color(250,250,250);
        passwordField.setBackground(light_gray);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("PassWord")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    // Echo character를 다시 설정하여 비밀번호를 감춤
                    passwordField.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    // 비밀번호가 비어있으면 다시 힌트를 표시하도록 함
                    passwordField.setText("PassWord");
                    passwordField.setForeground(Color.GRAY);
                    // Echo character를 공백 문자로 설정하여 내용을 표시하도록 함
                    passwordField.setEchoChar((char) 0);
                }
            }

        });

        panel.add(passwordField);
        JLabel borderLabel10= new JLabel();
        borderLabel10.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel10.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel10);

        String[] items = {"Male", "Female"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setMaximumSize(new Dimension(290,40));
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 선택된 항목 가져오기
                String selectedValue = (String) comboBox.getSelectedItem();
                }
        });
        sexField= new CustomTextField(50, "sex");
        String sex;
        if(comboBox.getSelectedItem()=="Male"){
            sex="0";
        }else sex="1";
        sexField.setText(sex);
        panel.add(comboBox);

        JLabel borderLabel11 = new JLabel();
        borderLabel11.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel11.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel11);

        telcomField = new CustomTextField(50, "Telecom");
        panel.add(telcomField);

        borderLabel4.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel4);


        RoundedButton signUpButton = new RoundedButton("Sign Up");
        signUpButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color bright_blue = new Color(0,100,224);
        signUpButton.setBackground(bright_blue);
        signUpButton.setMaximumSize(new Dimension(290, 40));
        signUpButton.setForeground(Color.WHITE);
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
                        new LoginFrame(); // LoginFrame 실행
                    } else {
                        JOptionPane.showMessageDialog(null, "회원가입 실패. 다시 시도해주세요.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(signUpButton);

        JLabel signUpLabel = new JLabel("go to Login");
        signUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpLabel.setForeground(Color.black);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // 회원가입 링크 클릭 시 처리
                // 여기에 회원가입 화면으로 이동하는 기능을 구현할 수 있습니다.
                new LoginFrame(); // SignUpFrame 실행
                dispose(); // Close the LoginFrame
            }
        });
        panel.add(signUpLabel);
        add(panel);
        setVisible(true);

        // 데이터베이스 연결
        String url = "jdbc:mysql://localhost:3306/instaDB2";
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
                new SignUpFrame();
            }
        });
    }
}
