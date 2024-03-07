package View;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import Model.CrudOperations;

public class DisplayEmplois extends JFrame {
    private JTextArea emploisTextArea;
    private JButton refreshButton;
    private JButton closeButton;

    public DisplayEmplois() {
        setTitle("Afficher Emplois");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        emploisTextArea = new JTextArea();
        emploisTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(emploisTextArea);
        refreshButton = new JButton("Actualiser");
        closeButton = new JButton("Fermer");

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to the "Actualiser" button
        refreshButton.addActionListener(e -> {
            // Call the method to get and display emplois
            displayEmplois();
        });

        // Add action listener to the "Fermer" button
        closeButton.addActionListener(e -> {
            // Close the DisplayEmplois window
            dispose();
        });
    }

    private void displayEmplois() {
        try {
            // Call the method to get emplois from the database
            List<String> emploisList = CrudOperations.getAllEmplois();

            // Display emplois in the text area
            emploisTextArea.setText("");
            for (String emploi : emploisList) {
                emploisTextArea.append(emploi + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception (e.g., show an error message)
            JOptionPane.showMessageDialog(DisplayEmplois.this, "Erreur lors de la récupération des emplois.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DisplayEmplois().setVisible(true));
    }
}
