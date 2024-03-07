package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import Model.CrudOperations;

public class DeleteExam extends JFrame {

    private JComboBox<String> courseComboBox;

    public DeleteExam() {
        setTitle("Delete Exam");
        setSize(300, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        createComponents();
        createPanel();
        createDeleteButton();
    }

    private void createComponents() {
        // Fetch the list of courses from the database
        // You can modify the method in CrudOperations to return the course list
        String[] courseList = {"Course1", "Course2", "Course3"}; // Replace with actual course names
        courseComboBox = new JComboBox<>(courseList);
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Select Course:"));
        panel.add(courseComboBox);
        add(panel);
    }

    private void createDeleteButton() {
        JButton deleteButton = new JButton("Delete Exam");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 14));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeleteButtonClick();
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleDeleteButtonClick() {
        try {
            String selectedCourse = (String) courseComboBox.getSelectedItem();
            CrudOperations.deleteExamsByCourse(selectedCourse);
            showInfo("Exams deleted successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            showError("Error deleting exams.");
        }
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
