package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Model.CrudOperations;

public class DeleteNote extends JFrame {

    private JTextField idNoteField;

    private CrudOperations crudOperations;

    // Default constructor for CrudOperations
    public DeleteNote() {
        this.crudOperations = new CrudOperations(); // You may need to initialize it according to your needs

        setTitle("Supprimer une Note");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createFields();
        createLabels();
        createPanel();
        createDeleteButton();

        setVisible(true); // Set the frame visible
    }

    private void createFields() {
        idNoteField = new JTextField();
    }

    private void createLabels() {
        JLabel idNoteLabel = new JLabel("ID Note à supprimer : ");
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("ID Note à supprimer : "));
        panel.add(idNoteField);
        add(panel);
    }

    private void createDeleteButton() {
        JButton deleteButton = new JButton("Supprimer Note");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 14));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    handleDeleteButtonClick();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton fermerButton = new JButton("Fermer");
        fermerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fermerButton.addActionListener(e -> dispose()); // Fermer la fenêtre lorsque le bouton est cliqué

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(deleteButton);
        buttonPanel.add(fermerButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleDeleteButtonClick() throws SQLException {
        try {
            // Get the information from text fields
            int idNote = Integer.parseInt(idNoteField.getText());

            // Supprimer la note de la base de données
            crudOperations.deleteNote(idNote);

            // Afficher un message de succès
            showInfo("Note supprimée avec succès.");

            // Effacer les champs de texte
            clearFields();
        } catch (NumberFormatException ex) {
            // Gérer l'erreur de format de nombre
            ex.printStackTrace();
            showError("Entrée invalide. Veuillez entrer un nombre valide.");
        }
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        idNoteField.setText("");
    }
}
