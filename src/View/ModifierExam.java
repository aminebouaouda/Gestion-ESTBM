package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import Model.CrudOperations;

public class ModifierExam extends JFrame {

    private JComboBox<String> examListComboBox;
    private JTextField modifiedDateField;

    public ModifierExam() {
        setTitle("Modify an Exam");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        createFields();
        createLabels();
        createPanel();
        createModifyButton();
    }

    private void createFields() {
        // Initialize the JComboBox with exams
        try {
            ArrayList<String> examList = CrudOperations.getExamList(); // Assuming this method is implemented in CrudOperations
            examListComboBox = new JComboBox<>(examList.toArray(new String[0]));
        } catch (SQLException e) {
            e.printStackTrace();
            showError("Error fetching exam list from the database.");
        }

        modifiedDateField = new JTextField();
    }

    private void createLabels() {
        JLabel examListLabel = new JLabel("Select Exam: ");
        JLabel modifiedDateLabel = new JLabel("Modified Exam Date (yyyy-MM-dd): ");
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Select Exam: "));
        panel.add(examListComboBox);
        panel.add(new JLabel("Modified Exam Date (yyyy-MM-dd): "));
        panel.add(modifiedDateField);
        add(panel);
    }

    private void createModifyButton() {
        JButton modifyButton = new JButton("Modify Exam");
        modifyButton.setFont(new Font("Arial", Font.PLAIN, 14));
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleModifyButtonClick();
            }
        });

        JButton fermerButton = new JButton("Close");
        fermerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fermerButton.addActionListener(e -> dispose()); // Close the window when the button is clicked

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(modifyButton);
        buttonPanel.add(fermerButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleModifyButtonClick() {
        try {
            // Get the selected exam from the JComboBox
            String selectedExam = (String) examListComboBox.getSelectedItem();

            // Parse the modified date string using SimpleDateFormat
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String modifiedDateInput = modifiedDateField.getText();
            Date modifiedDate = new Date(dateFormat.parse(modifiedDateInput).getTime());

            // Modify Exam in the database
            CrudOperations.modifyExamDate(selectedExam, modifiedDate);

            // Display a success message
            showInfo("Exam modified successfully.");

            // Clear the text fields
            clearFields();
        } catch (ParseException ex) {
            // Handle date parsing error
            ex.printStackTrace();
            showError("Error parsing the modified date. Please enter a valid date in the format yyyy-MM-dd.");
        } catch (SQLException ex) {
            // Handle errors such as database modification failure
            ex.printStackTrace();
            showError("Error modifying Exam in the database.");
        }
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        examListComboBox.setSelectedIndex(0); // Set the default selection (adjust as needed)
        modifiedDateField.setText("");
    }
}
