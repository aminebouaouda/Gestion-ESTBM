package View;

import javax.swing.*;
import Model.CrudOperations;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModifierEmplois extends JFrame {
    private JTextField emploisIdField;
    private JTextField enseignantField;
    private JTextField coursField;
    private JTextField jourSemaineField;
    private JTextField heureDebutField;
    private JTextField heureFinField;
    private JTextField salleField;
    private JButton modifierButton;
    private JButton closeButton;

    public ModifierEmplois() {
        setTitle("Modifier Emplois");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // Initialize components
        JLabel emploisIdLabel = new JLabel("ID de l'Emplois:");
        emploisIdField = new JTextField();
        JLabel enseignantLabel = new JLabel("Nouvel enseignant:");
        enseignantField = new JTextField();
        JLabel coursLabel = new JLabel("Nouveau cours:");
        coursField = new JTextField();
        JLabel jourSemaineLabel = new JLabel("Nouveau jour de la semaine:");
        jourSemaineField = new JTextField();
        JLabel heureDebutLabel = new JLabel("Nouvelle heure de début:");
        heureDebutField = new JTextField();
        JLabel heureFinLabel = new JLabel("Nouvelle heure de fin:");
        heureFinField = new JTextField();
        JLabel salleLabel = new JLabel("Nouvelle salle:");
        salleField = new JTextField();
        modifierButton = new JButton("Modifier");
        closeButton = new JButton("Close");

        // Add components to the frame
        add(emploisIdLabel);
        add(emploisIdField);
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
        add(modifierButton);
        add(closeButton);

        // Add action listener to the "Modifier" button
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Call the method to modify schedule in the database
                    int emploisId = Integer.parseInt(emploisIdField.getText());
                    String newEnseignant = enseignantField.getText();
                    String newCours = coursField.getText();
                    String newJourSemaine = jourSemaineField.getText();
                    String newHeureDebut = heureDebutField.getText();
                    String newHeureFin = heureFinField.getText();
                    String newSalle = salleField.getText();

                    CrudOperations.modifyEmplois(
                            emploisId,
                            newEnseignant,
                            newCours,
                            newJourSemaine,
                            newHeureDebut,
                            newHeureFin,
                            newSalle
                    );

                    // Display success message
                    JOptionPane.showMessageDialog(ModifierEmplois.this, "Emplois modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Clear fields after successful modification
                    clearFields();
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    // Handle the exception (e.g., show an error message)
                    JOptionPane.showMessageDialog(ModifierEmplois.this, "Erreur lors de la modification de l'Emplois.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add action listener to the "Close" button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the ModifierEmplois window
                dispose();
            }
        });
    }

    private void clearFields() {
        emploisIdField.setText("");
        enseignantField.setText("");
        coursField.setText("");
        jourSemaineField.setText("");
        heureDebutField.setText("");
        heureFinField.setText("");
        salleField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ModifierEmplois().setVisible(true);
            }
        });
    }
}
