import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import function.RoundJTextField;
import function.RoundJPasswordField;
import function.RoundedButton;
//import org.apache.batik.swing.JSVGCanvas;
import java.net.URI;
class ImagePanel extends JPanel {
    private Image backgroundImage;

    public void setBackgroundImage(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Connection connection;


    public LoginFrame() {


        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("src/png/instagram_logo.png");
        setIconImage(img);


        setTitle("mysqlgram");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(365, 586);
        setLocationRelativeTo(null);


        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.setBackgroundImage("src/png/배경.png");
        setContentPane(imagePanel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // Make it transparent
        panel.setBackground(new Color(0, 0, 0, 0));

        panel.setFocusable(true);
        panel.requestFocusInWindow();

        addComponentsToPanel(panel);

        imagePanel.add(panel);


/*
        pack();
*/
        setVisible(true);

        // Database Connection
        String url = "jdbc:mysql://localhost:3306/instaDB2";
        String userName = "root";
        String dbPassword = "12345";

        try {
            connection = DriverManager.getConnection(url, userName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addComponentsToPanel(JPanel panel) {

       /* ImageIcon logoIcon = new ImageIcon("src/png/Instagram_logo_wordmark_logotype.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(logoLabel);*/

        //2번쨰 이미지 로딩
        ImageIcon instagramWatermarkIcon = new ImageIcon("src/png/instagram_logo_real.png");
        Image instagramWatermarkImage = instagramWatermarkIcon.getImage();
        Image resizedImage = instagramWatermarkImage.getScaledInstance(65, 65, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);

        int padding=30;
        imageLabel.setBorder(BorderFactory.createEmptyBorder(80,60,padding,60));
        imageLabel.setBackground(Color.WHITE);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(imageLabel);

        JLabel borderLabel= new JLabel();
        borderLabel.setBorder(BorderFactory.createEmptyBorder(5,0,20,0));
        borderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel);


        // svg이미지 넣기
        /*        JSVGCanvas svgCanvas = new JSVGCanvas();
        // SVG 이미지 로드 및 표시
        try {
            URI svgURI = new URI("instagram-wordmark.svg");
            svgCanvas.setURI(String.valueOf(svgURI));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // JFrame에 JSVGCanvas 추가
        getContentPane().add(svgCanvas, BorderLayout.CENTER);*/



        // 사용자명 입력 창

        usernameField = new RoundJTextField(12);
        usernameField.setFont(new Font("SANS_SERIF",Font.BOLD,12));
        usernameField.setMaximumSize(new Dimension(290, 40));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        Border userNameBorder = BorderFactory.createCompoundBorder(
                usernameField.getBorder(),
                BorderFactory.createEmptyBorder(0, 10, 0, 0 )
        );
        usernameField.setBorder(userNameBorder);
        //둘레 굵게??
        //Border border1 = BorderFactory.createLineBorder(Color.BLACK, 10);
        //usernameField.setBorder(border1);

        // 포커스가 없을 때 사용자명 힌트를 표시하도록 함
        usernameField.setText("Username");
        usernameField.setForeground(Color.GRAY);
        Color light_gray = new Color(250,250,250);
        usernameField.setBackground(light_gray);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    // 사용자명이 비어있으면 다시 힌트를 표시하도록 함
                    usernameField.setText("Username");
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });
        Border border = BorderFactory.createCompoundBorder(
                usernameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        usernameField.setBorder(border);
        panel.add(usernameField);



        //border 추가
        JLabel borderLabel2= new JLabel();
        borderLabel2.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        borderLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel2);

        // 비밀번호 입력 창

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
        border = BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        passwordField.setBorder(border);
        panel.add(passwordField);
        //border 추가
        JLabel borderLabel3= new JLabel();
        borderLabel3.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        borderLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel3);


        // 로그인 버튼

        RoundedButton loginButton = new RoundedButton("Log in");
        loginButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color bright_blue = new Color(0,100,224);
        loginButton.setBackground(bright_blue);
        loginButton.setMaximumSize(new Dimension(290, 40));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());

                try {
                    if (authenticateUser(enteredUsername, enteredPassword)) {
                        JOptionPane.showMessageDialog(null, "로그인 성공!");
                        openMainFrame(enteredUsername);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "로그인 실패. 사용자명 또는 비밀번호가 잘못되었습니다.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(loginButton);


/*        ImageIcon instagramLogoIcon = new ImageIcon("src/png/instagramLogo.png");
        Image instagramLogoImage = instagramLogoIcon.getImage();
        Image resizedImage1 = instagramLogoImage.getScaledInstance(55, 55, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);

        JLabel instLGLabel = new JLabel(resizedIcon1);


        instLGLabel.setBorder(BorderFactory.createEmptyBorder(40,60,30,60));
        instLGLabel.setBackground(Color.WHITE);
        instLGLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(instLGLabel);*/

        //border 추가
        JLabel borderLabel4= new JLabel();
        borderLabel4.setBorder(BorderFactory.createEmptyBorder(0,0,170,0));
        borderLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(borderLabel4);



        // 회원가입 버튼
        RoundedButton signUpLabel = new RoundedButton("Create new account");
        signUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpLabel.setForeground(Color.BLACK);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // 회원가입 링크 클릭 시 처리
                // 여기에 회원가입 화면으로 이동하는 기능을 구현할 수 있습니다.
                new SignUpFrame(); // SignUpFrame 실행
                dispose(); // Close the LoginFrame

            }
        });
        panel.add(signUpLabel);

    }
    private void openMainFrame(String name) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame(name);
        });
    }


    private boolean authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM user WHERE name=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        boolean isValidUser = resultSet.next();

        resultSet.close();
        preparedStatement.close();

        return isValidUser;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }
}