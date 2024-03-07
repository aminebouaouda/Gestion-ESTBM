package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import Model.CrudOperations;

public class AdminProfileModifier extends JFrame {

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField ageField;
    private JTextField adresseField;
    private JTextField matriculeField;
    private JTextField emailField;
    
private String username ;
    public AdminProfileModifier(String username) {
    	this.username=username;
        setTitle("Modifier le Profil Administrateur");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        
        // Fetch administrator information from the database using CrudOperations
        String[] adminInfo = CrudOperations.displayData("Administrateurs", username);

        // Check if data is retrieved successfully
        if (adminInfo != null) {
            // Update text fields with administrator information
            updateFields(adminInfo);
        } else {
            // Handle the case where data retrieval fails
            showError("Failed to retrieve data for the administrator.");
        }
    }
    

    private void initUI() {
        createFields();
        createLabels();
        createPanel();
        createSaveButton();
    }

    private void createFields() {
        nomField = new JTextField();
        prenomField = new JTextField();
        ageField = new JTextField();
        adresseField = new JTextField();
        matriculeField = new JTextField();
        emailField = new JTextField();
    }

    private void createLabels() {
        JLabel nomLabel = new JLabel("Nom: ");
        JLabel prenomLabel = new JLabel("Prenom: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel adresseLabel = new JLabel("Adresse: ");
        JLabel matriculeLabel = new JLabel("Matricule: ");
        JLabel emailLabel = new JLabel("Email: ");
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 2));
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
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            int age = Integer.parseInt(ageField.getText());
            String adresse = adresseField.getText();
            String matricule = matriculeField.getText();
            String newEmail = emailField.getText();

            // Update the database with the modified information
            CrudOperations.updateData("Administrateurs", username, nom, prenom, adresse, age, matricule, newEmail);
//updateData(String tableName, String username, String nom, String prenom, String adresse, int age, int anneeNaissance, String matricule, String email)
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

    private void updateFields(String[] adminInfo) {
        nomField.setText(adminInfo[0]);
        prenomField.setText(adminInfo[1]);
        ageField.setText(adminInfo[2]);
        adresseField.setText(adminInfo[3]);
        matriculeField.setText(adminInfo[4]);
        emailField.setText(adminInfo[5]);
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

 
}
