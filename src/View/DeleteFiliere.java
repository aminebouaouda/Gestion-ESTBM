package View;

import Model.CrudOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteFiliere extends JFrame {

    private JTextField idField;

    public DeleteFiliere() {
        setTitle("Supprimer une Filiere");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        createFields();
        createPanel();
        createDeleteButton();
    }

    private void createFields() {
        idField = new JTextField();
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        // Add label and text field for ID
        JLabel idLabel = new JLabel("ID: ");
        panel.add(idLabel);

        panel.add(idField);

        add(panel, BorderLayout.CENTER);
    }

    private void createDeleteButton() {
        JButton deleteButton = new JButton("Supprimer la Filiere");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeleteButtonClick();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout()); // Use FlowLayout for the buttons
        buttonPanel.add(deleteButton);
        buttonPanel.add(createFermerButton()); // Add the "Fermer" button here

        add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the south
    }

    private JButton createFermerButton() {
        JButton fermerButton = new JButton("Fermer");
        fermerButton.addActionListener(e -> dispose());

        return fermerButton;
    }

    private void handleDeleteButtonClick() {
        try {
            int id = Integer.parseInt(idField.getText());
            CrudOperations.deleteFiliereData(id);
            showInfo("Filiere supprimée avec succès.");
            dispose();
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            showError("Erreur lors de la suppression de la Filiere.");
        }
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DeleteFiliere().setVisible(true);
            }
        });
    }
}
