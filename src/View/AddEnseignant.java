package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import Model.CrudOperations;

public class AddEnseignant extends JFrame {

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField ageField;
    private JTextField adresseField;
    private JTextField anneeNaissanceField;
    private JTextField matriculeField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public AddEnseignant() {
        setTitle("Ajouter un Enseignant");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        createFields();
        createLabels();
        createPanel();
        createButtons();
    }

    private void createFields() {
        nomField = new JTextField();
        prenomField = new JTextField();
        ageField = new JTextField();
        adresseField = new JTextField();
        anneeNaissanceField = new JTextField();
        matriculeField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
    }

    private void createLabels() {
        JLabel nomLabel = new JLabel("Nom: ");
        JLabel prenomLabel = new JLabel("Prenom: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel adresseLabel = new JLabel("Adresse: ");
        JLabel anneeNaissanceLabel = new JLabel("Année de Naissance: ");
        JLabel matriculeLabel = new JLabel("Matricule: ");
        JLabel emailLabel = new JLabel("Email: ");
        JLabel passwordLabel = new JLabel("Mot de Passe: ");
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Nom: "));
        panel.add(nomField);
        panel.add(new JLabel("Prenom: "));
        panel.add(prenomField);
        panel.add(new JLabel("Age: "));
        panel.add(ageField);
        panel.add(new JLabel("Adresse: "));
        panel.add(adresseField);
        panel.add(new JLabel("Année de Naissance: "));
        panel.add(anneeNaissanceField);
        panel.add(new JLabel("Matricule: "));
        panel.add(matriculeField);
        panel.add(new JLabel("Email: "));
        panel.add(emailField);
        panel.add(new JLabel("Mot de Passe: "));
        panel.add(passwordField);
        add(panel);
    }

    private void createButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton addButton = new JButton("Ajouter Enseignant");
        addButton.addActionListener(e -> handleAddButtonClick());
        buttonPanel.add(addButton);

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> handleCloseButtonClick());
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleAddButtonClick() {
        try {
            // Get the information from text fields
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            int age = Integer.parseInt(ageField.getText());
            String adresse = adresseField.getText();
            int anneeNaissance = Integer.parseInt(anneeNaissanceField.getText());
            String matricule = matriculeField.getText();
            String email = emailField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            // Add Enseignant to the database
            CrudOperations.insertData("enseignants", nom, prenom, age, adresse, anneeNaissance, matricule, email, password);

            // Display a success message
            showInfo("Enseignant ajouté avec succès.");

            // Clear the text fields
            clearFields();
        } catch (NumberFormatException | SQLException ex) {
            // Handle errors such as invalid input or database insertion failure
            ex.printStackTrace();
            showError("Erreur lors de l'ajout de l'Enseignant à la base de données.");
        }
    }

    private void handleCloseButtonClick() {
        this.dispose();
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        nomField.setText("");
        prenomField.setText("");
        ageField.setText("");
        adresseField.setText("");
        anneeNaissanceField.setText("");
        matriculeField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddEnseignant().setVisible(true));
    }
}
