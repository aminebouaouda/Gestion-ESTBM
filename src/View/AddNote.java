package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import Model.CrudOperations;

public class AddNote extends JFrame {

    private JTextField idEtudiantField;
    private JTextField idExamenField;
    private JTextField noteField;

    private CrudOperations crudOperations;

    public AddNote() {
        CrudOperations crudOperations = new CrudOperations();
this.setCrudOperations(crudOperations);
        setTitle("Add a Note");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        createFields();
        createLabels();
        createPanel();
        createAddButton();
    }

    public void setCrudOperations(CrudOperations crudOperations) {
        this.crudOperations = crudOperations;
    }

    private void createFields() {
        idEtudiantField = new JTextField();
        idExamenField = new JTextField();
        noteField = new JTextField();
    }

    private void createLabels() {
        JLabel idEtudiantLabel = new JLabel("ID Etudiant: ");
        JLabel idExamenLabel = new JLabel("ID Examen: ");
        JLabel noteLabel = new JLabel("Note: ");
    }

    private void createPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("ID Etudiant: "));
        panel.add(idEtudiantField);
        panel.add(new JLabel("ID Examen: "));
        panel.add(idExamenField);
        panel.add(new JLabel("Note: "));
        panel.add(noteField);
        add(panel);
    }

    private void createAddButton() {
        JButton addButton = new JButton("Add Note");
        addButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					handleAddButtonClick();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        JButton fermerButton = new JButton("Close");
        fermerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fermerButton.addActionListener(e -> dispose()); // Close the window when the button is clicked

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(fermerButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleAddButtonClick() throws SQLException {
        try {
            // Get the information from text fields
            int idEtudiant = Integer.parseInt(idEtudiantField.getText());
            int idExamen = Integer.parseInt(idExamenField.getText());
            double note = Double.parseDouble(noteField.getText());

            // Add Note to the database and associate it with the exam and student
            if (crudOperations != null) {
                crudOperations.insertNoteAndAssociateWithExamAndStudent(idEtudiant, idExamen, note);

                // Display a success message
                showInfo("Note added successfully.");

                // Clear the text fields
                clearFields();
            } else {
                showError("CrudOperations instance is not set.");
            }
        } catch (NumberFormatException ex) {
            // Handle number format error
            ex.printStackTrace();
            showError("Invalid input. Please enter valid numbers.");
        }
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        idEtudiantField.setText("");
        idExamenField.setText("");
        noteField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Assuming you have a CrudOperations instance (you need to create it)
                CrudOperations crudOperations = new CrudOperations();

                AddNote addNoteFrame = new AddNote();
                addNoteFrame.setCrudOperations(crudOperations);
                addNoteFrame.setVisible(true);
            }
        });
    }
}
