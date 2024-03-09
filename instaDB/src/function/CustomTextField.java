package function;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomTextField extends RoundJTextField {
    public CustomTextField(int size, String hint) {
        super(size);
        setFont(new Font("SANS_SERIF", Font.BOLD, 12));
        setMaximumSize(new Dimension(290, 40));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        Border fieldBorder = BorderFactory.createCompoundBorder(
                getBorder(),
                BorderFactory.createEmptyBorder(0, 10, 0, 0)
        );
        setBorder(fieldBorder);
        setForeground(Color.GRAY);
        setBackground(new Color(250, 250, 250));

        // 포커스 리스너 추가
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(hint);
                    setForeground(Color.GRAY);
                }
            }
        });


        // 초기 힌트 설정
        setText(hint);
    }
}