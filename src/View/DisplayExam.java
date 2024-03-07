package View;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.CrudOperations;

public class DisplayExam extends JFrame {

    private JTable examTable;

    public DisplayExam() {
        setTitle("Display Exams");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        createTable();
        createScrollPane();
    }

    private void createTable() {
        try {
            // Fetch the list of exams from the database
            // You can modify the method in CrudOperations to return the exam list
            ArrayList<String> examList = CrudOperations.getExamList();
            String[][] data = new String[examList.size()][1];

            for (int i = 0; i < examList.size(); i++) {
                data[i][0] = examList.get(i);
            }

            String[] columnNames = {"Exam Name"};

            examTable = new JTable(data, columnNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(examTable);
        add(scrollPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DisplayExam displayExam = new DisplayExam();
            displayExam.setVisible(true);
        });
    }
}
