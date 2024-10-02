import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterPatient {
    private JFrame frame;
    private JPanel panel;

    	//IMPLEMENTS DATABASE
    	private static final String DB_URL = "jdbc:mysql://localhost:3306/sjuhealthservices";
        private static final String USER = "root";
        private static final String PASS = "umargul";
    
        /**
		 * @wbp.parser.entryPoint
		 */
    
        //CREATING UI FOR REGISTERING A NEW PATIENT
    public RegisterPatient() {
        frame = new JFrame("Register New Patient");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        JLabel x_numberLabel = new JLabel("X Number:");
        x_numberLabel.setBounds(33, 50, 100, 30);
        panel.add(x_numberLabel);

        JTextField x_numberField = new JTextField();
        x_numberField.setBounds(179, 50, 171, 30);
        panel.add(x_numberField);

        JLabel passwordLabel = new JLabel("Create Password:");
        passwordLabel.setBounds(33, 100, 152, 30);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(179, 100, 171, 30);
        panel.add(passwordField);
        
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(33, 159, 152, 30);
        panel.add(firstNameLabel);
        
        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(179, 159, 171, 30);
        panel.add(firstNameField);
        
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(33, 216, 152, 30);
        panel.add(lastNameLabel);
        
        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(179, 216, 171, 30);
        panel.add(lastNameField);
               
        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setBounds(33, 271, 152, 30);
        panel.add(heightLabel);
        
        JTextField heightField = new JTextField();
        heightField.setBounds(179, 271, 171, 30);
        panel.add(heightField);
           
        JLabel weightLabel = new JLabel("Weight:");
        weightLabel.setBounds(33, 330, 152, 30);
        panel.add(weightLabel);
        
        JTextField weightField = new JTextField();
        weightField.setBounds(179, 330, 171, 30);
        panel.add(weightField);
        
        JLabel dobLabel = new JLabel("Date Of Birth:");
        dobLabel.setBounds(33, 393, 152, 30);
        panel.add(dobLabel);
        
        JTextField dobField = new JTextField();
        dobField.setBounds(179, 393, 171, 30);
        panel.add(dobField);
        
        JLabel insuranceCompanyLabel = new JLabel("Insurance Company:");
        insuranceCompanyLabel.setBounds(33, 451, 152, 30);
        panel.add(insuranceCompanyLabel);
        
        String[] insuranceProviders = {"HealthSafe", "MedicalCare", "Empire Insurance"};
        JComboBox<String> insuranceComboBox = new JComboBox<>(insuranceProviders);
        insuranceComboBox.setBounds(179, 450, 171, 30);
        panel.add(insuranceComboBox);
        
        JLabel insuranceIDLabel = new JLabel("Insurance ID:");
        insuranceIDLabel.setBounds(33, 520, 152, 30);
        panel.add(insuranceIDLabel);
        
        JTextField insuranceIDField = new JTextField();
        insuranceIDField.setBounds(179, 520, 171, 30);
        panel.add(insuranceIDField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(158, 595, 100, 30);
        panel.add(registerButton);

        //WHEN THE REGISTER BUTTON IS PRESSED TAKES ALL DATA FROM JFIELDS AND SEND IT TO GET INSERTED TO THE DATABASE;
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input from fields
                String x_number = x_numberField.getText();
                String password = new String(passwordField.getPassword());
                String firstName= firstNameField.getText();
                String lastName= lastNameField.getText();
                String height = heightField.getText();
                String weight = weightField.getText();
                String dob = dobField.getText();
                String insuranceCompany = (String) insuranceComboBox.getSelectedItem();
                String insuranceID = insuranceIDField.getText();

                insertUserData(x_number, password, firstName, lastName, height, dob, weight, insuranceCompany, insuranceID);
            }
        });
        
        //FRAME SETTINGS
        frame.getContentPane().add(panel);
        frame.setSize(447, 675);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //METHOD USED TO INSERT DATA TO THE DATABASE
    private void insertUserData(String x_number, String password, String firstName, String lastName, String height, String dob, String weight, String insuranceCompany, String insuranceId) {
    	//SENDS HALF THE INFORMATION TO PATIENT TABLE
        String userQuery = "INSERT INTO patient (XNumber, password, role, Fname, Lname) VALUES (?, ?, ?, ?,?)";
        //SENDS OTHER HALF OF THE INFORMATION TO PATIENT RECORD TABLE
        String patientQuery = "INSERT INTO patientrecord (PatientXNUMBER, height, DOB, weight, insurance_company, insurance_id) VALUES (?, ?, ?, ?, ?, ?)";

        //CONNECTING TO THE DATABASE
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement userStmt = conn.prepareStatement(userQuery);
             PreparedStatement patientStmt = conn.prepareStatement(patientQuery)) {

            
            userStmt.setString(1, x_number);
            userStmt.setString(2, password);
            userStmt.setString(3, "patient"); 
            userStmt.setString(4, firstName);
            userStmt.setString(5, lastName);
            int userRowsAffected = userStmt.executeUpdate();

            patientStmt.setString(1, x_number); 
            patientStmt.setString(2, height);
            patientStmt.setString(3, dob);
            patientStmt.setString(4, weight);
            patientStmt.setString(5, insuranceCompany);
            patientStmt.setString(6, insuranceId);
            int patientRowsAffected = patientStmt.executeUpdate();

            if (userRowsAffected > 0 && patientRowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "User and patient data registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to register user and patient data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error registering user and patient data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterPatient::new);
    }
}
