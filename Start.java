import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
	/**
	 * @wbp.parser.entryPoint
	 */
	
    public Start() {
    	
        JFrame frame = new JFrame("Start");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(); 
        panel.setBackground(new Color(36, 36, 59));
        panel.setBounds(0, 0, 418, 729);

        // CREATED BUTTONS/STYLES BUTTONS
        JButton patientButton = new JButton("Patient Login");
        patientButton.setBounds(84, 431, 232, 63);
        JButton employeeButton = new JButton("Employee Login");
        employeeButton.setBounds(84, 524, 232, 63);
        JButton registerButton = new JButton("Register as a Patient");
        registerButton.setBounds(108, 648, 171, 23);
        
        patientButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        employeeButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        registerButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        //GIVE BUTTONS FUNTIONALITY
        patientButton.addActionListener(new ActionListener() { //Action listener for patient login button that closes start frame and opens patient login frame
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new PatientLogin(); 
            }
        });

        
        employeeButton.addActionListener(new ActionListener() { //Action listener for employee login button that closes start frame and opens employee login frame
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EmployeeLogin(); 
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterPatient();
            }
        });
        
        
        //LAYOUT SETTINGS/ADD BUTTONS TO PANEL
        frame.getContentPane().setLayout(null);
        panel.setLayout(null);
        panel.add(patientButton);
        panel.add(employeeButton);
        panel.add(registerButton);
        frame.getContentPane().add(panel);
        
        
        //CREATED A USER FRIENDLY GUI
        JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Start.class.getResource("sjulogo.png")));
        lblNewLabel.setBounds(84, 43, 232, 324);
        panel.add(lblNewLabel);
        
        
        JLabel lblNewLabel_2 = new JLabel("or");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBounds(186, 611, 46, 14);
        panel.add(lblNewLabel_2);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(417, 0, 760, 729);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Start.class.getResource("sjuimage.jpg")));
        lblNewLabel_1.setBounds(-89, 0, 849, 729);
        panel_1.add(lblNewLabel_1);
        frame.setSize(1193, 768);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Start::new);
    }
}

