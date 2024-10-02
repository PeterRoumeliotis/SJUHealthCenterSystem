import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EmployeeLogin {

    private static JFrame frame;
    private static JPanel panel;
    public EmployeeLogin() {
    	
    	//FRAME SETTINGS
        frame = new JFrame("SJU Health Center Employee Log In");
        frame.setSize(1193, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //creates frame and sets size as well as closes when x is pressed
        frame.setLocationRelativeTo(null);


        panel = new JPanel();
        panel.setBackground(new Color(36, 36, 59));
        frame.getContentPane().add(panel);
        placeComponents(panel); //creates panel, adds it to frame and places components in the panel
        
        frame.setResizable(true); 
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
    	
    	//COMPONENT PLACEMENTS
        Font labelFont = new Font("Arial", Font.BOLD, 20); //creates fonts
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(EmployeeLogin.class.getResource("sjulogo.png")));
        lblNewLabel.setBounds(463, 26, 232, 324);
        panel.add(lblNewLabel);

        JLabel userLabel = new JLabel("Employee User:");
        userLabel.setForeground(new Color(255, 255, 255));
        userLabel.setBounds(381, 384, 153, 24);
        userLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 23));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(userLabel); //sets font and adds user label for the username entering, places it on panel

        JTextField userText = new JTextField(20);
        userText.setBounds(381, 409, 398, 45);
        userText.setMaximumSize(new Dimension(200, 50));
        userText.setFont(labelFont);        
        panel.add(userText); //sets font and adds ability for user text, places it on panel

        JLabel passwordLabel = new JLabel("Employee Password:");
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordLabel.setBounds(381, 477, 221, 24);
        passwordLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 23));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);        
        panel.add(passwordLabel); //sets font and adds password label for the password entering, places it on panel

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(381, 502, 398, 45);
        passwordText.setMaximumSize(new Dimension(200, 50));
        passwordText.setFont(labelFont);        
        panel.add(passwordText); //sets font and adds ability for user text, places it on panel

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(381, 579, 398, 50);
        loginButton.setFont(buttonFont);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);        
        panel.add(loginButton); //sets font and makes the button to press after you input your username and password

        userLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        userText.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        passwordText.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(JPanel.CENTER_ALIGNMENT); //sets where everything will be on the screen

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeLoginAuthenticator authenticator = new EmployeeLoginAuthenticator();
                boolean authenticated = authenticator.authenticate(userText.getText(), new String(passwordText.getPassword()));
                if (authenticated) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE); //shows when log in is a success
                    SwingUtilities.invokeLater(() -> { 
                        try {
                            frame.dispose(); //closes previous frame                                //this block is to authenticate that the user and pass match database table, calls authentication class
                            new EmployeeDashboard();  //runs employee dashboard
                        } catch (Exception ex) {
                            ex.printStackTrace();  //catches errors
                        } 
                    });
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
                } //shows this text if the username and/or password were incorrect
            }
        });
        
    }
}
