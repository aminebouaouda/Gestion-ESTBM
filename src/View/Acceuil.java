package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.CreateDataBaseAndTables;

public class Acceuil extends JFrame {

    public Acceuil() {
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("imgs/logo.png"));
        Image logoImage = logo.getImage();

        int logoWidth = 80;
        int logoHeight = 50;

        Image resizedLogo = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedLogoIcon = new ImageIcon(resizedLogo);

        JFrame frame = new JFrame("Gestion de l'EST-BM");
        frame.setIconImage(resizedLogoIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        JMenuBar menuBar = new JMenuBar();

        JLabel logoLabel = new JLabel(resizedLogoIcon);
        JPanel logoPanel = new JPanel();
        logoPanel.add(logoLabel);
        menuBar.add(logoPanel);

        int remainingWidth = frame.getWidth() - logoWidth;
        int elementWidth = remainingWidth / 3;

//        JMenu menuEnseignant = new JMenu("Espace Enseignant");
//        JMenuItem enseignantDashboardItem = new JMenuItem("connexion");
//        enseignantDashboardItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Open AdminDashboard when the menu item is clicked
//                SwingUtilities.invokeLater(() -> {
//                    new  EnseignantConnexion();
//                    frame.dispose();  // Close the current frame
//                });
//            }
//        });
//        
//
//        JMenuItem enseignantCoursItem = new JMenuItem("inscription");
//        enseignantCoursItem .addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Open EnseignantDashboard when the menu item is clicked
//                SwingUtilities.invokeLater(() -> {
//                    new EnseignantInscription();
//                    frame.dispose();  // Close the current frame
//                });
//            }
//        });
//        menuEnseignant.add(enseignantDashboardItem);
//        menuEnseignant.add(enseignantCoursItem);
//        menuBar.add(menuEnseignant);

//        JMenu menuEtudiant = new JMenu("Espace Étudiant");
//        JMenuItem etudiantDashboardItem = new JMenuItem("connexion");
//        etudiantDashboardItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Open EtudiantDashboard when the menu item is clicked
//                SwingUtilities.invokeLater(() -> {
//                    new EtudiantConnexion();
//                    frame.dispose();  // Close the current frame
//                });
//            }
//        });
//        
//        JMenuItem etudiantInscriptionItem = new JMenuItem("Inscription");
//        etudiantInscriptionItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Open AdminDashboard when the menu item is clicked
//                SwingUtilities.invokeLater(() -> {
//                    new  EtudiantInscription();
//                    frame.dispose();  // Close the current frame
//                });
//            }
//        });
//        menuEtudiant.add(etudiantDashboardItem);
//        menuEtudiant.add(etudiantInscriptionItem);
//        menuBar.add(menuEtudiant);

        JMenu menuAdmin = new JMenu("Espace Administrateur");
        JMenuItem adminDashboardItem = new JMenuItem("connexion");
        adminDashboardItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open AdminDashboard when the menu item is clicked
                SwingUtilities.invokeLater(() -> {
                    new AdminConnexion();
                    frame.dispose();  // Close the current frame
                });
            }
        });
        JMenuItem adminEnseignantsItem = new JMenuItem("inscription");
        adminEnseignantsItem .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open AdminDashboard when the menu item is clicked
                SwingUtilities.invokeLater(() -> {
                    new AdminInscription();
                    frame.dispose();  // Close the current frame
                });
            }
        });
        menuAdmin.add(adminDashboardItem);
        menuAdmin.add(adminEnseignantsItem);
        menuBar.add(menuAdmin);

        JMenu menuApropos = new JMenu("A propos");
        JMenuItem AproposdItem = new JMenuItem("Informations");
        menuApropos.add(AproposdItem);
        menuBar.add(menuApropos);
        AproposdItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomePage();
                frame.dispose();
            }
        });

        frame.setJMenuBar(menuBar);

        JPanel bodyPanel = new JPanel(new BorderLayout());

        JPanel estBmPanel = new JPanel();
        
        ImageIcon estBmPhoto = new ImageIcon(getClass().getClassLoader().getResource("imgs/bg-est.jpg"));
        Image resizedEstBmPhoto = estBmPhoto.getImage().getScaledInstance(800, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedEstBmPhotoIcon = new ImageIcon(resizedEstBmPhoto);
        JLabel estBmPhotoLabel = new JLabel(resizedEstBmPhotoIcon);
        estBmPanel.add(estBmPhotoLabel);

        JLabel welcomeLabel = new JLabel("Bienvenue dans Gestion l'EST-BM !");
        JButton clickMeButton = new JButton("À propos");
        clickMeButton.setFocusable(false);
        Dimension buttonSize = new Dimension(120, 30);
        clickMeButton.setPreferredSize(buttonSize);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(welcomeLabel, gbc);
        gbc.gridy = 1;
        centerPanel.add(clickMeButton, gbc);

        clickMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new WelcomePage();
                    frame.dispose();
                });
            }
        });

        Font professionalFont = new Font("Arial", Font.BOLD, 16);
        welcomeLabel.setFont(professionalFont);
        clickMeButton.setFont(professionalFont);

        clickMeButton.setBackground(new Color(0, 150, 136));
        clickMeButton.setForeground(Color.WHITE);
        clickMeButton.setBorderPainted(false);
        clickMeButton.setFocusPainted(false);

        bodyPanel.add(estBmPanel, BorderLayout.NORTH);
        bodyPanel.add(centerPanel, BorderLayout.CENTER);

        // Create a footer panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(33, 33, 33));
        footerPanel.setPreferredSize(new Dimension(frame.getWidth(), 30));

        // Add content to the footer
        JLabel copyrightLabel = new JLabel("© 2023 EST-BM. tous droit reserver .");
        copyrightLabel.setForeground(Color.WHITE);

        // Add the footer components
        footerPanel.add(copyrightLabel);

        // Add the footer panel to the frame
        frame.add(footerPanel, BorderLayout.SOUTH);

        frame.add(bodyPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
