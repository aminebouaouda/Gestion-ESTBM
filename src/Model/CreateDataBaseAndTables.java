package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDataBaseAndTables {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Replace with your actual password

    private static Connection connection;

    public static void CreateDatabaseAndTables() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            createDatabase();
            useDatabase("ESTBM_db");
            createEnseignantsTable();
            createAdministrateursTable();
            createEtudiantsTable();
            createFilieresTable();
            createCoursTable();
            createExamensTable();
            createNotesTable();
            createSallesDeClasseTable();
            createEmploisDuTempsTable();
            createEtudiantsFiliereTable();
            createEnseignantsFiliereTable();
            createEnseignantsCoursTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private static void createDatabase() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS ESTBM_db");
        System.out.println("Database created successfully!");
        System.out.println("");
    }

    private static void useDatabase(String dbName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("USE " + dbName);
    }

    private static void createEnseignantsTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS Enseignants ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "nom VARCHAR(50),"
                + "prenom VARCHAR(50),"
                + "age INT,"
                + "adresse VARCHAR(100),"
                + "anneeNaissance INT,"
                + "matricule VARCHAR(50),"
                + "email VARCHAR(50),"
                + "motDePasse VARCHAR(255))");
        System.out.println("Table enseignant created successfully!");
    }

    private static void createAdministrateursTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS Administrateurs ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "nom VARCHAR(50),"
                + "prenom VARCHAR(50),"
                + "age INT,"
                + "adresse VARCHAR(100),"
                + "anneeNaissance INT,"
                + "matricule VARCHAR(50),"
                + "email VARCHAR(50),"
                + "motDePasse VARCHAR(255))");
        System.out.println("Table admin created successfully!");
    }

    private static void createEmploisDuTempsTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS EmploisDuTemps ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "idEnseignant INT,"
                + "idCours INT,"
                + "jourSemaine VARCHAR(10),"
                + "heureDebut TIME,"
                + "heureFin TIME,"
                + "salle_id INT,"
                + "FOREIGN KEY (idEnseignant) REFERENCES Enseignants(id),"
                + "FOREIGN KEY (idCours) REFERENCES Cours(id),"
                + "FOREIGN KEY (salle_id) REFERENCES SallesDeClasse(id))");
        System.out.println("Table emplois du temps created successfully!");
    }

    
    private static void createEtudiantsTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS Etudiants ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "nom VARCHAR(50),"
                + "prenom VARCHAR(50),"
                + "age INT,"
                + "adresse VARCHAR(100),"
                + "anneeNaissance INT,"
                + "matricule VARCHAR(50),"
                + "email VARCHAR(50),"
                + "motDePasse VARCHAR(255))");
        System.out.println("Table etudiant created successfully!");
    }

    private static void createFilieresTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS Filieres (\r\n"
        		+ "    id INT PRIMARY KEY AUTO_INCREMENT,\r\n"
        		+ "    nomFiliere VARCHAR(50)\r\n"
        		+ ");\r\n"
        		+ "");
        System.out.println("Table filiere created successfully!");
    }

    private static void createCoursTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS Cours ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "nomCours VARCHAR(50),"
                + "idEnseignant INT,"
                + "nb_heures INT,"
                + "idFiliere INT,"
                + "FOREIGN KEY (idEnseignant) REFERENCES Enseignants(id),"
                + "FOREIGN KEY (idFiliere) REFERENCES Filieres(id))");
        System.out.println("Table cours created successfully!");
    }

    
    
    private static void createExamensTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS Examens ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "idCours INT,"
                + "dateExamen DATE,"
                + "FOREIGN KEY (idCours) REFERENCES Cours(id))");
        System.out.println("Table exams created successfully!");
    }

    private static void createNotesTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS Notes ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "idEtudiant INT,"
                + "idExamen INT,"
                + "note DECIMAL(4,2),"
                + "FOREIGN KEY (idEtudiant) REFERENCES Etudiants(id),"
                + "FOREIGN KEY (idExamen) REFERENCES Examens(id))");
        System.out.println("Table notes created successfully!");
    }

    private static void createSallesDeClasseTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS SallesDeClasse ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "nomSalle VARCHAR(50),"
                + "capacite INT)");
        System.out.println("Table salles created successfully!");
    }
    

    private static void createEtudiantsFiliereTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS EtudiantsFiliere ("
                + "id INT AUTO_INCREMENT,"
                + "idEtudiant INT,"
                + "idFiliere INT,"
                + "FOREIGN KEY (idEtudiant) REFERENCES Etudiants(id),"
                + "FOREIGN KEY (idFiliere) REFERENCES Filieres(id),"
                + "PRIMARY KEY (id, idFiliere))");
        System.out.println("Table etudiantFiliere created successfully!");
    }

    private static void createEnseignantsFiliereTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS EnseignantsFiliere ("
                + "id INT AUTO_INCREMENT,"
                + "idEnseignant INT,"
                + "idFiliere INT,"
                + "FOREIGN KEY (idEnseignant) REFERENCES Enseignants(id),"
                + "FOREIGN KEY (idFiliere) REFERENCES Filieres(id),"
                + "PRIMARY KEY (id, idFiliere))");
        System.out.println("Table EnseignantFiliere created successfully!");
    }

    
    private static void createEnseignantsCoursTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS EnseignantsCours ("
                + "id INT AUTO_INCREMENT,"
                + "idEnseignant INT,"
                + "idCours INT,"
                + "FOREIGN KEY (idEnseignant) REFERENCES Enseignants(id),"
                + "FOREIGN KEY (idCours) REFERENCES Cours(id),"
                + "PRIMARY KEY (id, idCours))");
        System.out.println("Table Enseignantcours created successfully!");
    }

    private static void executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    
    private static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
