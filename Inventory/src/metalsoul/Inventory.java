package metalsoul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Inventory extends Frame implements ExistingItems {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel enterAmount;
    private static JPanel itemsPanel;  // This will hold the item cells
    private static JLabel finalAmount;
    private static JTextField enterAmountTextField;
    private static JLabel enterItem;
    private static JTextField enterItemTextField;
    private static final int WIDTH = 450;
    private static final int HEIGHT = 350;
    private static boolean isFound = false;
    private static ArrayList<Item> inventory = new ArrayList<>();
    private static ArrayList<ItemCell> itemCells = new ArrayList<>();

    public static void framePreferences() {

        frame = new JFrame("Inventory");
        panel = new JPanel();
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        enterAmount = new JLabel("Enter amount: ");
        enterAmount.setBounds(10, 0, 200, 50);
        panel.add(enterAmount);

        finalAmount = new JLabel();
        finalAmount.setBounds(10, 20, 500, 50);
        panel.add(finalAmount);

        enterItem = new JLabel();
        enterItem.setBounds(10, 40, 100, 50);
        panel.add(enterItem);

        enterItemTextField = new JTextField();
        enterItemTextField.setBounds(80, 58, 100, 15);

        enterAmountTextField = new JTextField();
        enterAmountTextField.setBounds(95, 18, 50, 15);
        enterAmountTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                String getText = enterAmountTextField.getText();

                if (key == KeyEvent.VK_ENTER) {
                    if (getText.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Empty input!");
                        return;
                    }
                    try {
                        int getAmount = Integer.parseInt(getText);
                        if (getAmount < 3) {
                            JOptionPane.showMessageDialog(frame, "Minimum amount of items in an inventory is 3!");
                        } else if (getAmount > 6) {
                            JOptionPane.showMessageDialog(frame, "The limit of items in your inventory is 6!");
                        } else {
                            finalAmount.setText("Items in your inventory: " + getAmount);
                            enterItem.setText("Enter item: ");

                            enterAmountTextField.setEditable(false);
                            enterAmountTextField.setText("");
                            enterAmountTextField.setFocusable(false);
                            itemsPanel = new JPanel(new GridLayout(2, 3, 10, 10));
                            itemsPanel.setBounds(10, 80, 200, 100);
                            panel.add(enterItemTextField);
                            panel.add(itemsPanel);
                            panel.revalidate();
                            panel.repaint();
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(frame, "Input must be numbers!");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        panel.add(enterAmountTextField);

        enterItemTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                String getText = enterItemTextField.getText();

                if (key == KeyEvent.VK_ENTER) {
                    enterItemTextField.setText("");

                    if (getText.isEmpty()) {
                        return;
                    }

                    isFound = false;

                    for (String item : existingItems) {
                        if (item.equals(getText)) {
                            isFound = true;
                            break;
                        }
                    }

                    if (!isFound) {
                        JOptionPane.showMessageDialog(frame, "This item does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        addItemToInventory(getText);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        frame.setVisible(true);
    }

    private static void addItemToInventory(String itemName) {
        boolean itemExists = false;

        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                item.setAmount(item.getAmount() + 1);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            inventory.add(new Item(itemName, 1));
        }

        updateInventoryDisplay();
    }

    private static void updateInventoryDisplay() {
        itemsPanel.removeAll();

        itemCells.clear();

        for (Item item : inventory) {
            ItemCell itemCell = new ItemCell(item.getName(), item.getAmount());
            itemCells.add(itemCell);
            itemsPanel.add(itemCell);
        }

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }
}