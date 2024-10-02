import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class showBillingInformation {
    private JFrame frame;

    public showBillingInformation() {
        frame = new JFrame("Billing Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());  

        String[] columnNames = {"Bill ID", "Co Pay", "Reason", "Due Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Bill> bills = getExampleBillingInformation();

        for (Bill bill : bills) {
            model.addRow(new Object[]{bill.billId, bill.amount, bill.reason, bill.dueDate});
        }

        JTable table = new JTable(model); 
        JScrollPane scrollPane = new JScrollPane(table); 

        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);  

        frame.setVisible(true); 
    }

    private static class Bill {
        int billId;
        double amount;
        String reason;
        String dueDate;

        Bill(int billId, double amount, String reason, String dueDate) {
            this.billId = billId;
            this.amount = amount;
            this.reason = reason;
            this.dueDate = dueDate;
        }
    }

    private List<Bill> getExampleBillingInformation() {
        List<Bill> bills = new ArrayList<>();

        bills.add(new Bill(1, 150.00, "Consultation Fee", "01-05-2024"));
        bills.add(new Bill(2, 75.50, "Lab Test", "05-15-2024"));
        bills.add(new Bill(3, 200.75, "Medication Charge", "01-06-2024"));

        return bills;
    }
}
