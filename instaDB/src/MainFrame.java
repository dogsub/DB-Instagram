import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import function.*;

public class MainFrame extends JFrame {
    private JLabel usernameLabel;
    private Connection connection;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel WholePanel;
    public JPanel searchPanel;

    public MainFrame mainFrame1;
    public MainFrame(String username) {

        setTitle("MySQLgram");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(365, 586);
        setLocationRelativeTo(null);

        int userId = getCurrentUserId(username);




        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setOpaque(false); // Make it transparent
        MainPanel.setBackground(new Color(0, 0, 0, 0));
        MainPanel.setFocusable(true);
        MainPanel.requestFocusInWindow();



        WholePanel = new JPanel();
        WholePanel.setOpaque(false); // Make it transparent
        WholePanel.setLayout(new BorderLayout());
        WholePanel.setBackground(Color.WHITE);

        JPanel TopPanel = new JPanel();
        TopPanel.setLayout(new BoxLayout(TopPanel,BoxLayout.Y_AXIS));
        TopPanel.setOpaque(false); // Make it transparent
        TopPanel.setMaximumSize(new Dimension(365,75));

        JPanel TopPanel1 = new JPanel();
        TopPanel1.setLayout(new BorderLayout());

        ImageIcon instagramLogo = new ImageIcon("src/png/인스타그램_텍스트_로고.png");
        Image instagramLogoImage = instagramLogo.getImage();
        Image resizedImage = instagramLogoImage.getScaledInstance(100, 25, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        TopPanel1.add(imageLabel,BorderLayout.WEST);

        usernameLabel = new JLabel("사용자 이름: " + username);
        usernameLabel.setMaximumSize(new Dimension(200,25));
        TopPanel1.add(usernameLabel,BorderLayout.EAST);
        JPanel TopPanel2 = new JPanel();

        searchField = new JTextField(20);
        searchButton = new JButton("검색");
        searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.setVisible(false);
        TopPanel2.add(searchPanel);
        searchButton.setPreferredSize(new Dimension(30,25));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedUsername = searchField.getText();
                searchUser(username, searchedUsername);
            }
        });
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
/*        imageLabel.setBorder(BorderFactory.createEmptyBorder(40,60,30,60));
        imageLabel.setBackground(Color.WHITE);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);*/


        TopPanel.add(TopPanel1);
        TopPanel.add(TopPanel2);
        WholePanel.add(TopPanel,BorderLayout.NORTH);



