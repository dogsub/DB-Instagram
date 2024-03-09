package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ComboBox Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();

            String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
            JComboBox<String> comboBox = new JComboBox<>(items);

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 선택된 항목 가져오기
                    String selectedValue = (String) comboBox.getSelectedItem();
                }
            });

            panel.add(comboBox);
            frame.getContentPane().add(panel);

            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
