package View;

import javax.swing.*;

import Model.DataBaseHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EtudiantConnexion extends JFrame {

    public EtudiantConnexion() {
        setTitle("Etudiant Connexion");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Connexion");
        JButton returnButton = new JButton("Retour à l'accueil");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement login logic for teacher here
                // Example: Get input values, validate, and check against the database
                boolean success = DataBaseHandler.checkLogin(usernameField.getText(), passwordField.getPassword(), "etudiants");

                if (success) {
                    new EtudiantDashboard();
                    dispose();  // Close the current frame
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");
                }
            }
        });
        
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action for returning to the home page
                // For example, open the home page or perform other actions
                new Acceuil().setVisible(true);

                dispose();  // Close the current frame
            }
        });
        
        
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLUE);
        loginButton.setPreferredSize(new Dimension(150, 30));
        
        // Set font, foreground color, background color, and preferred size for the "Retour à l'accueil" button
        returnButton.setFont(new Font("Arial", Font.PLAIN, 12));
        returnButton.setForeground(Color.BLACK);
        returnButton.setBackground(Color.LIGHT_GRAY);
        returnButton.setPreferredSize(new Dimension(150, 30));

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());  // Empty label for spacing
        panel.add(new JLabel());  //
        panel.add(loginButton);
        panel.add(returnButton);

        add(panel);
        setVisible(true);
    }
}
