package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import Model.CrudOperations;

public class ModifyEtudiant extends JFrame {

    private JTextField idField;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField ageField;
    private JTextField adresseField;
    private JTextField matriculeField;
    private JTextField emailField;

    public ModifyEtudiant() {
        setTitle("Modifier le Profil Etudiant");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();

        // Fetch Etudiant information from the database using CrudOperations
        String[] etudiantInfo = CrudOperations.displayDataById("Etudiants", 1); // Replace 1 with the actual Etudiant ID

        // Check if data is retrieved successfully
        if (etudiantInfo != null) {
            // Update text fields with Etudiant information
            updateFields(etudiantInfo);
        } else {
            // Handle the case where data retrieval fails
            showError("Failed to retrieve data for the Etudiant.");
        }
    }

    private void initUI() {
        createFields();
        createLabels();
        createPanel();
        createSaveButton();
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
            CrudOperations.updateEnseignantData("etudiants", id, nom, prenom, adresse, age, matricule, newEmail);
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

    private void updateFields(String[] etudiantInfo) {
        // Assuming ID is the first element in etudiantInfo
        idField.setText(etudiantInfo[0]);
        nomField.setText(etudiantInfo[1]);
        prenomField.setText(etudiantInfo[2]);
        ageField.setText(etudiantInfo[3]);
        adresseField.setText(etudiantInfo[4]);
        matriculeField.setText(etudiantInfo[5]);
        emailField.setText(etudiantInfo[6]);
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
