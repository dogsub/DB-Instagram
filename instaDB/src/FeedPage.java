import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FeedPage extends JFrame {

    private JLabel usernameLabel;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel WholePanel;
    public JPanel searchPanel;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;

    private Connection connection;
    private int loggedInUserId = 1;


    public FeedPage() {
        // 기본 생성자 구현
        initializeUI();
        displayUserFeed();
    }

    // 다른 생성자 오버로딩하여 사용자 ID를 받도록 설정
    public FeedPage(int userId) {
        loggedInUserId = userId;
        initializeUI();
        displayUserFeed();
    }



    private void initializeUI() {
        setTitle("MySQLgram");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(365,586);
        setLocationRelativeTo(null);





        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setOpaque(false); // Make it transparent
        MainPanel.setBackground(new Color(0, 0, 0, 0));
        MainPanel.setFocusable(true);
        MainPanel.requestFocusInWindow();



        WholePanel = new JPanel();
        WholePanel.setOpaque(false); // Make it transparent
        WholePanel.setLayout(new BorderLayout());


        JPanel TopPanel = new JPanel();
        TopPanel.setLayout(new BoxLayout(TopPanel,BoxLayout.Y_AXIS));
        TopPanel.setOpaque(false); // Make it transparent
        TopPanel.setMaximumSize(new Dimension(365,75));

        JPanel TopPanel1 = new JPanel();
        TopPanel1.setBackground(Color.white);
        TopPanel1.setLayout(new BorderLayout());

        ImageIcon instagramLogo = new ImageIcon("src/png/인스타그램_텍스트_로고.png");
        Image instagramLogoImage = instagramLogo.getImage();
        Image resizedImage = instagramLogoImage.getScaledInstance(100, 25, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        TopPanel1.add(imageLabel,BorderLayout.WEST);
        createButton = new JButton("Create Feed");
        createButton.setMaximumSize(new Dimension(50,25));
        createButton.setBackground(Color.white);
        createButton.setForeground(Color.black);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = JOptionPane.showInputDialog("Enter feed content:");
                createFeed(content);
            }
        });
        TopPanel1.add(createButton,BorderLayout.EAST);

        TopPanel.add(TopPanel1);
        WholePanel.add(TopPanel,BorderLayout.NORTH);





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
                new MainFrame(getUsernameFromId(loggedInUserId));

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

                new ProfilePage(getUsernameFromId(loggedInUserId));
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

        try {
            String url = "jdbc:mysql://localhost:3306/instaDB2";
            String userName = "root";
            String dbPassword = "12345";

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

/*    private void initDatabaseConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/instaDB2";
            String userName = "root";
            String dbPassword = "12345";

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }*/
    private void openProfilePage(int userId) {
        ProfilePage profilePage = new ProfilePage(getUsernameFromId(userId)); // ProfilePage 생성
        profilePage.setVisible(true);
    }
    private String getUsernameFromId(int userId) {
        String username = "";

        String url = "jdbc:mysql://localhost:3306/instaDB2";
        String userName = "root";
        String dbPassword = "12345";

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
    private void createFeedWithImage(String content, File imageFile) {
        try {
            String query;
            if (imageFile != null) {
                FileInputStream fis = new FileInputStream(imageFile);
                query = "INSERT INTO Feed (user_id, content, feed_img, time) VALUES (?, ?, ?, NOW())";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, loggedInUserId);
                preparedStatement.setString(2, content);
                preparedStatement.setBinaryStream(3, fis, (int) imageFile.length());
                preparedStatement.executeUpdate();

                fis.close();
            } else {
                query = "INSERT INTO Feed (user_id, content, feed_img, time) VALUES (?, ?, NULL, NOW())";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, loggedInUserId);
                preparedStatement.setString(2, content);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFeed(String content) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedImage = fileChooser.getSelectedFile();
            createFeedWithImage(content, selectedImage);



            // 새로운 FeedPage를 생성하여 보여줌
            refreshFeedPage();
        } else {
            // 사용자가 "취소"를 선택한 경우에 대한 처리
            // 여기서는 아무 작업도 하지 않음
        }
    }
    private ImageIcon resizeImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // 이미지 아이콘에서 이미지를 가져옴
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // 이미지 크기 조정

        return new ImageIcon(resizedImage); // 조정된 이미지를 ImageIcon으로 반환
    }
    private void refreshFeedPage() {
        // 페이지를 다시 표시하는 메서드 호출
        dispose();
        new FeedPage(loggedInUserId);
    }
    private void updateFeedContent(String feedId) {
        String feedIdInput = feedId;
        if (feedIdInput != null && !feedIdInput.isEmpty()) {
            try {
                int feedIdToUpdate = Integer.parseInt(feedIdInput);

                // 특정 피드 아이디의 content를 가져온 후 수정
                String newContent = JOptionPane.showInputDialog("Enter new content for Feed ID " + feedIdToUpdate + ":");
                if (newContent != null && !newContent.isEmpty()) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.showOpenDialog(null);
                    File selectedImage = fileChooser.getSelectedFile();

                    updateFeed(feedIdToUpdate, newContent, selectedImage);
                    displayUserFeed(); // 업데이트 후 피드 다시 표시

                    // 업데이트 후 해당 창을 다시 열어줌
                    reopenFeedPage();


                } else {
                    // 사용자가 취소 또는 빈 입력을 했을 때 처리할 내용을 여기에 추가하세요.
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Feed ID.");
            }
        }
    }

    // 특정 피드 ID에 해당하는 content와 이미지를 업데이트하는 메서드
    private void updateFeed(int feedId, String newContent, File imageFile) {
        try {
            String query;
            if (imageFile != null) {
                FileInputStream fis = new FileInputStream(imageFile);
                query = "UPDATE Feed SET content = ?, feed_img = ? WHERE id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newContent);
                preparedStatement.setBinaryStream(2, fis, (int) imageFile.length());
                preparedStatement.setInt(3, feedId);
                preparedStatement.executeUpdate();

                fis.close();
            } else {
                query = "UPDATE Feed SET content = ? WHERE id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newContent);
                preparedStatement.setInt(2, feedId);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FeedPage를 다시 열어주는 메서드
    private void reopenFeedPage() {
        dispose(); // 현재 FeedPage 창을 닫음
        new FeedPage(loggedInUserId); // 업데이트된 정보를 가진 새로운 FeedPage 열기
    }


    private void deleteFeed(String feedId) {
        String feedIdInput = feedId;
        if (feedIdInput != null && !feedIdInput.isEmpty()) {
            try {
                int feedIdToDelete = Integer.parseInt(feedIdInput);
                boolean deletionSuccessful = deleteFeedFromDatabase(feedIdToDelete);

                if (deletionSuccessful) {
                    JOptionPane.showMessageDialog(null, "Feed deleted successfully!");
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
            String url = "jdbc:mysql://localhost:3306/instaDB2";
            String userName = "root";
            String dbPassword = "12345";

            Connection connection = DriverManager.getConnection(url, userName, dbPassword);

            String query = "SELECT * FROM Feed WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, loggedInUserId);
            ResultSet resultSet = preparedStatement.executeQuery();

            JPanel feedPanel = new JPanel();
            feedPanel.setBackground(Color.white);
            feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS)); // 여러 개의 패널을 세로로 배치

            while (resultSet.next()) {
                int feedId = resultSet.getInt("id");
                String content = resultSet.getString("content");
                Timestamp timestamp = resultSet.getTimestamp("time");
                byte[] imageData = resultSet.getBytes("feed_img");

                JPanel singleFeedPanel = new JPanel();
                singleFeedPanel.setLayout(new BoxLayout(singleFeedPanel, BoxLayout.Y_AXIS)); // 이미지와 텍스트를 세로로 나란히 배치
                singleFeedPanel.setMaximumSize(new Dimension(350,700));
                singleFeedPanel.setOpaque(false);
                JPanel left1 = new JPanel();
                left1.setOpaque(false);
                left1.setLayout(new BorderLayout());
                JLabel feedBorder0 = new JLabel(getUsernameFromId(loggedInUserId));
                feedBorder0.setOpaque(false);
                left1.add(feedBorder0,BorderLayout.WEST);
                singleFeedPanel.add(left1);
                updateButton = new JButton("Update Feed");

                updateButton.setBackground(Color.white);
                updateButton.setForeground(Color.black);
                deleteButton = new JButton("Delete Feed");
                deleteButton.setBackground(Color.white);
                deleteButton.setForeground(Color.black);

                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateFeedContent(Integer.toString(feedId));

                    }
                });

                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteFeed(Integer.toString(feedId));
                        dispose();
                        new FeedPage(loggedInUserId);
                    }
                });
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BorderLayout());
                buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                buttonPanel.add(updateButton,BorderLayout.EAST);
                buttonPanel.add(deleteButton,BorderLayout.WEST);
                buttonPanel.setMaximumSize(new Dimension(340,20));
                buttonPanel.setOpaque(false);


                // 이미지가 null이 아닌 경우에만 이미지 표시
                if (imageData != null) {
                    ImageIcon imageIcon = new ImageIcon(imageData);
                    ImageIcon resizedIcon = resizeImageIcon(imageIcon, 340, 365); // 원하는 크기로 이미지 조정
                    JLabel imageLabel = new JLabel(resizedIcon);

                    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    singleFeedPanel.add(imageLabel);
                }

                JLabel contentLabel = new JLabel("<html>Feed ID: " + feedId + "<br>Content: " + content + "<br>Time: " + timestamp.toString() + "</html>");
                contentLabel.setMaximumSize(new Dimension(340,50));
                contentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                singleFeedPanel.add(contentLabel);
                singleFeedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                JLabel feedBorder = new JLabel();
                feedBorder.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
                feedBorder.setAlignmentX(Component.CENTER_ALIGNMENT);
                singleFeedPanel.add(feedBorder);
                singleFeedPanel.add(buttonPanel);
                JLabel feedBorder1 = new JLabel();
                feedBorder1.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
                feedBorder1.setAlignmentX(Component.CENTER_ALIGNMENT);
                singleFeedPanel.add(feedBorder1);
                feedPanel.add(singleFeedPanel);

            }

            JScrollPane scrollPane = new JScrollPane(feedPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.getVerticalScrollBar().setUnitIncrement(30);
            WholePanel.add(scrollPane, BorderLayout.CENTER);

            setLocationRelativeTo(null);
            setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FeedPage();
        });
    }
}