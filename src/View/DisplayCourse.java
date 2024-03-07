package View;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

import Model.CrudOperations;

public class DisplayCourse extends JFrame {

    public DisplayCourse() {
        JFrame frame = new JFrame("Display Courses");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        try {
            // Retrieve the list of courses with their corresponding teachers and filieres
            List<CrudOperations.CourseInfo> coursesInfo = CrudOperations.getCoursesWithDetails();

            // Check if there are any courses
            if (!coursesInfo.isEmpty()) {
                // Create a table to display course information
                String[] columnNames = {"Course", "Teacher", "Filiere"};
                Object[][] data = new Object[coursesInfo.size()][columnNames.length];

                for (int i = 0; i < coursesInfo.size(); i++) {
                    CrudOperations.CourseInfo courseInfo = coursesInfo.get(i);
                    data[i][0] = courseInfo.getCourseName();
                    data[i][1] = courseInfo.getTeacherName();
                    data[i][2] = courseInfo.getFiliereName();
                }

                JTable table = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);

                panel.add(scrollPane, BorderLayout.CENTER);
            } else {
                // Display a message if there are no courses
                JLabel noCoursesLabel = new JLabel("No courses available.");
                panel.add(noCoursesLabel, BorderLayout.CENTER);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add "Fermer" button
        JButton fermerButton = new JButton("Fermer");
        fermerButton.addActionListener(e -> frame.dispose()); // Close the window when the button is clicked
        fermerButton.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(fermerButton);

        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
