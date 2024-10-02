import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class PatientDashboard {
	/**
	 * @wbp.parser.entryPoint
	 */
	
	private String x_number;
    public PatientDashboard(String x_number) {
        this.x_number = x_number; // Set XNumber from constructor parameter

        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1193, 715); //makes frame with size and closes when X is pressed

        JPanel panel = new JPanel();
        panel.setBackground(new Color(36, 36, 59));
        panel.setLayout(null);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(0, 134, 1187, 542);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
                JButton button1 = new JButton("View Patient Records");
                button1.setBounds(10, 11, 562, 251);
                panel_1.add(button1);
                JButton button2 = new JButton("Manage Appointments");
                button2.setBounds(602, 11, 562, 251);
                panel_1.add(button2);
                JButton button3 = new JButton("Billing Information");
                button3.setBounds(602, 283, 562, 251);
                panel_1.add(button3);
                JButton button4 = new JButton("Contact Us");
                
                
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new InformationDisplay("141 Grand Central Pkwy, Jamaica, NY 11439 (Rear entrance of DaSilva Hall)", "(718) 990-6360");
                    }
                });
                        button4.setBounds(10, 283, 562, 251);
                        panel_1.add(button4);
                
                        button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new showBillingInformation(); //when button is pressed it goes to the billing class
                    }
                });
                
                        button2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new AppointmentSystem("patient"); //when button is pressed it goes to the appointment class
                            }
                        });
                
                        button1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new showPatientRecords(x_number); //when button is pressed it goes to the records class
                            }
                        });
                        
                      
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PatientDashboard.class.getResource("sjulogolong.png")));
        lblNewLabel.setBounds(446, 11, 304, 80);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(429, 11, 270, 86);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("PATIENT DASHBOARD");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBounds(515, 97, 151, 26);
        panel.add(lblNewLabel_2);

        frame.setVisible(true);

        System.out.println("Dashboard constructor executed"); //prints message to confirm dashboard was made
    }

    public static void main(String[] args) {
    	String x_number = PatientLogin.getXNumber();
        SwingUtilities.invokeLater(() -> new PatientDashboard(x_number)); //creates new instance
    }
}
