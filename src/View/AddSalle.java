package View;
import javax.swing.*;

import Model.CrudOperations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddSalle extends JFrame {
    private JTextField nomSalleField;
    private JTextField capaciteField;

    public AddSalle() {
        setTitle("Ajouter une salle");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nomSalleField = new JTextField(20);
        capaciteField = new JTextField(20);

        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomSalle = nomSalleField.getText();
                    int capacite = Integer.parseInt(capaciteField.getText());
                    CrudOperations.insertSalle(nomSalle, capacite);
                    JOptionPane.showMessageDialog(null, "Salle ajoutée avec succès.");
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de la salle.");
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nom de la salle:"));
        panel.add(nomSalleField);
        panel.add(new JLabel("Capacité:"));
        panel.add(capaciteField);
        panel.add(new JLabel(""));
        panel.add(addButton);

        add(panel);
        setVisible(true);
    }

    // Les autres classes (ModifierSalle, DeleteSalle, DisplaySalle) peuvent être implémentées de manière similaire.
}
