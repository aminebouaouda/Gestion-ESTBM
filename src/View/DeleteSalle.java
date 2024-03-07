package View;
import javax.swing.*;

import Model.CrudOperations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteSalle extends JFrame {
    private JTextField idSalleField;

    public DeleteSalle() {
        setTitle("Supprimer une salle");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idSalleField = new JTextField(20);

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idSalle = Integer.parseInt(idSalleField.getText());
                    CrudOperations.deleteSalle(idSalle);
                    JOptionPane.showMessageDialog(null, "Salle supprimée avec succès.");
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de la salle.");
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("ID de la salle à supprimer:"));
        panel.add(idSalleField);
        panel.add(new JLabel(""));
        panel.add(deleteButton);

        add(panel);
        setVisible(true);
    }
}
