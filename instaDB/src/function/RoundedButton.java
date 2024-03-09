package function;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setOpaque(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D graphics = (Graphics2D) g;

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            graphics.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            graphics.setColor(getBackground().darker());
        } else {
            graphics.setColor(getBackground());
        }

        graphics.fillRoundRect(0, 0, width - 1, height - 1, 15, 15);

        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

        int textX = (width - stringBounds.width) / 2;
        int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();

        graphics.setColor(getForeground());
        graphics.setFont(getFont());
        graphics.drawString(getText(), textX, textY);
        graphics.dispose();

        super.paintComponent(g);
    }
    public void setPreferredSize(Dimension size) {
        super.setPreferredSize(size);
        revalidate();
    }
    public void setButtonSize(int width, int height) {
        super.setPreferredSize(new Dimension(width, height));
        revalidate();
    }
}