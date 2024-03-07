package View;

import Model.CrudOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModifiyEnseignant extends JFrame {

    private JTextField idField;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField ageField;
    private JTextField adresseField;
    private JTextField matriculeField;
    private JTextField emailField;

    public ModifiyEnseignant() {
        setTitle("Modifier le Profil Enseignant");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();

        // Fetch enseignant information from the database using CrudOperations
        // You may need to adjust how you retrieve the enseignant's information based on your system
        // For now, I assume you have a method like CrudOperations.displayDataById
        String[] enseignantInfo = CrudOperations.displayDataById("Enseignants", 1); // Replace 1 with the actual Enseignant ID

        // Check if data is retrieved successfully
        if (enseignantInfo != null) {
            // Update text fields with enseignant information
            updateFields(enseignantInfo);
        } else {
            // Handle the case where data retrieval fails
            showError("Failed to retrieve data for the enseignant.");
        }
    }

    private void initUI() {
        createFields();
        createLabels();
        createPanel();
        createButtons();
    }

    private void createFields() {
        idField = new JTextField();
        nomField = new JTextField();
        prenomField = new JTextField();
        ageField = new JTextField();
        adresseField = new JTextField();
        matriculeField = new JTextField();
        emailField = new JTextField();
    }

    private void createLabels() {
        JLabel idLabel = new JLabel("ID: ");
        JLabel nomLabel = new JLabel("Nom: ");
        JLabel prenomLabel = new JLabel("Prenom: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel adresseLabel = new JLabel("Adresse: ");
        JLabel matriculeLabel = new JLabel("Matricule: ");
        JLabel emailLabel = new JLabel("Email: ");
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("ID: "));
        panel.add(idField);
        panel.add(new JLabel("Nom: "));
        panel.add(nomField);
        panel.add(new JLabel("Prenom: "));
        panel.add(prenomField);
        panel.add(new JLabel("Age: "));
        panel.add(ageField);
        panel.add(new JLabel("Adresse: "));
        panel.add(adresseField);
        panel.add(new JLabel("Matricule: "));
        panel.add(matriculeField);
        panel.add(new JLabel("Email: "));
        panel.add(emailField);
        add(panel);
    }

    private void createButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton saveButton = new JButton("Enregistrer les modifications");
        saveButton.addActionListener(e -> handleSaveButtonClick());
        buttonPanel.add(saveButton);

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> handleCloseButtonClick());
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleSaveButtonClick() {
        try {
            // Get the modified information from text fields
            int id = Integer.parseInt(idField.getText());
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            int age = Integer.parseInt(ageField.getText());
            String adresse = adresseField.getText();
            String matricule = matriculeField.getText();
            String newEmail = emailField.getText();

            // Update the database with the modified information
            CrudOperations.updateEnseignantData("enseignants", id, nom, prenom, adresse, age, matricule, newEmail);
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

    private void handleCloseButtonClick() {
        this.dispose();
    }

    private void updateFields(String[] enseignantInfo) {
        // Assuming ID is the first element in enseignantInfo
        idField.setText(enseignantInfo[0]);
        nomField.setText(enseignantInfo[1]);
        prenomField.setText(enseignantInfo[2]);
        ageField.setText(enseignantInfo[3]);
        adresseField.setText(enseignantInfo[4]);
        matriculeField.setText(enseignantInfo[5]);
        emailField.setText(enseignantInfo[6]);
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModifiyEnseignant().setVisible(true));
    }
}
