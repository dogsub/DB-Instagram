import com.sun.tools.javac.Main;
import function.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ProfilePage extends JFrame {
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JTextArea introduceArea;
    private JLabel profileImageLabel;

    public ProfilePage(String username) {
        setTitle("MySQLgram");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(365, 586);
        setLocationRelativeTo(null);

        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setOpaque(false); // Make it transparent
        MainPanel.setBackground(new Color(0, 0, 0, 0));
        MainPanel.setFocusable(true);
        MainPanel.requestFocusInWindow();

        JPanel WholePanel = new JPanel();
        WholePanel.setOpaque(false); // Make it transparent
        WholePanel.setLayout(new BorderLayout());
        WholePanel.setBackground(Color.white);
        JPanel TopPanel = new JPanel();
        TopPanel.setLayout(new BorderLayout());
        TopPanel.setOpaque(false); // Make it transparent
        TopPanel.setPreferredSize(new Dimension(365,25));
        TopPanel.setLayout(new BorderLayout());






        ImageIcon instagramLogo = new ImageIcon("src/png/인스타그램_텍스트_로고.png");
        Image instagramLogoImage = instagramLogo.getImage();
        Image resizedImage = instagramLogoImage.getScaledInstance(100, 25, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setOpaque(false);
        TopPanel.add(imageLabel,BorderLayout.WEST);
        TopPanel.setBackground(Color.white);
        TopPanel.setOpaque(true);

        WholePanel.add(TopPanel, BorderLayout.NORTH);
        Color borderColor = Color.BLACK;
        int borderThickness = 1;

        // 선 생성
        Border topBorder = BorderFactory.createMatteBorder(0, 0, borderThickness, 0, borderColor);
        TopPanel.setBorder(topBorder);


        profileImageLabel = new JLabel();
        profileImageLabel.setMaximumSize(new Dimension(100,100));


        usernameLabel = new JLabel();


        emailLabel = new JLabel();


        introduceArea = new JTextArea();
        introduceArea.setEditable(false);
        introduceArea.setMaximumSize(new Dimension(365,40));
        Color very_lightgray = new Color(242,242,242);
        introduceArea.setBackground(very_lightgray);
        JScrollPane scrollPane = new JScrollPane(introduceArea);

        scrollPane.setPreferredSize(new Dimension(365,40));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(22,0,0,20));
        scrollPane.setOpaque(false);



        JPanel CenterPanel = new JPanel();
        CenterPanel.setMaximumSize(new Dimension(365,100));
        CenterPanel.setLayout(new BorderLayout());
        CenterPanel.setBackground(Color.white);
/*        JPanel insidePanel0 = new JPanel();
        insidePanel0.setLayout(new BoxLayout(insidePanel0,BoxLayout.Y_AXIS));
        */
        JPanel insidePanel = new JPanel();
        insidePanel.setLayout(new BoxLayout(insidePanel,BoxLayout.Y_AXIS));
        insidePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        insidePanel.setOpaque(false);

        JLabel borderLabel = new JLabel();
        borderLabel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        borderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        borderLabel.setOpaque(false);
        insidePanel.add(borderLabel);
        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top,BoxLayout.X_AXIS));

        top.setMaximumSize(new Dimension(1000,100));
        top.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel borderLabel3 = new JLabel();
        borderLabel3.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
        borderLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        top.add(profileImageLabel);
        top.add(borderLabel3);
        top.add(scrollPane);
        profileImageLabel.setAlignmentY(Component.TOP_ALIGNMENT);
        profileImageLabel.setBorder(BorderFactory.createEmptyBorder(0,10,10,0));
        CenterPanel.setMaximumSize(new Dimension(100,100));

        insidePanel.add(top);
        JLabel middlePanel = new JLabel();


        middlePanel.setBorder(new LineBorder(Color.black,1));
        middlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        middlePanel.setOpaque(false);
        insidePanel.add(middlePanel);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));

        rightPanel.add(usernameLabel);
        rightPanel.add(emailLabel);
        insidePanel.add(rightPanel);
        CenterPanel.add(insidePanel,BorderLayout.CENTER);
        JPanel middle2Panel = new JPanel();
        middle2Panel.setOpaque(false);
        middle2Panel.setLayout(new BoxLayout(middle2Panel,BoxLayout.Y_AXIS));

