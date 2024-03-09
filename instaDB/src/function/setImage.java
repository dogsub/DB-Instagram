package function;

import javax.swing.*;
import java.awt.*;

public  class setImage extends JLabel {
    public setImage(ImageIcon imageicon,int width,int height){

        Image feedImage = imageicon.getImage();
        Image resizedImage =feedImage.getScaledInstance(width,height,Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(imageLabel);
    }
}
