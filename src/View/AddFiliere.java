package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import Model.CrudOperations;

public class AddFiliere extends JFrame {

    private JTextField nomFiliereField;

    public AddFiliere() {
        setTitle("Ajouter une Filiere");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        createFields();
        createLabels();
        createPanel();
        createAddButton();
    }

    private void createFields() {
        nomFiliereField = new JTextField();
    }

    private void createLabels() {
        JLabel nomFiliereLabel = new JLabel("Nom de la Filiere: ");
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Nom de la Filiere: "));
        panel.add(nomFiliereField);
        add(panel);
    }

    private void createAddButton() {
        JButton addButton = new JButton("Ajouter Filiere");
        addButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddButtonClick();
            }
        });

        JButton fermerButton = new JButton("Fermer");
        fermerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fermerButton.addActionListener(e -> dispose()); // Close the window when the button is clicked

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(fermerButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleAddButtonClick() {
        try {
            // Get the information from text fields
            String nomFiliere = nomFiliereField.getText();

            // Add Filiere to the database
            CrudOperations.insertFiliereData(nomFiliere);

            // Display a success message
            showInfo("Filiere ajoutée avec succès.");

            // Clear the text fields
            clearFields();
        } catch (SQLException ex) {
            // Handle errors such as database insertion failure
            ex.printStackTrace();
            showError("Erreur lors de l'ajout de la Filiere à la base de données.");
        }
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        nomFiliereField.setText("");
    }
}