/*        JLabel middlePanel2 = new JLabel();
        middlePanel2.setBorder(BorderFactory.createEmptyBorder(22,0,0,0));
        middlePanel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        middlePanel2.setOpaque(false);
        middle2Panel.add(middlePanel2);*/

        JPanel twoButton = new JPanel();
        twoButton.setLayout(new BoxLayout(twoButton, BoxLayout.X_AXIS));
        twoButton.setOpaque(false);
        twoButton.setMaximumSize(new Dimension(365,50));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

        JPanel realtwoButton = new JPanel();
        realtwoButton.setOpaque(false);
        RoundedButton modifyProfileButton = new RoundedButton("프로필 수정");
        Color light_gray = new Color(217,217,217);
        modifyProfileButton.setBackground(light_gray);
        modifyProfileButton.setPreferredSize(new Dimension(150,30));
        modifyProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 // 프로필 페이지 닫기
                openModifyProfilePage(username);
                dispose();// ModifyProfilePage 실행
            }
        });
        realtwoButton.add(modifyProfileButton);

        RoundedButton feedManageButton = new RoundedButton("피드 관리");
        feedManageButton.setBackground(light_gray);
        feedManageButton.setPreferredSize(new Dimension(150,30));
        feedManageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 프로필 페이지 닫기
                openFeedPage(username); // FeedPage 실행
            }
        });
        realtwoButton.add(feedManageButton);

        JLabel line = new JLabel("");
        line.setSize(100,50);
        line.setBackground(Color.BLACK);

        twoButton.add(line);
        twoButton.add(realtwoButton);
        twoButton.setAlignmentX(Component.LEFT_ALIGNMENT);


        insidePanel.add(twoButton);
        JPanel logoutPanel = new JPanel();
        logoutPanel.setLayout(new BorderLayout());
        RoundedButton logout = new RoundedButton("Log Out");

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 프로필 페이지 닫기
                new LoginFrame(); // FeedPage 실행
            }
        });
        insidePanel.add(logout);


        JPanel BottomPanel = new JPanel();
        BottomPanel.setLayout(new BorderLayout());
        BottomPanel.setOpaque(true); // Make it transparent
        BottomPanel.setBorder(topBorder);
        BottomPanel.setPreferredSize(new Dimension(365,50));


        Image MainImage = new ImageIcon("src/png/home.png").getImage();
        Image resizedMainImage = MainImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedMainIcon = new ImageIcon(resizedMainImage);


        JButton MainButton = new JButton(resizedMainIcon);
        MainButton.setMaximumSize(new Dimension(73,50));
        MainButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();// 회원가입 링크 클릭 시 처리
                // 여기에 회원가입 화면으로 이동하는 기능을 구현할 수 있습니다.
                openMainFrame(username)
                ; // SignUpFrame 실행
                // Close the LoginFrame

            }
        });


        Image searchImage = new ImageIcon("src/png/search1.png").getImage();
        Image resizedsearchImage = searchImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedsearchIcon = new ImageIcon(resizedsearchImage);
        JButton SearchButton = new JButton(resizedsearchIcon);

        SearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new MainFrame(username);

                // SignUpFrame 실행
                dispose(); // Close the LoginFrame
            }
        });
        SearchButton.setMaximumSize(new Dimension(10,10));







       /* JButton UploadFeed = new JButton("upload");
        UploadFeed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // 회원가입 링크 클릭 시 처리
                // 여기에 회원가입 화면으로 이동하는 기능을 구현할 수 있습니다.
                new UploadPage(); // SignUpFrame 실행
                dispose(); // Close the LoginFrame
            }
        });
        UploadFeed.setMaximumSize(new Dimension(73,50));*/

        Image profileImage = new ImageIcon("src/png/profile.png").getImage();
        Image resizedprofileImage = profileImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedprofileIcon = new ImageIcon(resizedprofileImage);


        JButton profileButton = new JButton(resizedprofileIcon);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                openProfilePage(username);
            }
        });
        MainButton.setBackground(Color.white);
        SearchButton.setBackground(Color.white);
        profileButton.setBackground(Color.white);
        profileButton.setMaximumSize(new Dimension(73,50));
        MainButton.setBorder(BorderFactory.createEmptyBorder(0,60,0,40));
        BottomPanel.add(MainButton,BorderLayout.WEST);
        SearchButton.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        BottomPanel.add(SearchButton,BorderLayout.CENTER);
        profileButton.setBorder(BorderFactory.createEmptyBorder(0,40,0,60));
        BottomPanel.add(profileButton,BorderLayout.EAST);

        CenterPanel.add(BottomPanel,BorderLayout.SOUTH);

        WholePanel.add(CenterPanel,BorderLayout.CENTER);



        MainPanel.add(WholePanel);
        add(MainPanel);
        setVisible(true);

        // Database Connection
        String url = "jdbc:mysql://localhost:3306/instaDB2";
        String userName = "root";
        String dbPassword = "12345";

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
                usernameLabel.setText("name : " + name);
                emailLabel.setText("email : " + email);
                introduceArea.setText(introduce);
                displayProfileImage(profileImageBytes);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void openProfilePage(String username) {
        // Profile 페이지 열기
        ProfilePage profilePage = new ProfilePage(username);
        profilePage.setVisible(true);
    }
    private void displayProfileImage(byte[] profileImageBytes) {
        ImageIcon profileImageIcon = new ImageIcon(profileImageBytes);
        Image profileImage = profileImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profileImageIcon = new ImageIcon(profileImage);
        profileImageLabel.setIcon(profileImageIcon);
    }

    private void openModifyProfilePage(String username) {
        ModifyProfilePage modifyProfilePage = new ModifyProfilePage(username);
        modifyProfilePage.setVisible(true);
    }

    private void openMainFrame(String username) {
        MainFrame mainFrame = new MainFrame(username);
        mainFrame.setVisible(true);
    }
    private void openFeedPage(String username) {
        int userId = getUserIdFromDatabase(username); // 사용자 이름으로부터 ID를 가져옴
        if (userId != -1) {
            FeedPage feedPage = new FeedPage(userId); // FeedPage 생성하여 해당 사용자 ID를 전달
            feedPage.setVisible(true);
        } else {
            System.out.println("사용자 ID를 가져오는 데 문제가 발생했습니다.");
            // 사용자 ID를 가져오지 못한 경우 예외 처리
        }
    }

    private int getUserIdFromDatabase(String username) {
        int userId = -1; // 기본값 -1로 설정

        String url = "jdbc:mysql://localhost:3306/instaDB2";
        String userName = "root";
        String dbPassword = "12345";

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
            new ProfilePage("username");
        });
    }
}