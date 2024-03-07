package View;

import Model.CrudOperations;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class DisplayEnseignant extends JFrame {

    private JTable enseignantTable;

    public DisplayEnseignant() {
        setTitle("Liste des Enseignants");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        loadEnseignants(); // Load Enseignants in the constructor
    }

    private void initUI() {
        createTable();
        createScrollPane();
        createPanel();
        createFermerButton();
    }

    private void createTable() {
        enseignantTable = new JTable();
        enseignantTable.setModel(new DefaultTableModel(
                new Object[]{"ID", "Nom", "Prenom", "Age", "Adresse", "Annee Naissance", "Matricule", "Email"},
                0));
        enseignantTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        enseignantTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(enseignantTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Enseignants:"), BorderLayout.NORTH);
        panel.add(enseignantTable.getTableHeader(), BorderLayout.PAGE_START);
        panel.add(enseignantTable, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }

    private void createFermerButton() {
        JButton fermerButton = new JButton("Fermer");
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
            }
        });

        add(fermerButton, BorderLayout.SOUTH);
    }

    private void loadEnseignants() {
        try {
            // Get the list of Enseignants from the database
            List<CrudOperations.Teacher> enseignants = CrudOperations.getTeachers1();

            // Display Enseignants in the table
            displayEnseignants(enseignants);
        } catch (SQLException e) {
            e.printStackTrace();
            showError("Erreur lors du chargement des Enseignants depuis la base de données.");
        }
    }

    private void displayEnseignants(List<CrudOperations.Teacher> enseignants) {
        DefaultTableModel model = (DefaultTableModel) enseignantTable.getModel();
        model.setRowCount(0); // Clear the table before displaying new data

        if (enseignants != null && !enseignants.isEmpty()) {
            for (CrudOperations.Teacher enseignant : enseignants) {
                model.addRow(new Object[]{
                        enseignant.getId(),
                        enseignant.getNom(),
                        enseignant.getPrenom(),
                        enseignant.getAge(),
                        enseignant.getAdresse(),
                        enseignant.getAnneeNaissance(),
                        enseignant.getMatricule(),
                        enseignant.getEmail()
                });
            }
        } else {
            showError("Aucun Enseignant trouvé dans la base de données.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
