package View;

import javax.swing.*;

import Model.CrudOperations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDashboard extends JFrame {
	

	
	
    private String loggedInAdminEmail;

    public AdminDashboard(String loggedInAdminEmail) {

        this.loggedInAdminEmail = loggedInAdminEmail;

        setTitle("Tableau de bord - Administrateur");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Your AdminDashboard content goes here...

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu for managing Etudiants
        JMenu etudiantMenu = new JMenu("Etudiants");
        JMenuItem ajoutEtudiantItem = new JMenuItem("Ajout");
        ajoutEtudiantItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new AddEtudiant().setVisible(true);
            }
        });
        JMenuItem modifEtudiantItem = new JMenuItem("Modification");
        modifEtudiantItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new ModifyEtudiant().setVisible(true);
            }
        });
        JMenuItem suppEtudiantItem = new JMenuItem("Suppression");
        suppEtudiantItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new deleteEnseignant().setVisible(true);
            }
        });
        
        
        JMenuItem showEtudiantItem = new JMenuItem("affiachage de la liste des Etudiants disponibles");
        showEtudiantItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DisplayEtudiant().setVisible(true);
            }
        });
        
        etudiantMenu.add(ajoutEtudiantItem);
        etudiantMenu.add(modifEtudiantItem);
        etudiantMenu.add(suppEtudiantItem);
        etudiantMenu.add(showEtudiantItem);

        
        // Create a menu for managing Enseignants
        JMenu enseignantMenu = new JMenu("Enseignants");
        JMenuItem ajoutEnseignantItem = new JMenuItem("Ajout");
        ajoutEnseignantItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new AddEnseignant().setVisible(true);
            }
        });
        JMenuItem modifEnseignantItem = new JMenuItem("Modification");
        modifEnseignantItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new ModifiyEnseignant().setVisible(true);
            }
        });
        JMenuItem suppEnseignantItem = new JMenuItem("Suppression");
        suppEnseignantItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new deleteEnseignant().setVisible(true);
            }
        });
        JMenuItem showEnseignantItem = new JMenuItem("affichage des enseignants disponibles");
        showEnseignantItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DisplayEnseignant().setVisible(true);
            }
        });
        enseignantMenu.add(ajoutEnseignantItem);
        enseignantMenu.add(modifEnseignantItem);
        enseignantMenu.add(suppEnseignantItem);
        enseignantMenu.add(showEnseignantItem );

        // Create a menu for managing Filieres
        JMenu filiereMenu = new JMenu("Filieres");
        JMenuItem ajoutFiliereItem = new JMenuItem("Ajout");
        ajoutFiliereItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new AddFiliere().setVisible(true);
            }
        });
        JMenuItem modifFiliereItem = new JMenuItem("Modification");
        modifFiliereItem.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                try {
					new ModifyFiliere().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        JMenuItem suppFiliereItem = new JMenuItem("Suppression");
        suppFiliereItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DeleteFiliere().setVisible(true);
            }
        });
        JMenuItem showFiliereItem = new JMenuItem("affichage des filieres disponibles");
        showFiliereItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DisplayFiliere().setVisible(true);
            }
        });
        
        filiereMenu.add(ajoutFiliereItem);
        filiereMenu.add(modifFiliereItem);
        filiereMenu.add(suppFiliereItem);
        filiereMenu.add(showFiliereItem);

        // Create a menu for managing Cours
        JMenu coursMenu = new JMenu("Cours");
        JMenuItem ajoutCoursItem = new JMenuItem("Ajout des cours ");
        ajoutCoursItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new AjouterCours().setVisible(true);
            }
        });
        
        JMenuItem modifCoursItem = new JMenuItem("Modification des cours");
        modifCoursItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new modifierCours().setVisible(true);
            }
        });
        
        JMenuItem suppCoursItem = new JMenuItem("Suppression des cours");
        suppCoursItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new SupprimerCours().setVisible(true);
            }
        });
        
        JMenuItem showCoursItem = new JMenuItem("affichage des cours");
        showCoursItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DisplayCourse().setVisible(true);
            }
        });
        
        coursMenu.add(ajoutCoursItem);
        coursMenu.add(modifCoursItem);
        coursMenu.add(suppCoursItem);
        coursMenu.add(showCoursItem);

        // Create a menu for managing Notes
        JMenu notesMenu = new JMenu("Notes");
        JMenuItem ajoutNotesItem = new JMenuItem("Ajout");
        ajoutNotesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new AddNote().setVisible(true);
            }
        });
        
        JMenuItem modifNotesItem = new JMenuItem("Modification");
        modifNotesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new ModifierNote().setVisible(true);
            }
        });
        
        JMenuItem suppNotesItem = new JMenuItem("Suppression");
        suppNotesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DeleteNote().setVisible(true);
            }
        });
        JMenuItem showNotesItem = new JMenuItem("affichage des moyenne des note pour chaque etudiant");
        showNotesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DisplayNote().setVisible(true);
            }
        });
        notesMenu.add(ajoutNotesItem);
        notesMenu.add(modifNotesItem);
        notesMenu.add(suppNotesItem);
        notesMenu.add(showNotesItem);

        // Create a menu for managing Examens
        JMenu emploisMenu = new JMenu("Emplois du temps");
        JMenuItem ajoutEmploisItem = new JMenuItem("Ajout");
        ajoutEmploisItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new AddEmplois().setVisible(true);
            }
        });
        
        JMenuItem modifEmploisItem = new JMenuItem("Modification");
        modifEmploisItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new ModifierEmplois().setVisible(true);
            }
        });
        JMenuItem suppEmploisItem = new JMenuItem("Suppression");
        suppEmploisItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DeleteEmplois().setVisible(true);
            }
        });
        JMenuItem showEmploisItem = new JMenuItem("affichage des emplois du temps");
        showEmploisItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DisplayEmplois().setVisible(true);
            }
        });
        emploisMenu.add(ajoutEmploisItem);
        emploisMenu.add(modifEmploisItem);
        emploisMenu.add(suppEmploisItem);
        emploisMenu.add(showEmploisItem );

        // Create a menu for managing Examens
        JMenu examensMenu = new JMenu("Examens");
        JMenuItem ajoutExamensItem = new JMenuItem("Ajout");
        ajoutExamensItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new AddExam().setVisible(true);
            }
        });
        
        JMenuItem modifExamensItem = new JMenuItem("Modification");
        modifExamensItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new ModifierExam().setVisible(true);
            }
        });
        JMenuItem suppExamensItem = new JMenuItem("Suppression");
        suppExamensItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DeleteExam().setVisible(true);
            }
        });
        
        JMenuItem showExamensItem = new JMenuItem("affichages des examens disponibles");
        showExamensItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DisplayExam().setVisible(true);
            }
        });
        
        examensMenu.add(ajoutExamensItem);
        examensMenu.add(modifExamensItem);
        examensMenu.add(suppExamensItem);
        examensMenu.add(showExamensItem);
        JMenu salleMenu = new JMenu("Salle de classe ");
        JMenuItem ajoutSalleItem = new JMenuItem("Ajout");
        ajoutSalleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new AddSalle().setVisible(true);
            }
        });
        JMenuItem modifierSalleItem = new JMenuItem("Modification");
        modifierSalleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new ModifierSalle().setVisible(true);
            }
        });
        JMenuItem suppSalleItem = new JMenuItem("Suppression");
        suppSalleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DeleteSalle().setVisible(true);
            }
        });
        JMenuItem showSalleItem = new JMenuItem("affichage");
        showSalleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new DisplaySalle().setVisible(true);
            }
        });
        salleMenu.add(ajoutSalleItem);
        salleMenu.add(modifierSalleItem);
        salleMenu.add(suppSalleItem);
        salleMenu.add(showSalleItem);


        // Create a menu for managing the profile
        JMenu profileMenu = new JMenu("Profile");
        JMenuItem viewProfileItem = new JMenuItem("Voir le profil");
  
        // Add ActionListener to viewProfileItem
        viewProfileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new AdminProfileVoir(loggedInAdminEmail).setVisible(true);
            }
        });
        JMenuItem editProfileItem = new JMenuItem("Modifier le profil");
        editProfileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AdminProfileVoir window with the appropriate admin's profile
                // You need to replace "username" with the actual username of the logged-in admin
                new AdminProfileModifier(loggedInAdminEmail).setVisible(true);
            }
        });
        
        //starts from here ---
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        


        // Add a user photo under the menu bar
        ImageIcon userPhoto = new ImageIcon(getClass().getClassLoader().getResource("imgs/bg-est.jpg")); // Replace with the actual path
        JLabel photoLabel = new JLabel(userPhoto);
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(photoLabel, BorderLayout.CENTER);

        // Create buttons for adding students and teachers
        JPanel buttonPanel = new JPanel();
        JButton addStudentButton = new JButton("Ajouter Etudiants");
        JButton addTeacherButton = new JButton("Ajouter Enseignants");
        JButton showStatisticsButton = new JButton("Afficher Statistiques");

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEtudiant().setVisible(true);
            }
        });

        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEnseignant().setVisible(true);
            }
        });


        buttonPanel.add(addStudentButton);
        buttonPanel.add(addTeacherButton);
       
        panel.add(buttonPanel, BorderLayout.SOUTH);

        profileMenu.add(viewProfileItem);
        profileMenu.add(editProfileItem);

        // Add menus to the menu bar
        menuBar.add(etudiantMenu);
        menuBar.add(enseignantMenu);
        menuBar.add(filiereMenu);
        menuBar.add(coursMenu);
        menuBar.add(emploisMenu);
        menuBar.add(notesMenu);
        menuBar.add(examensMenu);
        menuBar.add(salleMenu);
        menuBar.add(profileMenu);
        add(panel);
        // Add menu bar to the frame
        setJMenuBar(menuBar);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
