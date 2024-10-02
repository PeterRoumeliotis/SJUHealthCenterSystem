import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PatientLogin {

    private static JFrame frame;
    private static JPanel panel;
    private static String xNumber; 

    public PatientLogin() {
    	
    	//FRAME SETTINGS
        frame = new JFrame("SJU Health Center Patient Log In");
        frame.setSize(1193, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);


        panel = new JPanel();
        panel.setBackground(new Color(36, 36, 59));
        panel.setBounds(0, 0, 1177, 729);
        frame.getContentPane().add(panel);
        placeComponents(panel); 
        
        
        frame.setResizable(true); 
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
    	
    	//COMPONENT PLACEMENT
        Font labelFont = new Font("Arial", Font.BOLD, 20); 
        Font buttonFont = new Font("Arial", Font.PLAIN, 20); //creates fonts
        panel.setLayout(null);        
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(PatientLogin.class.getResource("sjulogo.png")));
        label.setBounds(463, 26, 232, 324);
        panel.add(label);

        JLabel userLabel = new JLabel("Patient X Number:");
        userLabel.setForeground(new Color(255, 255, 255));
        userLabel.setBounds(381, 381, 159, 27);
        userLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 23));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(userLabel); 

        JTextField userText = new JTextField(20);
        userText.setBounds(381, 409, 398, 45);
        userText.setMaximumSize(new Dimension(200, 50));
        userText.setFont(labelFont);        
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Patient Password:");
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordLabel.setBounds(381, 475, 159, 27);
        passwordLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 23));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);        
        panel.add(passwordLabel); 

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(381, 502, 398, 45);
        passwordText.setMaximumSize(new Dimension(200, 50)); 
        passwordText.setFont(labelFont);        
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(381, 579, 398, 50);
        loginButton.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);        
        panel.add(loginButton); 

   
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientLoginAuthenticator authenticator = new PatientLoginAuthenticator();
                boolean authenticated = authenticator.authenticate(userText.getText(), new String(passwordText.getPassword()));
                if (authenticated) {
                    xNumber = userText.getText(); // Set XNumber after successful authentication
                    JOptionPane.showMessageDialog(frame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE); //shows when log in is a success
                    SwingUtilities.invokeLater(() -> {
                        try {
                            frame.dispose();  //closes previous frame                                //this block is to authenticate that the user and pass match database table, calls authentication class
                            new PatientDashboard(xNumber);  //runs patient dashboard
                        } catch (Exception ex) {
                            ex.printStackTrace();  //catches errors 
                        } 
                    });
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
    }
    
    public static String getXNumber() {
        return xNumber;
    }
}
