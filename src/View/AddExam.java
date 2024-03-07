package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Model.CrudOperations;

public class AddExam extends JFrame {

    private JTextField selectedCourseField;
    private JTextField dateExamenField;

    public AddExam() {
        setTitle("Add an Exam");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        createFields();
        createLabels();
        createPanel();
        createAddButton();
    }

    private void createFields() {
        selectedCourseField = new JTextField();
        dateExamenField = new JTextField();
    }

    private void createLabels() {
        JLabel selectedCourseLabel = new JLabel("Selected Course: ");
        JLabel dateExamenLabel = new JLabel("Exam Date (yyyy-MM-dd): ");
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Selected Course: "));
        panel.add(selectedCourseField);
        panel.add(new JLabel("Exam Date (yyyy-MM-dd): "));
        panel.add(dateExamenField);
        add(panel);
    }

    private void createAddButton() {
        JButton addButton = new JButton("Add Exam");
        addButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddButtonClick();
            }
        });

        JButton fermerButton = new JButton("Close");
        fermerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fermerButton.addActionListener(e -> dispose()); // Close the window when the button is clicked

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(fermerButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleAddButtonClick() {
        try {
            // Get the information from text fields
            String selectedCourse = selectedCourseField.getText();

            // Parse the date string using SimpleDateFormat
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateInput = dateExamenField.getText();
            Date dateExamen = new Date(dateFormat.parse(dateInput).getTime());

            // Add Exam to the database and associate it with the course
            CrudOperations.insertExamAndAssociateWithCourse(selectedCourse, dateExamen);

            // Display a success message
            showInfo("Exam added successfully.");

            // Clear the text fields
            clearFields();
        } catch (ParseException ex) {
            // Handle date parsing error
            ex.printStackTrace();
            showError("Error parsing the date. Please enter a valid date in the format yyyy-MM-dd.");
        } catch (SQLException ex) {
            // Handle errors such as database insertion failure
            ex.printStackTrace();
            showError("Error adding Exam to the database.");
        }
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        selectedCourseField.setText("");
        dateExamenField.setText("");
    }
}
