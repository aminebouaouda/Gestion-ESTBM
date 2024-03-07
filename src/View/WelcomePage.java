package View ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomePage extends JFrame {
    public WelcomePage() {
        // Load the logo image
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("imgs/bg-est.jpg"));
        Image logoImage = logo.getImage();

        // Create a new frame for the WelcomePage
        JFrame frame = new JFrame("Welcome to EST-BM Information");
        frame.setIconImage(logoImage);
        frame.setLayout(new BorderLayout());

        // Add a return button
        JButton returnButton = new JButton("Return to Acceuil");
        returnButton.setBackground(new Color(0, 150, 136));  // Turquoise color
        returnButton.setForeground(Color.WHITE);
        returnButton.setBorderPainted(false);
        returnButton.setFocusPainted(false);
        returnButton.setFocusable(false);

        // Add ActionListener to the return button
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Acceuil page when the return button is clicked
                new Acceuil();
                frame.dispose();  // Close the current frame
            }
        });

        // Create a panel for the return button
        JPanel returnPanel = new JPanel();
        returnPanel.add(returnButton);

        // Add the return panel to the frame
        frame.getContentPane().add(returnPanel, BorderLayout.SOUTH);

        // Create a panel for text and image
        JPanel textImagePanel = new JPanel(new BorderLayout());

        // Text panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        // Add text to the text panel
        JLabel titleLabel = new JLabel("À propos de l'EST Béni Mellal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(10));  // Add vertical space
        textPanel.add(new JLabel("<html><p style='text-align: justify; font-weight:15px;  font-familly:sans-serif;'><br>\"Créée en 2012\", <br>l’Ecole Supérieure de Technologie de Béni Mellal (ESTBM) a pour mission de diversifier<br> et améliorer l'offre de formation au sein de l’Université Sultan Moulay Slimane.<br> L'école supérieure de technologie de Béni Mellalformera des futurs techniciens supérieurs<br> ainsi que des licenciés professionnels capables de produire<br> et d’entreprendre dans différents domaines techniques. L’accès à l’ESTBM est ouvert aux:<br> <br>* Titulaires d’un Bac scientifique ou techniques pour préparer le diplôme universitaire<br> de technologie Bac+2 (DUT).<br> * Titulaires d'un diplôme Bac+2 pour préparer le diplôme (bac+3) de la licence<br> professionnelle (LP).</p></html>"));

        // Add text panel to the text and image panel
        textImagePanel.add(textPanel, BorderLayout.WEST);

        // Image panel
        ImageIcon estBmImage = new ImageIcon("bg-est.jpg");  // Change this to the actual file path
        Image resizedEstBmImage = estBmImage.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedEstBmImageIcon = new ImageIcon(resizedEstBmImage);
        JLabel estBmImageLabel = new JLabel(resizedEstBmImageIcon);

        // Add image to the text and image panel
        textImagePanel.add(estBmImageLabel, BorderLayout.EAST);

        // Add the text and image panel to the frame
        frame.getContentPane().add(textImagePanel, BorderLayout.CENTER);

        // Display the frame
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
