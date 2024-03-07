package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.CrudOperations;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayNote extends JFrame {

    private JTable noteTable;
    private JButton calculateAverageButton;

    private CrudOperations crudOperations;

    public DisplayNote() {
        setTitle("Display Notes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create an instance of CrudOperations
        this.crudOperations = new CrudOperations();

        initUI();
    }

    private void initUI() {
        createTable();
        createCalculateAverageButton();
    }

    private void createTable() {
        DefaultTableModel model = new DefaultTableModel();
        noteTable = new JTable(model);

        model.addColumn("ID Note");
        model.addColumn("ID Etudiant");
        model.addColumn("ID Examen");
        model.addColumn("Note");

        // Fetch and display notes
        try {
            ResultSet resultSet = crudOperations.getNotesResultSet(); // Assume this method exists in CrudOperations
            while (resultSet.next()) {
                int idNote = resultSet.getInt("id");
                int idEtudiant = resultSet.getInt("idEtudiant");
                int idExamen = resultSet.getInt("idExamen");
                double note = resultSet.getDouble("note");

                model.addRow(new Object[]{idNote, idEtudiant, idExamen, note});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(noteTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createCalculateAverageButton() {
        calculateAverageButton = new JButton("Calculater la moyene");
        calculateAverageButton.addActionListener(e -> calculateAverage());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calculateAverageButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void calculateAverage() {
        DefaultTableModel model = (DefaultTableModel) noteTable.getModel();
        int rowCount = model.getRowCount();

        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "No notes to calculate average.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        double sum = 0;
        for (int i = 0; i < rowCount; i++) {
            sum += (double) model.getValueAt(i, 3); // Assuming the 'Note' column is at index 3
        }

        double average = sum / rowCount;
        JOptionPane.showMessageDialog(this, "moyenne des  Notes: " + average, "Average Calculation", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DisplayNote displayNoteFrame = new DisplayNote();
            displayNoteFrame.setVisible(true);
        });
    }
}

