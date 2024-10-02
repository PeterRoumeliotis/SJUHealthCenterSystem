import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDashboard {
	/**
	 * @wbp.parser.entryPoint
	 */
    public EmployeeDashboard() {
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1193, 715); //makes frame with size and closes when X is pressed

        JPanel panel = new JPanel();
        panel.setBounds(0, 134, 1187, 542);
        panel.setBackground(new Color(255, 255, 255));

        JButton button1 = new JButton("Add Patient Records"); //creates buttons for dashboard
        button1.setBounds(10, 11, 573, 251);
        JButton button2 = new JButton("View Appointments");
        button2.setBounds(603, 11, 562, 251);
        JButton button3 = new JButton("Assign bills");
        button3.setBounds(10, 273, 573, 261);
        JButton button4 = new JButton("Settings");
        button4.setBounds(603, 273, 562, 261);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AppointmentSystem("employee"); //when button is pressed it goes to the appointment class
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String xNumber = JOptionPane.showInputDialog(frame, "Enter patient XNumber:");
                if (xNumber != null && !xNumber.isEmpty()) {
                    new showPatientRecords(xNumber);
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new showBillingInformation(); //when button is pressed it goes to the billing class
            }
        });
        
        frame.getContentPane().setLayout(null);
        panel.setLayout(null);

        panel.add(button1); //adds buttons to panel
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        frame.getContentPane().add(panel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 1176, 676);
        frame.getContentPane().add(panel_1);
        panel_1.setBackground(new Color(36, 36, 59));
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(EmployeeDashboard.class.getResource("sjulogolong.png")));
        lblNewLabel.setBounds(450, 11, 270, 86);
        panel_1.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("EMPLOYEE DASHBOARD");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(514, 108, 162, 14);
        panel_1.add(lblNewLabel_1);

        
        frame.setVisible(true);

        System.out.println("Employee Dashboard constructor executed"); //prints message to confirm dashboard was made
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeDashboard()); //creates new instance
    }
}
