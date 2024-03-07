package View;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.CrudOperations;

public class AjouterCours extends JFrame {
    private JTextField courseField;
    private JComboBox<CrudOperations.Teacher> teacherDropdown;
    private JComboBox<CrudOperations.Filiere> filiereDropdown;

    public AjouterCours() {
        setTitle("Add Course");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        courseField = new JTextField();

        // Add a dropdown list for selecting the teacher
        List<CrudOperations.Teacher> teachers = new ArrayList<>();
        try {
            teachers = CrudOperations.getTeachers1();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teacherDropdown = new JComboBox<>(teachers.toArray(new CrudOperations.Teacher[0]));

        // Add a dropdown list for selecting the filiere
        List<CrudOperations.Filiere> filieres = new ArrayList<>();
        try {
            filieres = CrudOperations.getFilieres();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        filiereDropdown = new JComboBox<>(filieres.toArray(new CrudOperations.Filiere[0]));

        panel.add(new JLabel("Course Name:"));
        panel.add(courseField);
        panel.add(new JLabel("Select Teacher:"));
        panel.add(teacherDropdown);
        panel.add(new JLabel("Select Filiere:"));
        panel.add(filiereDropdown);

        JButton addButton = new JButton("Add Course");
        addButton.setFont(new Font("Arial", Font.PLAIN, 14));

        addButton.addActionListener(e -> {
            try {
                String courseName = courseField.getText();
                CrudOperations.Teacher selectedTeacher = (CrudOperations.Teacher) teacherDropdown.getSelectedItem();
                CrudOperations.Filiere selectedFiliere = (CrudOperations.Filiere) filiereDropdown.getSelectedItem();

                CrudOperations.insertCourseData(courseName, selectedTeacher.getId(), selectedFiliere.getId());
                JOptionPane.showMessageDialog(null, "Course added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding course.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton fermerButton = new JButton("Fermer");
        fermerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fermerButton.addActionListener(e -> dispose()); // Close the window when the button is clicked

        panel.add(addButton);
        panel.add(fermerButton);

        getContentPane().add(BorderLayout.CENTER, panel);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AjouterCours());
    }
}
