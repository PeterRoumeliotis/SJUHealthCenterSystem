import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class showPatientRecords {
	
	//IMPLEMENTED DATABASE
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sjuhealthservices";
    private static final String USER = "root";
    private static final String PASS = "umargul";

    public showPatientRecords(String x_number) {
        SwingUtilities.invokeLater(() -> {
        	
            // FRAME SETTINGS
            JFrame frame = new JFrame("Patient Records");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(600, 400);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // QUERY TO FETCH PATIENT RECORDS FROM PATIENT AND PATIENT RECORD TABLE
            String query = "SELECT pr.*, p.Fname, p.Lname " +
                    "FROM patientrecord pr " +
                    "JOIN patient p ON pr.PatientXNUMBER = p.XNumber " +
                    "WHERE pr.PatientXNUMBER = ?";

            //ESTABLISHING CONNECTION TO DB
            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, x_number);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                    	//GETS DATA FROM DB AND STORES THEM IN VARIABLES
                        String first_name = resultSet.getString("Fname");
                        String last_name = resultSet.getString("Lname");
                        String dob = resultSet.getString("DOB");
                        double height = resultSet.getDouble("height");
                        int weight = resultSet.getInt("weight");
                        String insurance = resultSet.getString("insurance_company");
                        int insuranceID = resultSet.getInt("insurance_id");
                        
                        //USES VARIABLES CREATED TO PRINT THE PATIENTS INFORMATION
                        JLabel nameLabel = new JLabel("Name: " + first_name + " " + last_name);
                        JLabel dobLabel = new JLabel("Date of Birth: " + dob);
                        JLabel heightLabel = new JLabel("Height: " + height + " ft");
                        JLabel weightLabel = new JLabel("Weight: " + weight + " lbs");
                        JLabel insuranceLabel = new JLabel("Insurance: " + insurance);
                        JLabel insuranceIDLabel = new JLabel("Insurance ID: " + insuranceID);
                        
                        //ADDS LABELS TO THE PANNEL
                        panel.add(nameLabel);
                        panel.add(dobLabel);
                        panel.add(heightLabel);
                        panel.add(weightLabel);
                        panel.add(insuranceLabel);
                        panel.add(insuranceIDLabel);
                        
                        //HANDLING SQL ERRORS
                    } else {
                        JOptionPane.showMessageDialog(null, "No patient records found for this XNumber.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching patient records.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            //FRAME SETTINGS
            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    //GETS XNUMBER FROM XNUMBER INPUTED IN PATIENTLOGIN
    public static void main(String[] args) {
        String xNumber = PatientLogin.getXNumber();
        SwingUtilities.invokeLater(() -> new showPatientRecords(xNumber));
    }
}
