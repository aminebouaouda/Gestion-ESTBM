package View;
import javax.swing.*;

import Model.CrudOperations;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DisplaySalle extends JFrame {
    private JTextArea displayArea;

    public DisplaySalle() {
        setTitle("Afficher les salles");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        try {
            List<String> salles = CrudOperations.getAllSalles();
            for (String salle : salles) {
                displayArea.append(salle + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des salles.");
        }

        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DisplaySalle();
        });
    }
}
