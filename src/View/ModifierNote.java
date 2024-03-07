package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Model.CrudOperations;

public class ModifierNote extends JFrame {

    private JTextField idNoteField;
    private JTextField newNoteField;

    private CrudOperations crudOperations;

    // Default constructor for CrudOperations
    public ModifierNote() {
        this.crudOperations = new CrudOperations(); // You may need to initialize it according to your needs

        setTitle("Modifier une Note");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createFields();
        createLabels();
        createPanel();
        createModifyButton();

        setVisible(true); // Set the frame visible
    }

    private void createFields() {
        idNoteField = new JTextField();
        newNoteField = new JTextField();
    }

    private void createLabels() {
        JLabel idNoteLabel = new JLabel("ID Note à modifier : ");
        JLabel newNoteLabel = new JLabel("Nouvelle Note : ");
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("ID Note à modifier : "));
        panel.add(idNoteField);
        panel.add(new JLabel("Nouvelle Note : "));
        panel.add(newNoteField);
        add(panel);
    }

    private void createModifyButton() {
        JButton modifyButton = new JButton("Modifier Note");
        modifyButton.setFont(new Font("Arial", Font.PLAIN, 14));
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleModifyButtonClick();
            }
        });

        JButton fermerButton = new JButton("Close");
        fermerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fermerButton.addActionListener(e -> dispose()); // Fermer la fenêtre lorsque le bouton est cliqué

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(modifyButton);
        buttonPanel.add(fermerButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleModifyButtonClick() {
        try {
            // Get the information from text fields
            int idNote = Integer.parseInt(idNoteField.getText());
            double newNote = Double.parseDouble(newNoteField.getText());

            // Modifier la note dans la base de données
            crudOperations.modifyNote(idNote, newNote);

            // Afficher un message de succès
            showInfo("Note modifiée avec succès.");

            // Effacer les champs de texte
            clearFields();
        } catch (NumberFormatException ex) {
            // Gérer l'erreur de format de nombre
            ex.printStackTrace();
            showError("Entrée invalide. Veuillez entrer des nombres valides.");
        } catch (SQLException ex) {
            // Gérer les erreurs telles que l'échec de la modification dans la base de données
            ex.printStackTrace();
            showError("Erreur lors de la modification de la note dans la base de données.");
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
        newNoteField.setText("");
    }
}
