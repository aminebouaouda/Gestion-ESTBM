package View;

import Model.CrudOperations;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class DisplayEtudiant extends JFrame {

    private JTable etudiantTable;

    public DisplayEtudiant() {
        setTitle("Liste des Etudiants");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        loadEtudiants(); // Load Etudiants in the constructor
    }

    private void initUI() {
        createTable();
        createScrollPane();
        createPanel();
        createFermerButton();
    }

    private void createTable() {
        etudiantTable = new JTable();
        etudiantTable.setModel(new DefaultTableModel(
                new Object[]{"ID", "Nom", "Prenom", "Age", "Adresse", "Annee Naissance", "Matricule", "Email"},
                0));
        etudiantTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        etudiantTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(etudiantTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Etudiants:"), BorderLayout.NORTH);
        panel.add(etudiantTable.getTableHeader(), BorderLayout.PAGE_START);
        panel.add(etudiantTable, BorderLayout.CENTER);
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

    private void loadEtudiants() {
        try {
            // Get the list of Etudiants from the database
            List<CrudOperations.Student> etudiants = CrudOperations.getStudents();

            // Display Etudiants in the table
            displayEtudiants(etudiants);
        } catch (SQLException e) {
            e.printStackTrace();
            showError("Erreur lors du chargement des Etudiants depuis la base de données.");
        }
    }

    private void displayEtudiants(List<CrudOperations.Student> etudiants) {
        DefaultTableModel model = (DefaultTableModel) etudiantTable.getModel();
        model.setRowCount(0); // Clear the table before displaying new data

        if (etudiants != null && !etudiants.isEmpty()) {
            for (CrudOperations.Student etudiant : etudiants) {
                model.addRow(new Object[]{
                        etudiant.getId(),
                        etudiant.getNom(),
                        etudiant.getPrenom(),
                        etudiant.getAge(),
                        etudiant.getAdresse(),
                        etudiant.getAnneeNaissance(),
                        etudiant.getMatricule(),
                        etudiant.getEmail()
                });
            }
        } else {
            showError("Aucun Etudiant trouvé dans la base de données.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
