package View;
import javax.swing.*;

import Model.CrudOperations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteEmplois extends JFrame {
    private JTextField emploisIdField;
    private JButton deleteButton;
    private JButton closeButton;

    public DeleteEmplois() {
        setTitle("Supprimer Emplois");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // Initialize components
        JLabel emploisIdLabel = new JLabel("ID de l'Emplois:");
        emploisIdField = new JTextField();
        deleteButton = new JButton("Supprimer");
        closeButton = new JButton("Fermer");

        // Add components to the frame
        add(emploisIdLabel);
        add(emploisIdField);
        add(deleteButton);
        add(closeButton);

        // Add action listener to the "Supprimer" button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Call the method to delete schedule from the database
                    int emploisId = Integer.parseInt(emploisIdField.getText());
                    CrudOperations.deleteEmplois(emploisId);

                    // Display success message
                    JOptionPane.showMessageDialog(DeleteEmplois.this, "Emplois supprimé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Clear fields after successful deletion
                    clearFields();
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    // Handle the exception (e.g., show an error message)
                    JOptionPane.showMessageDialog(DeleteEmplois.this, "Erreur lors de la suppression de l'Emplois.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add action listener to the "Fermer" button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the DeleteEmplois window
                dispose();
            }
        });
    }

    private void clearFields() {
        emploisIdField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DeleteEmplois().setVisible(true);
            }
        });
    }
}
