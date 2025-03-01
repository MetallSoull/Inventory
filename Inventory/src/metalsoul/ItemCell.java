package metalsoul;

import javax.swing.*;
import java.awt.*;

public class ItemCell extends JPanel {
    private JLabel itemNameLabel;
    private JLabel itemAmountLabel;
    private String itemName;
    private int itemAmount;

    public ItemCell(String name, int amount) {
        this.itemName = name;
        this.itemAmount = amount;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(50, 50));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        itemNameLabel = new JLabel(name);
        itemNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        itemNameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        itemAmountLabel = new JLabel("x" + amount);
        itemAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        itemAmountLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        add(itemNameLabel, BorderLayout.NORTH);
        add(itemAmountLabel, BorderLayout.SOUTH);

        setBackground(new Color(240, 240, 240));
    }

    public void updateAmount(int newAmount) {
        this.itemAmount = newAmount;
        itemAmountLabel.setText("x" + newAmount);
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemAmount() {
        return itemAmount;
    }
}

