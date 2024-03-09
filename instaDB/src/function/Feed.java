package function;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Feed extends JPanel {
    public Feed(ImageIcon imageicon) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Border border = BorderFactory.createLineBorder(Color.BLACK,1);
        setBorder(border);
        setMaximumSize(new Dimension(356,356));

        JLabel Profile = new JLabel();


        JTextField UserName = new JTextField("유저 이름");
        UserName.setMaximumSize(new Dimension(80,30));
        add(UserName);
/*        JLabel FeedImage = new JLabel();
        ImageIcon ProfileImg = new ImageIcon("src/png/instagram_logo.png");
        FeedImage.add(new setImage(ProfileImg,10,20));

        Profile.add(FeedImage,BorderLayout.WEST);*/

        add(Profile);




        // 이미지 중앙에 표시하는 레이블
        Image feedImage = imageicon.getImage();
        Image resizedImage =feedImage.getScaledInstance(356,300,Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(imageLabel);
        /*add(new setImage(imageicon,305,200));*/
        JPanel Bottom = new JPanel();

        // 좋아요 버튼
        JButton likeButton = new JButton("좋아요");
        likeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 좋아요 버튼 클릭 시 수행할 동작 추가
                JOptionPane.showMessageDialog(null, "좋아요 버튼 클릭!");
            }
        });
        Bottom.add(likeButton);

        // 댓글 버튼
        JButton commentButton = new JButton("댓글");
        commentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 댓글 버튼 클릭 시 수행할 동작 추가
                JOptionPane.showMessageDialog(null, "댓글 버튼 클릭!");
            }
        });
        Bottom.add(commentButton);
        add(Bottom);
    }
}