package View;

import Model.CrudOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteEtudiant extends JFrame {

    private JTextField idField;

    public DeleteEtudiant() {
        setTitle("Supprimer un Etudiant");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set the default close operation
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
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("ID: "));
        panel.add(idField);
        add(panel);
    }

    private void createButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton deleteButton = new JButton("Supprimer l'Etudiant");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeleteButtonClick();
            }
        });
        buttonPanel.add(deleteButton);

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCloseButtonClick();
            }
        });
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleDeleteButtonClick() {
        try {
            // Get the Etudiant ID to delete
            int id = Integer.parseInt(idField.getText());

            // Delete the Etudiant from the database
            CrudOperations.deleteData("Etudiants", id);

            // Display a success message
            showInfo("Etudiant supprimé avec succès.");

            // Close the deletion window
            dispose();
        } catch (NumberFormatException | SQLException ex) {
            // Handle errors such as invalid input or database deletion failure
            ex.printStackTrace();
            showError("Erreur lors de la suppression de l'Etudiant.");
        }
    }

    private void handleCloseButtonClick() {
        this.dispose(); // Close the frame when the "Fermer" button is clicked
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeleteEtudiant().setVisible(true));
    }
}
