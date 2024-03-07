package View;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.CrudOperations;

public class modifierCours extends JFrame {

    public modifierCours() {
        JFrame frame = new JFrame("Modify Course");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JComboBox<String> courseDropdown = new JComboBox<>();
        try {
            List<String> courses = CrudOperations.getCourses(); // Assuming you have a method to get the list of courses
            for (String course : courses) {
                courseDropdown.addItem(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTextField newCourseNameField = new JTextField();

        List<CrudOperations.Teacher> teachers = new ArrayList<>();
        try {
            teachers = CrudOperations.getTeachers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JComboBox<CrudOperations.Teacher> teacherDropdown = new JComboBox<>(teachers.toArray(new CrudOperations.Teacher[0]));

        List<CrudOperations.Filiere> filieres = new ArrayList<>();
        try {
            filieres = CrudOperations.getFilieres();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JComboBox<CrudOperations.Filiere> filiereDropdown = new JComboBox<>(filieres.toArray(new CrudOperations.Filiere[0]));

        panel.add(new JLabel("Select Course:"));
        panel.add(courseDropdown);
        panel.add(new JLabel("New Course Name:"));
        panel.add(newCourseNameField);
        panel.add(new JLabel("Select New Teacher:"));
        panel.add(teacherDropdown);
        panel.add(new JLabel("Select New Filiere:"));
        panel.add(filiereDropdown);

        JButton modifyButton = new JButton("Modify Course");
        modifyButton.setFont(new Font("Arial", Font.PLAIN, 14));
        modifyButton.addActionListener(e -> {
            try {
                String selectedCourse = (String) courseDropdown.getSelectedItem();
                String newCourseName = newCourseNameField.getText();
                CrudOperations.Teacher newTeacher = (CrudOperations.Teacher) teacherDropdown.getSelectedItem();
                CrudOperations.Filiere newFiliere = (CrudOperations.Filiere) filiereDropdown.getSelectedItem();

                // Assuming you need to implement a method like updateCourseData
                CrudOperations.updateCourseData(selectedCourse, newCourseName, newTeacher.getId(), newFiliere.getId());
                JOptionPane.showMessageDialog(frame, "Course modified successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error modifying course.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton fermerButton = new JButton("Fermer");
        fermerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fermerButton.addActionListener(e -> dispose()); // Close the window when the button is clicked

        panel.add(modifyButton);
        panel.add(fermerButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
