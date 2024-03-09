/*
package function;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class CircleImage extends JPanel {

    private Image backgroundImage;
    private Image image;

    public CircleImage() {
        // 예제 이미지를 로드합니다. 원하는 이미지로 변경하세요.
        backgroundImage = new ImageIcon("path/to/backgroundImage.jpg").getImage();
        image = new ImageIcon("path/to/yourImage.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 배경 이미지를 그립니다.
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // 원을 그립니다.
        int diameter = Math.min(getWidth(), getHeight()) - 20; // 지름
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        Graphics2D g2d = (Graphics2D) g.create();
        Shape clip = new Ellipse2D.Float(x, y, diameter, diameter);
        g2d.clip(clip);

        // 이미지를 원 안에 그립니다.
        g2d.drawImage(image, x, y, diameter, diameter, this);

        // 테두리를 그립니다.
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(clip);

        g2d.dispose();
    }
}

public class CircleImageExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Circle Image Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            CircleImage imagePanel = new CircleImage();
            frame.setContentPane(imagePanel);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
*/
