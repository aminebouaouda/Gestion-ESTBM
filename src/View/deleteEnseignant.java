package View;

import Model.CrudOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class deleteEnseignant extends JFrame {

    private JTextField idField;

    public deleteEnseignant() {
        setTitle("Supprimer un Enseignant");
        setSize(300, 150);
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
        idField = new JTextField();
    }

    private void createLabels() {
        JLabel idLabel = new JLabel("ID: ");
        // You can customize the label properties here if needed
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("ID: "));
        panel.add(idField);
        add(panel);
    }

    private void createButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton deleteButton = new JButton("Supprimer l'Enseignant");
        deleteButton.addActionListener(e -> handleDeleteButtonClick());
        buttonPanel.add(deleteButton);

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> handleCloseButtonClick());
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleDeleteButtonClick() {
        try {
            // Get the Enseignant ID to delete
            int id = Integer.parseInt(idField.getText());

            // Delete the Enseignant from the database
            CrudOperations.deleteData("Enseignants", id);

            // Display a success message
            showInfo("Enseignant supprimé avec succès.");

            // Close the deletion window
            dispose();
        } catch (NumberFormatException | SQLException ex) {
            // Handle errors such as invalid input or database deletion failure
            ex.printStackTrace();
            showError("Erreur lors de la suppression de l'Enseignant.");
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


}
