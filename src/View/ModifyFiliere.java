package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import Model.CrudOperations;

public class ModifyFiliere extends JFrame {

    private JTextField idField;
    private JTextField nomFiliereField;

    public ModifyFiliere() throws SQLException {
        setTitle("Modifier la Filiere");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();

        // Fetch Filiere information from the database using CrudOperations
        String[] filiereInfo = CrudOperations.displayFiliereDataById(1); // Replace 1 with the actual Filiere ID

        // Check if data is retrieved successfully
        if (filiereInfo != null) {
            // Update text fields with Filiere information
            updateFields(filiereInfo);
        } else {
            // Handle the case where data retrieval fails
            showError("Failed to retrieve data for the Filiere.");
        }
    }

    private void initUI() {
        createFields();
        createLabels();
        createPanel();
        createSaveButton();
        createFermerButton(); // Added this line to create "Fermer" button
    }

    private void createFields() {
        idField = new JTextField();
        nomFiliereField = new JTextField();
    }

    private void createLabels() {
        JLabel idLabel = new JLabel("ID: ");
        JLabel nomFiliereLabel = new JLabel("Nom de la Filiere: ");
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("ID: "));
        panel.add(idField);
        panel.add(new JLabel("Nom de la Filiere: "));
        panel.add(nomFiliereField);
        add(panel);
    }

    private void createSaveButton() {
        JButton saveButton = new JButton("Enregistrer les modifications");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveButtonClick();
            }
        });

        add(saveButton, BorderLayout.SOUTH);
    }

    private void createFermerButton() {
        JButton fermerButton = new JButton("Fermer");
        fermerButton.addActionListener(e -> dispose()); // Close the window when the button is clicked

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(fermerButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleSaveButtonClick() {
        try {
            // Get the modified information from text fields
            int id = Integer.parseInt(idField.getText());
            String newNomFiliere = nomFiliereField.getText();

            // Update the database with the modified information
            CrudOperations.updateFiliereData(id, newNomFiliere);

            // Display a success message
            showInfo("Modifications enregistrées avec succès.");

            // Close the modification window
            dispose();
        } catch (NumberFormatException | SQLException ex) {
            // Handle errors such as invalid input or database update failure
            ex.printStackTrace();
            showError("Erreur lors de l'enregistrement des modifications.");
        }
    }

    private void updateFields(String[] filiereInfo) {
        // Assuming ID is the first element in filiereInfo
        idField.setText(filiereInfo[0]);
        nomFiliereField.setText(filiereInfo[1]);
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
