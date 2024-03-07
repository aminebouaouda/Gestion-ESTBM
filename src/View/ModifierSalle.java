package View;
import javax.swing.*;

import Model.CrudOperations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModifierSalle extends JFrame {
    private JTextField idSalleField;
    private JTextField nomSalleField;
    private JTextField capaciteField;

    public ModifierSalle() {
        setTitle("Modifier une salle");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idSalleField = new JTextField(20);
        nomSalleField = new JTextField(20);
        capaciteField = new JTextField(20);

        JButton updateButton = new JButton("Modifier");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idSalle = Integer.parseInt(idSalleField.getText());
                    String nomSalle = nomSalleField.getText();
                    int capacite = Integer.parseInt(capaciteField.getText());
                    CrudOperations.updateSalle(idSalle, nomSalle, capacite);
                    JOptionPane.showMessageDialog(null, "Salle modifiée avec succès.");
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur lors de la modification de la salle.");
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("ID de la salle à modifier:"));
        panel.add(idSalleField);
        panel.add(new JLabel("Nouveau nom de la salle:"));
        panel.add(nomSalleField);
        panel.add(new JLabel("Nouvelle capacité:"));
        panel.add(capaciteField);
        panel.add(new JLabel(""));
        panel.add(updateButton);

        add(panel);
        setVisible(true);
    }
}
