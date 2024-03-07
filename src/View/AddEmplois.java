package View;
import javax.swing.*;

import Model.CrudOperations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddEmplois extends JFrame {
    private JTextField enseignantField;
    private JTextField coursField;
    private JTextField jourSemaineField;
    private JTextField heureDebutField;
    private JTextField heureFinField;
    private JTextField salleField;
    private JButton addButton;
    private JButton fermerButton; // Added "Fermer" button

    public AddEmplois() {
        setTitle("Add Emplois");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2)); // Increased rows to accommodate "Fermer" button

        // Initialize components
        JLabel enseignantLabel = new JLabel("Enseignant:");
        enseignantField = new JTextField();
        JLabel coursLabel = new JLabel("Cours:");
        coursField = new JTextField();
        JLabel jourSemaineLabel = new JLabel("Jour de la semaine:");
        jourSemaineField = new JTextField();
        JLabel heureDebutLabel = new JLabel("Heure de début:");
        heureDebutField = new JTextField();
        JLabel heureFinLabel = new JLabel("Heure de fin:");
        heureFinField = new JTextField();
        JLabel salleLabel = new JLabel("Salle:");
        salleField = new JTextField();
        addButton = new JButton("Add");
        fermerButton = new JButton("Fermer"); // Added "Fermer" button

        // Add components to the frame
        add(enseignantLabel);
        add(enseignantField);
        add(coursLabel);
        add(coursField);
        add(jourSemaineLabel);
        add(jourSemaineField);
        add(heureDebutLabel);
        add(heureDebutField);
        add(heureFinLabel);
        add(heureFinField);
        add(salleLabel);
        add(salleField);
        add(addButton);
        add(fermerButton); // Added "Fermer" button

        // Add action listener to the "Add" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Call the method to add schedule to the database
                    CrudOperations.insertEmplois(
                            enseignantField.getText(),
                            coursField.getText(),
                            jourSemaineField.getText(),
                            heureDebutField.getText(),
                            heureFinField.getText(),
                            salleField.getText()
                    );
                    showSuccessMessage(); // Display success message
                    // Add any additional UI updates or messages if needed
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle the exception (e.g., show an error message)
                }
            }
        });

        // Add action listener to the "Fermer" button
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the window when the "Fermer" button is clicked
                dispose();
            }
        });
    }

    // Add this method to display a success message
    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(this, "Emplois du temps ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddEmplois().setVisible(true);
            }
        });
    }
}
