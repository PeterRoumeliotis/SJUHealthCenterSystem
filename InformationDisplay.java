import javax.swing.*;
import java.awt.*;

public class InformationDisplay extends JFrame {

    public InformationDisplay(String location, String phoneNumber) {
        // Set up the frame
        setTitle("Contact Information");
        setSize(700, 200);
        setLocationRelativeTo(null);  // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));  // Use GridLayout

        // Create labels
        JLabel locationLabel = new JLabel("Location: " + location);
        locationLabel.setHorizontalAlignment(JLabel.CENTER);
        add(locationLabel);

        JLabel phoneLabel = new JLabel("Phone: " + phoneNumber);
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        add(phoneLabel);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Use of SJU address/number
        new InformationDisplay("141 Grand Central Pkwy, Jamaica, NY 11439 (Rear entrance of DaSilva Hall)", "(718) 990-6360");
    }
}
