import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AppointmentSystem {
    private JFrame frame;
    private JPanel panel;

    //ESTABLISHES CONNECTION TO THE DATABASE
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sjuhealthservices";
    private static final String USER = "root";
    private static final String PASS = "umargul";

    
    public AppointmentSystem(String userRole) {
    	//CREATES FRAME
        frame = new JFrame("Appointment System");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //CREATES PANEL
        panel = new JPanel();
        panel.setLayout(null);

        //IF THE USER SIGNS IN FROM THE PATIENT DASHBOARD THEN THEY ARE AUTOMATICALLY ASSIGNED AS PATIENT, UNLESS FROM THE EMPLOYEE DASHBOARD
        if (userRole.equalsIgnoreCase("patient")) {
            createPatientUI();
        } else {
        	//IF THE ROLE IS NOT PATIENT AND IS EMPLOYEE THEN CREATES A PANEL TO VIEW APPOINTMENTS
            JLabel titleLabel = new JLabel("Appointments for Employee: umargul1122");
            titleLabel.setBounds(33, 10, 300, 30);
            panel.add(titleLabel);
            
            //SET AS DEFAULT DOCTOR
            showAppointmentsForDoctor("umargul1122");
        }

        //FRAME SETTINGS
        frame.getContentPane().add(panel);
        frame.setSize(447, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //CREATED UI FOR THE PATIENT TO INPUT DATA
    private void createPatientUI() {
        JLabel x_numberLabel = new JLabel("X Number:");
        x_numberLabel.setBounds(33, 50, 100, 30);
        panel.add(x_numberLabel);

        JTextField x_numberField = new JTextField();
        x_numberField.setBounds(179, 50, 171, 30);
        panel.add(x_numberField);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(33, 100, 152, 30);
        panel.add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(179, 100, 171, 30);
        panel.add(dateField);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(33, 159, 152, 30);
        panel.add(timeLabel);

        JTextField timeField = new JTextField();
        timeField.setBounds(179, 159, 171, 30);
        panel.add(timeField);

        JButton createAppointmentButton = new JButton("Create Appointment");
        createAppointmentButton.setBounds(158, 220, 152, 30);
        panel.add(createAppointmentButton);

        
        //WHEN THE CREATE APPOINTMENT BUTTON IS PRESSED GET ALL DATA INPUTED IN JFIELDS AND SEND IT TO CREATEAPPOINTMENT METHOD
        createAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String x_number = x_numberField.getText();
                String date = dateField.getText();
                String time = timeField.getText();

                createAppointment(x_number, date, time);
            }
        });
    }

    //METHOD TO SEND THE APPOINTMENT INFORMATION TO THE APPOINTMENT TABLE IN THE DATABASE
    private void createAppointment(String x_number, String date, String time) {
    	//CREATES QUERY
        String appointmentQuery = "INSERT INTO appointment (username, PatientXNUMBER, Date, Time) VALUES (?, ?, ?, ?)";

        //ESTABLISHES CONNECTION TO DB
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement appointmentStmt = conn.prepareStatement(appointmentQuery)) {

            appointmentStmt.setString(1, "umargul1122");
            appointmentStmt.setString(2, x_number);
            appointmentStmt.setString(3, date);
            appointmentStmt.setString(4, time);
            int rowsAffected = appointmentStmt.executeUpdate();
            
            	if (rowsAffected > 0) {
            		JOptionPane.showMessageDialog(frame, "Appointment created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to create appointment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error creating appointment.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //METHOD TO DISPLAY APPOINMENT CREATED WITH THE DOCTORS USERNAME
    private void showAppointmentsForDoctor(String doctorName) {
    	//CREATES QUERY
        String appointmentsQuery = "SELECT * FROM appointment WHERE username = ?"; 
        //CREATES AN AREA OF TEXT THAT DISPLAYS THE PATIENTS APPOINTMENT
        JTextArea appointmentsTextArea = new JTextArea();
        appointmentsTextArea.setEditable(false);
        
        //ESTABLISHES CONNECTION TO THE DB
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(appointmentsQuery)) {

            stmt.setString(1, doctorName);
            
            //WHILE THE DOCTORS NAME/USERNAME IS "umargul1122" GET ALL THE APPOINTMENTS ASSOCIATED WITH THE USERNAME
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String patientXNumber = rs.getString("PatientXNUMBER");
                String date = rs.getString("Date");
                String time = rs.getString("Time");
                
                //DISPLAY THE APPOINTMENT IN THE TEXTAREA
                appointmentsTextArea.append("Patient XNumber: " + patientXNumber + "\n");
                appointmentsTextArea.append("Date: " + date + "\n");
                appointmentsTextArea.append("Time: " + time + "\n");
                appointmentsTextArea.append("-----------------------\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error fetching appointments.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // ALLOWS THE DOCTOR TO SCROLL INCASE THERE ARE MULTIPLE APPOINTMENTS
        JScrollPane scrollPane = new JScrollPane(appointmentsTextArea);
        scrollPane.setBounds(33, 50, 400, 200);
        panel.add(scrollPane);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppointmentSystem("employee"));
    }
}
