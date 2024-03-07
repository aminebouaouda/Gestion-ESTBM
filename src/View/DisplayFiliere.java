package View;

import Model.CrudOperations;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DisplayFiliere extends JFrame {

    public DisplayFiliere() {
        setTitle("Display Filieres");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JTextArea filiereTextArea = new JTextArea();
        filiereTextArea.setEditable(false);



        try {
            List<CrudOperations.Filiere> filieres = CrudOperations.getFilieres();

            if (!filieres.isEmpty()) {
            	for (CrudOperations.Filiere filiere : filieres) {
            	    filiereTextArea.append("ID: " + filiere.getId() + "\tNom: " + filiere.getName() + "\n");
            	}

            } else {
                filiereTextArea.setText("Aucune filiere disponible.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            filiereTextArea.setText("Erreur lors de la récupération des filieres.");
        }

        JScrollPane scrollPane = new JScrollPane(filiereTextArea);
        add(scrollPane);

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DisplayFiliere().setVisible(true));
    }
}

