import function.RoundJPasswordField;
import function.CustomTextField;
import function.RoundedButton;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;




public class ModifyProfilePage extends JFrame {
    private CustomTextField nameField;
    private CustomTextField emailField;
    private CustomTextField introduceArea;
    private CustomTextField birthField;
    private CustomTextField sexField;
    private JPasswordField passwordField;
    private CustomTextField telcomField;
    private RoundedButton selectImageButton; // New button to select an image
    private JLabel imageLabel; // New label to display selected image
    private byte[] profileImageBytes; // Store profile image as bytes
    private JLabel profileImgLabel;
    private RoundedButton chooseImageButton;
    private Connection connection;

    private byte[] userProfileImage;
    private String username;
    private String originalTelcom;

    public ModifyProfilePage(String username) {
        this.username = username;



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

        JLabel imageLabel1 = new JLabel(resizedIcon);


        imageLabel1.setBorder(BorderFactory.createEmptyBorder(40,60,30,60));
        imageLabel1.setBackground(Color.WHITE);
        imageLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(imageLabel1);



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

        JLabel borderLabel7= new JLabel();
        borderLabel7.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel7.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel7);



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

        JLabel borderLabel13= new JLabel();
        borderLabel13.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel13.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel13);

        telcomField = new CustomTextField(50,"Telecom");

        panel.add(telcomField);

        JLabel borderLabel14= new JLabel();
        borderLabel14.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel14.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel14);

        selectImageButton = new RoundedButton("이미지 선택");
        selectImageButton.setBackground(Color.white);
        selectImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectImageButton.setMaximumSize(new Dimension(290, 40));

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
                setSize(365,626);
            }

        });
        panel.add(selectImageButton);

        imageLabel = new JLabel();
        panel.add(imageLabel);

        JLabel borderLabel6 = new JLabel();
        borderLabel6.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel6.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel6);


        RoundedButton loadButton = new RoundedButton("정보 불러오기");
        loadButton.setBackground(Color.white);
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.setMaximumSize(new Dimension(290, 40));

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUserInfo();
            }
        });
        panel.add(loadButton);

        JPanel panel1 = new JPanel();

        JLabel borderLabel15 = new JLabel();
        borderLabel15.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        borderLabel15.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel15);



        RoundedButton updateButton = new RoundedButton("profile modify");
        updateButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color bright_blue = new Color(0,100,224);
        updateButton.setBackground(bright_blue);
        updateButton.setMaximumSize(new Dimension(135, 40));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserProfile();
                ProfilePage profilePage = new ProfilePage(username);
                profilePage.setVisible(true);
                dispose();
            }
        });
        panel1.add(updateButton);



        JLabel borderLabel8= new JLabel();
        borderLabel8.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        borderLabel8.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(borderLabel8);

        RoundedButton cancelButton = new RoundedButton("Cancel");
        cancelButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setBackground(bright_blue);
        cancelButton.setMaximumSize(new Dimension(135, 40));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(new ActionListener() {
                // 회원가입 링크 클릭 시 처리
                // 여기에 회원가입 화면으로 이동하는 기능을 구현할 수 있습니다.
                @Override
                public void actionPerformed(ActionEvent e) {
                    ProfilePage profilePage = new ProfilePage(username);
                    profilePage.setVisible(true);
                    dispose();
                }


        });
        panel1.add(cancelButton);

        panel.add(panel1);
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

    private byte[] readFileToByteArray(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return data;
    }
    private void displayImage(File selectedFile) {
        ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profileImgLabel.setIcon(new ImageIcon(image));
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
                profileImageBytes = resultSet.getBytes("profile_img");

                String password = resultSet.getString("password");
                passwordField.setText(password);
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
            new ModifyProfilePage("username");
        });
    }
}