package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import Model.CrudOperations;

public class AdminProfileVoir extends JFrame {

    private JLabel profileImageLabel;
    private JLabel nameLabel;
    private JLabel prenomLabel;
    private JLabel ageLabel;
    private JLabel adresseLabel;
    private JLabel matriculeLabel;
    private JLabel emailLabel;

    public AdminProfileVoir(String username) {
        setTitle("Admin Profile");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load a sample profile image (replace with actual image loading logic)
        // BufferedImage profileImage = loadProfileImage();

        // Create a JLabel for the profile image with rounded borders and shadow
        profileImageLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("imgs/user.png")));
        profileImageLabel.setHorizontalAlignment(JLabel.CENTER);
        profileImageLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 5),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        profileImageLabel.setOpaque(true);
        profileImageLabel.setBackground(new Color(220, 220, 220)); // Light gray background

        // Create labels to display the information
        nameLabel = createLabel("Nom: ");
        prenomLabel = createLabel("Prenom: ");
        ageLabel = createLabel("Age: ");
        adresseLabel = createLabel("Adresse: ");
        matriculeLabel = createLabel("Matricule: ");
        emailLabel = createLabel("Email: ");

        // Create a panel to hold the labels
        JPanel panel = new JPanel(new GridLayout(7, 1, 0, 10));  // Adjusted row spacing
        panel.setBackground(Color.WHITE);
        panel.add(profileImageLabel);  // Add the profile image label
        panel.add(nameLabel);
        panel.add(prenomLabel);
        panel.add(ageLabel);
        panel.add(adresseLabel);
        panel.add(matriculeLabel);
        panel.add(emailLabel);

        // Add some padding around the panel for a cleaner layout
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Customize fonts and styles for a more professional look
        customizeLabels();

        // Add the panel to the frame
        add(panel);

        // Fetch administrator information from the database using CrudOperations
        String[] adminInfo = CrudOperations.displayData("Administrateurs", username);

        // Check if data is retrieved successfully
        if (adminInfo != null) {
            // Update the labels with administrator information
            updateLabels(adminInfo);
        } else {
            // Handle the case where data retrieval fails
            JOptionPane.showMessageDialog(this, "Failed to retrieve data for the administrator.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    private BufferedImage loadProfileImage() {
//        try {
//            // Replace "path/to/your/profile-image.jpg" with the actual path to your image
//            return ImageIO.read(getClass().getResource("user.png"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    private JLabel createLabel(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        return label;
    }

    private void customizeLabels() {
        nameLabel.setForeground(Color.DARK_GRAY);
        prenomLabel.setForeground(Color.DARK_GRAY);
        ageLabel.setForeground(Color.DARK_GRAY);
        adresseLabel.setForeground(Color.DARK_GRAY);
        matriculeLabel.setForeground(Color.DARK_GRAY);
        emailLabel.setForeground(Color.DARK_GRAY);
    }

    public void updateLabels(String[] adminInfo) {
        nameLabel.setText("Nom: " + adminInfo[0]);
        prenomLabel.setText("Prenom: " + adminInfo[1]);
        ageLabel.setText("Age: " + adminInfo[2]);
        adresseLabel.setText("Adresse: " + adminInfo[3]);
        matriculeLabel.setText("Matricule: " + adminInfo[4]);
        emailLabel.setText("Email: " + adminInfo[5]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminProfileVoir("admin123").setVisible(true);
        });
    }
}
