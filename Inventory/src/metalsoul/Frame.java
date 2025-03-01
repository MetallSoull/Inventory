package metalsoul;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Frame extends JFrame {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel openInventory;
    private static JButton openInventoryButton;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 100;

    public static void framePreferences() {

        frame = new JFrame("Inventory");
        panel = new JPanel();
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        openInventory = new JLabel("Open Inventory: ");
        panel.add(openInventory);

        openInventoryButton = new JButton("Open");
        openInventoryButton.setFocusable(false);
        openInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(openInventoryButton.equals(e.getSource())) {
                    frame.dispose();
                    Inventory inventory = new Inventory();
                    inventory.framePreferences();
                }
            }
        });
        panel.add(openInventoryButton);

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        framePreferences();
    }
}