package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoScroll {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Auto Scroll Button Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            JPanel area = new JPanel();
            area.setPreferredSize(new Dimension(5000,300));

            JScrollPane scrollPane = new JScrollPane(area);

            JButton scrollButton = new JButton("Scroll");
            scrollButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 버튼이 눌렸을 때 자동으로 스크롤
                    int amount = 10;
                    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                    verticalScrollBar.setValue(verticalScrollBar.getValue()+amount);
                }
            });

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(scrollButton, BorderLayout.SOUTH);

            frame.getContentPane().add(panel);
            frame.setSize(300, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
