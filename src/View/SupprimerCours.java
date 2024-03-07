package View;

import javax.swing.*;
import Model.CrudOperations;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SupprimerCours extends JFrame {

    public SupprimerCours() {
        JFrame frame = new JFrame("Delete Course");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Populate the course dropdown with existing courses
        JComboBox<String> courseDropdown = new JComboBox<>();
        try {
            List<String> courses = CrudOperations.getCourses();
            for (String course : courses) {
                courseDropdown.addItem(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        panel.add(new JLabel("Select Course:"));
        panel.add(courseDropdown);

        JButton deleteButton = new JButton("Delete Course");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 14));
        deleteButton.addActionListener(e -> {
            try {
                String selectedCourse = (String) courseDropdown.getSelectedItem();

                // Assuming you need to implement a method like deleteCourseData
                CrudOperations.deleteCourseData(selectedCourse);
                JOptionPane.showMessageDialog(frame, "Course deleted successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error deleting course.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton fermerButton = new JButton("Fermer");
        fermerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fermerButton.addActionListener(e -> dispose()); // Close the window when the button is clicked

        panel.add(deleteButton);
        panel.add(fermerButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }
}
