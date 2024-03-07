package  View;
import javax.swing.*;
import Model.DataBaseHandler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminInscription extends JFrame {

    public AdminInscription() {
        setTitle("Admin Inscription");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(11, 2));
        JLabel nameLabel = new JLabel("Nom:");
        JTextField nameField = new JTextField();
        JLabel prenomLabel = new JLabel("Prénom:");
        JTextField prenomField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel adresseLabel = new JLabel("Adresse:");
        JTextField adresseField = new JTextField();
        JLabel anneeNaissanceLabel = new JLabel("Année de Naissance: (ex:2002)");
        JTextField anneeNaissanceField = new JTextField();
        JLabel matriculeLabel = new JLabel("Matricule: ex(abcd)");
        JTextField matriculeField = new JTextField();
        JLabel usernameLabel = new JLabel("Nom d'utilisateur(email):");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Mot de passe:");
        JPasswordField passwordField = new JPasswordField();
        JButton signUpButton = new JButton("S'inscrire");
        JButton returnButton = new JButton("Retour à l'Accueil");

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the registerUser method from DatabaseHandler for Admin
                if (DataBaseHandler.registerUser(
                        nameField.getText(),
                        prenomField.getText(),
                        Integer.parseInt(ageField.getText()),
                        adresseField.getText(),
                        
                        Integer.parseInt(anneeNaissanceField.getText()),
                        matriculeField.getText(),
                        usernameField.getText(),
                        passwordField.getPassword(),
                        "administrateurs")) {
                    // Successful registration, show a success message
                    JOptionPane.showMessageDialog(null, "Vous avez été inscrit avec succès!");
                } else {
                    // Registration failed, show an error message or handle accordingly
                    JOptionPane.showMessageDialog(null, "L'inscription a échoué. Veuillez réessayer.");
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code here to open Acceuil frame or perform any other action
                // For example, you can create an instance of Acceuil and make it visible
                new Acceuil().setVisible(true);
                dispose();  // Close the current frame
            }
        });

        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(Color.BLUE);
        signUpButton.setPreferredSize(new Dimension(150, 30));

        // Set font, foreground color, and preferred size for the "Retour à l'accueil" button
        returnButton.setFont(new Font("Arial", Font.PLAIN, 12));
        returnButton.setForeground(Color.BLACK);
        returnButton.setBackground(Color.LIGHT_GRAY);
        returnButton.setPreferredSize(new Dimension(150, 30));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(prenomLabel);
        panel.add(prenomField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(adresseLabel);
        panel.add(adresseField);
        panel.add(anneeNaissanceLabel);
        panel.add(anneeNaissanceField);
        panel.add(matriculeLabel);
        panel.add(matriculeField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(signUpButton);
        panel.add(returnButton);

        add(panel);
        setVisible(true);
    }


}