/*        JPanel InsidePanel = new JPanel();
        InsidePanel.setLayout(new BoxLayout(InsidePanel, BoxLayout.Y_AXIS));
        InsidePanel.setOpaque(false); // Make it transparent
        InsidePanel.setPreferredSize(new Dimension(205,10000));
        ImageIcon imageIcon = new ImageIcon("src/png/instagram_logo.png");

        for (int i = 0; i < 20; i++) {
            Feed feed = new Feed(imageIcon);
            InsidePanel.add(feed);
        }


        JScrollPane scrollPane = new JScrollPane(InsidePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);*/



        displayUserFeed(userId);
        //WholePanel.add(scrollPane);

        Color borderColor = Color.BLACK;
        int borderThickness = 1;

        // 선 생성
        Border topBorder = BorderFactory.createMatteBorder(borderThickness, 0, 0, 0, borderColor);


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
                dispose();
                new MainFrame(username);

            }
        });


        Image searchImage = new ImageIcon("src/png/search1.png").getImage();
        Image resizedsearchImage = searchImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedsearchIcon = new ImageIcon(resizedsearchImage);
        JButton SearchButton = new JButton(resizedsearchIcon);

        SearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (searchPanel.isVisible()) {
                    searchPanel.setVisible(false);

                } else {
                    searchPanel.setVisible(true);

                }
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
        WholePanel.add(BottomPanel,BorderLayout.SOUTH);

        /*userProfileImageLabel = new JLabel();
        TopPanel.add(userProfileImageLabel, BorderLayout.CENTER);

        usernameLabel = new JLabel("사용자 이름: " + username);
        TopPanel.add(usernameLabel, BorderLayout.WEST);
*/

        //panel.add(profileButton, BorderLayout.NORTH);




        MainPanel.add(WholePanel);
        add(MainPanel);
        //add(panel);
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



    private void openProfilePage(String username) {
        // Profile 페이지 열기
        ProfilePage profilePage = new ProfilePage(username);
        profilePage.setVisible(true);
    }

    private void searchUser(String username, String searchedUsername) {
        try {
            int loggedInUserId = getUserIdFromName(username);
            int searchedUserId = getUserIdFromName(searchedUsername);

            if (loggedInUserId != -1 && searchedUserId != -1) {
                if (!searchedUsername.equals(username)) {
                    insertSearch(loggedInUserId, searchedUserId);
                    openFollowPage(loggedInUserId, searchedUserId);
                } else {
                    JOptionPane.showMessageDialog(this, "자기 자신을 검색했습니다.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "해당 사용자를 찾을 수 없습니다.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // 예외 처리를 추가하여 SQLException을 처리합니다.
        }
    }

    private void insertSearch(int userId, int searchedUserId) {
        try {
            String insertQuery = "INSERT INTO Search (user_id, searched_user_id) VALUES (?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, userId);
            insertStatement.setInt(2, searchedUserId);

            insertStatement.executeUpdate();
            insertStatement.close();
            JOptionPane.showMessageDialog(this, "검색 결과를 저장했습니다.");
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this, "외래 키 제약 조건 위반이 발생했습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리를 추가하여 SQLException을 처리합니다.
        }
    }

    private int getUserIdFromName(String name) {
        int userId = -1;
        try {
            String query = "SELECT id FROM User WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리를 추가하여 SQLException을 처리합니다.
        }
        return userId;
    }
    public void updateStatus(String username) {
        dispose();
        new MainFrame(username);
    }

    private void openFollowPage(int loggedInUserId, int searchedUserId) {
        // FollowPage 페이지 열기

        FollowPage followPage = new FollowPage(loggedInUserId, searchedUserId, connection);
        followPage.setVisible(true);
    }

    private int getCurrentUserId(String username) {
        int userId = -1;
        try {
            String url = "jdbc:mysql://localhost:3306/instaDB2";
            String userName = "root";
            String dbPassword = "12345";

            // connection 객체 초기화
            connection = DriverManager.getConnection(url, userName, dbPassword);

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



    private void displayUserFeed(int userId) {
        try {
            // 기존 쿼리에 팔로잉한 사용자의 피드도 가져올 수 있도록 변경
            String query = "SELECT f.*, u.name FROM Feed f " +
                    "LEFT JOIN Follow fo ON f.user_id = fo.whom_id AND fo.who_id = ? " +
                    "INNER JOIN User u ON f.user_id = u.id " +
                    "WHERE f.user_id = ? OR fo.whom_id IS NOT NULL " + // 팔로잉한 사용자의 피드 포함
                    "ORDER BY f.time DESC";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            JPanel userFeedPanel = new JPanel();
            userFeedPanel.setLayout(new BoxLayout(userFeedPanel, BoxLayout.Y_AXIS));

            while (resultSet.next()) {
                int feedId = resultSet.getInt("id");
                String content = resultSet.getString("content");
                Timestamp timestamp = resultSet.getTimestamp("time");
                byte[] imageData = resultSet.getBytes("feed_img");
                String username = resultSet.getString("name");

                JPanel singleFeedPanel = createFeedPanel(feedId, content, timestamp, imageData, username);
                userFeedPanel.add(singleFeedPanel);
                userFeedPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 각 피드 패널 사이에 간격 추가
            }

            JScrollPane userScrollPane = new JScrollPane(userFeedPanel);
            userScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            userScrollPane.getVerticalScrollBar().setUnitIncrement(16);
            WholePanel.add(userScrollPane, BorderLayout.CENTER);

            setLocationRelativeTo(null);
            setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // createFeedPanel 메서드에 사용자 이름 추가
    private JPanel createFeedPanel(int feedId, String content, Timestamp timestamp, byte[] imageData, String username) {
        JPanel singleFeedPanel = new JPanel();
        singleFeedPanel.setLayout(new BoxLayout(singleFeedPanel,BoxLayout.Y_AXIS));
        singleFeedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 각 피드 패널에 테두리 추가

        if (imageData != null) {
            ImageIcon imageIcon = new ImageIcon(imageData);
            ImageIcon resizedIcon = resizeImageIcon(imageIcon, 340, 365);
            JLabel imageLabel = new JLabel(resizedIcon);
            singleFeedPanel.add(imageLabel);
        }

        JLabel contentLabel = new JLabel("<html>User: " + username + "<br>Feed ID: " + feedId + "<br>Content: " + content + "<br>Time: " + timestamp.toString() + "</html>");
        singleFeedPanel.add(contentLabel);
        singleFeedPanel.setMaximumSize(new Dimension(365,700));
        return singleFeedPanel;
    }

    private ImageIcon resizeImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // 이미지 아이콘에서 이미지를 가져옴
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // 이미지 크기 조정

        return new ImageIcon(resizedImage); // 조정된 이미지를 ImageIcon으로 반환
    }
    //====================================================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame("username");
        });
    }
}