package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class CrudOperations {
	
	
    private static final String URL = "jdbc:mysql://localhost:3306/estbm_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    static {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the database connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database connection.");
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertData(String tableName, String nom, String prenom, int age, String adresse, int anneeNaissance, String matricule, String email, String motDePasse) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String insertQuery = "INSERT INTO " + tableName + " (nom, prenom, age, adresse, anneeNaissance, matricule, email, motDePasse) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, adresse);
            preparedStatement.setInt(5, anneeNaissance);
            preparedStatement.setString(6, matricule);
            preparedStatement.setString(7, email);
            preparedStatement.setString(8, motDePasse);

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully!");
        }
    }

    
    public static void updateData(String tableName, String username, String nom, String prenom, String adresse, int age, String matricule, String email) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE " + tableName + " SET nom=?, prenom=?, adresse=?, age=?, matricule=?, email=? WHERE email=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, adresse);
                preparedStatement.setInt(4, age);
                preparedStatement.setString(5, matricule);
                preparedStatement.setString(6, email);
                preparedStatement.setString(7, username); // Set the username in the WHERE clause

                preparedStatement.executeUpdate();
                System.out.println("Data updated successfully!");
            }
        }
    }



    public static void deleteData(String tableName, int id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String deleteQuery = "DELETE FROM " + tableName + " WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully!");
        }
    }

    public static String[] displayData(String tableName, String username) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            connection.createStatement().executeUpdate("USE estbm_db");

            // Use PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM " + tableName + " WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Adjust column names based on your actual table structure
                        String[] adminInfo = new String[]{
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                String.valueOf(resultSet.getInt("age")),
                                resultSet.getString("adresse"),
                                resultSet.getString("matricule"),
                                resultSet.getString("email")
                        };
                        return adminInfo;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if data retrieval fails
    }
    
    public static void updateEnseignantData(String tableName, int id, String nom, String prenom, String adresse, int age, String matricule, String email) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE " + tableName + " SET nom=?, prenom=?, adresse=?, age=?, matricule=?, email=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, adresse);
                preparedStatement.setInt(4, age);
                preparedStatement.setString(5, matricule);
                preparedStatement.setString(6, email);
                preparedStatement.setInt(7, id); // Set the Enseignant ID in the WHERE clause

                preparedStatement.executeUpdate();
                System.out.println("Enseignant data updated successfully!");
            }
        }
    }
    
    public static String[] displayDataById(String tableName, int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            connection.createStatement().executeUpdate("USE estbm_db");

            // Use PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM " + tableName + " WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Adjust column names based on your actual table structure
                        String[] enseignantInfo = new String[]{
                                String.valueOf(resultSet.getInt("id")),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                String.valueOf(resultSet.getInt("age")),
                                resultSet.getString("adresse"),
                                String.valueOf(resultSet.getInt("anneeNaissance")),
                                resultSet.getString("matricule"),
                                resultSet.getString("email")
                        };
                        return enseignantInfo;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if data retrieval fails
    }
    public static void deleteEnseignantById(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String tableName = "Enseignants";
            deleteData(tableName, id);
            System.out.println("Enseignant with ID " + id + " deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertFiliereData(String nomFiliere) throws SQLException {
        String query = "INSERT INTO Filieres (nomFiliere) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nomFiliere);
            preparedStatement.executeUpdate();

            // Retrieve the auto-generated key (id)
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("Filiere inserted with ID: " + generatedId);
                } else {
                    System.err.println("Failed to retrieve the generated ID for Filiere.");
                }
            }
        }
    }



    public static String[] displayFiliereDataById(int id) throws SQLException {
        String query = "SELECT * FROM Filieres WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String[] filiereInfo = {
                            String.valueOf(resultSet.getInt("id")),
                            resultSet.getString("nomFiliere")
                    };
                    return filiereInfo;
                }
            }
        }
        return null;
    }


    public static void updateFiliereData(int id, String newNomFiliere) throws SQLException {
        String query = "UPDATE Filieres SET nomFiliere=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newNomFiliere);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteFiliereData(int id) throws SQLException {
        String query = "DELETE FROM Filieres WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
    public static List<Teacher> getTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();

        // Use a try-with-resources to automatically close the PreparedStatement and ResultSet
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nom FROM Enseignants");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nom");
                teachers.add(new Teacher(id, name));
            }

        } // The connection is automatically closed here

        return teachers;
    }


    public static List<Filiere> getFilieres() throws SQLException {
        List<Filiere> filieres = new ArrayList<>();
        String query = "SELECT id, nomFiliere FROM Filieres";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("nomFiliere");
                    filieres.add(new Filiere(id, name));
                }
            }
        }
        return filieres;
    }

    public static void insertCourseData(String courseName, int teacherId, int filiereId) throws SQLException {
        String query = "INSERT INTO Cours (nomCours, idEnseignant, idFiliere) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, courseName);
            preparedStatement.setInt(2, teacherId);
            preparedStatement.setInt(3, filiereId);
            preparedStatement.executeUpdate();
        }
    }
    public static List<Teacher> getTeachers1() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT id, nom, prenom, age, adresse, anneeNaissance, matricule, email FROM Enseignants";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    int age = resultSet.getInt("age");
                    String adresse = resultSet.getString("adresse");
                    int anneeNaissance = resultSet.getInt("anneeNaissance");
                    String matricule = resultSet.getString("matricule");
                    String email = resultSet.getString("email");

                    teachers.add(new Teacher(id, nom, prenom, age, adresse, anneeNaissance, matricule, email));
                }
            }
        }
        return teachers;
    }

    public static class Teacher {
        private int id;
        private String nom;
        private String prenom;
        private int age;
        private String adresse;
        private int anneeNaissance;
        private String matricule;
        private String email;
public Teacher(int id,String nom) {
	this.id=id;
	this.nom=nom;
	
}
        public Teacher(int id, String nom, String prenom, int age, String adresse, int anneeNaissance, String matricule, String email) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.age = age;
            this.adresse = adresse;
            this.anneeNaissance = anneeNaissance;
            this.matricule = matricule;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public int getAge() {
            return age;
        }

        public String getAdresse() {
            return adresse;
        }

        public int getAnneeNaissance() {
            return anneeNaissance;
        }

        public String getMatricule() {
            return matricule;
        }

        public String getEmail() {
            return email;
        }
    }


    public static class Filiere {
        private int id;
        private String name;

        public Filiere(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
        	return name;
        }
        
        @Override
        public String toString() {
            return name;
        }
    }
    public static void updateCourseData(String courseName, String newCourseName, int newTeacherId, int newFiliereId) throws SQLException {
        Connection connection = getConnection();
        try {
            String query = "UPDATE Cours SET nomCours = ?, idEnseignant = ?, idFiliere = ? WHERE nomCours = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newCourseName);
                preparedStatement.setInt(2, newTeacherId);
                preparedStatement.setInt(3, newFiliereId);
                preparedStatement.setString(4, courseName);

                preparedStatement.executeUpdate();
            }
        } finally {
            closeConnection(connection);
        }
    }

    public static void deleteCourseData(String courseName) throws SQLException {
        Connection connection = getConnection();
        try {
            String query = "DELETE FROM Cours WHERE nomCours = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, courseName);

                preparedStatement.executeUpdate();
            }
        } finally {
            closeConnection(connection);
        }
    }
    public static List<String> getCourses() throws SQLException {
        List<String> courses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String selectQuery = "SELECT nomCours FROM Cours";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String courseName = resultSet.getString("nomCours");
                    courses.add(courseName);
                }
            }
        }

        return courses;
    }
    
    public static class CourseInfo {
        private final String courseName;
        private final String teacherName;
        private final String filiereName;

        public CourseInfo(String courseName, String teacherName, String filiereName) {
            this.courseName = courseName;
            this.teacherName = teacherName;
            this.filiereName = filiereName;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public String getFiliereName() {
            return filiereName;
        }
    }
    // Method to retrieve courses with details (teacher and filiere)
    public static List<CourseInfo> getCoursesWithDetails() throws SQLException {
        List<CourseInfo> coursesInfo = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String selectQuery = "SELECT C.nomCours, E.nom AS enseignantNom, F.nomFiliere " +
                                 "FROM Cours C " +
                                 "JOIN Enseignants E ON C.idEnseignant = E.id " +
                                 "JOIN Filieres F ON C.idFiliere = F.id";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectQuery)) {

                while (resultSet.next()) {
                    String courseName = resultSet.getString("nomCours");
                    String teacherName = resultSet.getString("enseignantNom");
                    String filiereName = resultSet.getString("nomFiliere");

                    CourseInfo courseInfo = new CourseInfo(courseName, teacherName, filiereName);
                    coursesInfo.add(courseInfo);
                }
            }
        }

        return coursesInfo;
    }

    public static List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nom, prenom, age, adresse, anneeNaissance, matricule, email FROM Etudiants");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                int age = resultSet.getInt("age");
                String adresse = resultSet.getString("adresse");
                int anneeNaissance = resultSet.getInt("anneeNaissance");
                String matricule = resultSet.getString("matricule");
                String email = resultSet.getString("email");

                students.add(new Student(id, nom, prenom, age, adresse, anneeNaissance, matricule, email));
            }

        } // The connection is automatically closed here

        return students;
    }

    public static class Student {
        private int id;
        private String nom;
        private String prenom;
        private int age;
        private String adresse;
        private int anneeNaissance;
        private String matricule;
        private String email;

        public Student(int id, String nom, String prenom, int age, String adresse, int anneeNaissance, String matricule, String email) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.age = age;
            this.adresse = adresse;
            this.anneeNaissance = anneeNaissance;
            this.matricule = matricule;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public int getAge() {
            return age;
        }

        public String getAdresse() {
            return adresse;
        }

        public int getAnneeNaissance() {
            return anneeNaissance;
        }

        public String getMatricule() {
            return matricule;
        }

        public String getEmail() {
            return email;
        }
        
            }
    public static class Person {
        private int id;
        private String nom;
        private String prenom;
        private int age;
        private String adresse;
        private int anneeNaissance;
        private String matricule;
        private String email;

        public Person(int id, String nom, String prenom, int age, String adresse, int anneeNaissance, String matricule, String email) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.age = age;
            this.adresse = adresse;
            this.anneeNaissance = anneeNaissance;
            this.matricule = matricule;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public int getAge() {
            return age;
        }

        public String getAdresse() {
            return adresse;
        }

        public int getAnneeNaissance() {
            return anneeNaissance;
        }

        public String getMatricule() {
            return matricule;
        }

        public String getEmail() {
            return email;
        }
    }
    public static void insertExamAndAssociateWithCourse(String courseName, Date examDate) throws SQLException {
        Connection connection = getConnection();

        try {
            // Get the course ID based on the course name
            int courseId = getCourseIdByName(courseName);

            // Insert the exam data into the Examens table
            String insertExamQuery = "INSERT INTO Examens (idCours, dateExamen) VALUES (?, ?)";
            try (PreparedStatement examStatement = connection.prepareStatement(insertExamQuery, Statement.RETURN_GENERATED_KEYS)) {
                examStatement.setInt(1, courseId);
                examStatement.setDate(2, examDate);
                examStatement.executeUpdate();

                // Retrieve the auto-generated key (exam ID)
                try (ResultSet generatedKeys = examStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedExamId = generatedKeys.getInt(1);
                        System.out.println("Exam inserted with ID: " + generatedExamId);
                    } else {
                        System.err.println("Failed to retrieve the generated ID for Exam.");
                    }
                }
            }
        } finally {
            closeConnection(connection);
        }
    }

    private static int getCourseIdByName(String courseName) throws SQLException {
        String query = "SELECT id FROM Cours WHERE nomCours = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, courseName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                } else {
                    throw new SQLException("Course with name '" + courseName + "' not found.");
                }
            }
        }
    }

 public static void modifyExamDate(String examName, Date modifiedDate) throws SQLException {
	    Connection conn = null;
	    PreparedStatement stmt = null;

	    try {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);

	        String sql = "UPDATE Examens SET dateExamen = ? WHERE idCours = (SELECT id FROM Cours WHERE nomCours = ?)";
	        stmt = conn.prepareStatement(sql);
	        stmt.setDate(1, modifiedDate);
	        stmt.setString(2, examName);

	        stmt.executeUpdate();
	    } finally {
	        // Close resources in the finally block
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }
	}


    public static ArrayList<String> getExamList() throws SQLException {
        ArrayList<String> examList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT id, dateExamen FROM Examens";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int examId = rs.getInt("id");
                Date examDate = rs.getDate("dateExamen");
                String examInfo = "Exam " + examId + " - " + examDate.toString();
                examList.add(examInfo);
            }
        } finally {
            // Close resources in the finally block
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return examList;
    }
    
    public static void deleteExamsByCourse(String courseName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "DELETE FROM Examens WHERE idCours = (SELECT id FROM Cours WHERE nomCours = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseName);

            stmt.executeUpdate();
        } finally {
            closeResources(conn, stmt);
        }
    }

    private static void closeResources(Connection conn, Statement stmt) {
        closeResources(conn, stmt, null);
    }

    private static void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public static void insertNote(String studentName, Date examName, double note) throws SQLException {
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Notes (idEtudiant, idExamen, note) VALUES (?, ?, ?)")) {
//
//            // Get student and exam IDs based on names
//            int studentId = getStudentIdByName(studentName);
//            int examId = getExamIdByName(examName);
//
//            // Validate that IDs are found
//            if (studentId == -1 || examId == -1) {
//                // Handle the case where student or exam is not found
//                System.err.println("Student or exam not found.");
//                return;
//            }
//
//            // Set parameters for the prepared statement
//            stmt.setInt(1, studentId);
//            stmt.setInt(2, examId);
//            stmt.setDouble(3, note);
//
//            // Execute the update
//            stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            // Log the exception details
//            e.printStackTrace();
//
//            // Re-throw the exception if necessary
//            throw e;
//        }
//    }
    
    
    public void insertNoteAndAssociateWithExamAndStudent(int idEtudiant, int idExamen, double note) {
    	 Connection conn = null;
    	try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "INSERT INTO Notes (idEtudiant, idExamen, note) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idEtudiant);
                preparedStatement.setInt(2, idExamen);
                preparedStatement.setDouble(3, note);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ajoutez d'autres méthodes CRUD ici en fonction de vos besoins

    
    
 // Get the ID of an exam by name
    private static int getExamIdByName(Date examDate) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT id FROM Examens WHERE dateExamen = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, examDate);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } finally {
            // Close resources in the finally block
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return -1; // Return -1 if not found (handle this case appropriately in your code)
    }



    // Get the ID of a student by name
    private static int getStudentIdByName(String studentName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT id FROM Etudiants WHERE nom = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentName);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } finally {
            // Close resources in the finally block
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return -1; // Return -1 if not found (handle this case appropriately in your code)
    }

    // Get the ID of an exam by name
 // Get the ID of an exam by name
    private static int getExamIdByDate(Date examDate) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT id FROM Examens WHERE dateExamen = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, examDate);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } finally {
            // Close resources in the finally block
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return -1; // Return -1 if not found (handle this case appropriately in your code)
    }


    public static ArrayList<String> getStudentsList() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT nom FROM Etudiants";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            // Use ArrayList to dynamically add student names
            ArrayList<String> studentList = new ArrayList<>();
            while (rs.next()) {
                String studentName = rs.getString("nom");
                studentList.add(studentName);
            }

            return studentList;
        } finally {
            // Close resources in the finally block
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public static void insertSalle(String nomSalle, int capacite) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO SallesDeClasse (nomSalle, capacite) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nomSalle);
                stmt.setInt(2, capacite);
                stmt.executeUpdate();
            }
        }
    }

    public static void updateSalle(int idSalle, String nomSalle, int capacite) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE SallesDeClasse SET nomSalle = ?, capacite = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nomSalle);
                stmt.setInt(2, capacite);
                stmt.setInt(3, idSalle);
                stmt.executeUpdate();
            }
        }
    }

    public static void deleteSalle(int idSalle) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM SallesDeClasse WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idSalle);
                stmt.executeUpdate();
            }
        }
    }

    public static List<String> getAllSalles() throws SQLException {
        List<String> salles = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM SallesDeClasse";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nomSalle = rs.getString("nomSalle");
                        int capacite = rs.getInt("capacite");
                        salles.add("ID: " + id + ", Nom: " + nomSalle + ", Capacité: " + capacite);
                    }
                }
            }
        }
        return salles;
    }

    public static void insertEmplois(String enseignant, String cours, String jourSemaine, String heureDebut, String heureFin, String salle) throws SQLException {
        Connection connection = getConnection();

        try {
            int idEnseignant = getEnseignantIdByName(enseignant);
            int idCours = getCourseIdByName(cours);
            int idSalle = getSalleIdByName(salle);

            String insertEmploisQuery = "INSERT INTO EmploisDuTemps (idEnseignant, idCours, jourSemaine, heureDebut, heureFin, salle_id) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement emploisStatement = connection.prepareStatement(insertEmploisQuery)) {
                emploisStatement.setInt(1, idEnseignant);
                emploisStatement.setInt(2, idCours);
                emploisStatement.setString(3, jourSemaine);
                emploisStatement.setString(4, heureDebut);
                emploisStatement.setString(5, heureFin);
                emploisStatement.setInt(6, idSalle);
                emploisStatement.executeUpdate();
                System.out.println("Emplois added successfully!");
            }
        } finally {
            closeConnection(connection);
        }
    }

    public static void modifyEmplois(int id, String newEnseignant, String newCours, String newJourSemaine, String newHeureDebut, String newHeureFin, String newSalle) throws SQLException {
        Connection connection = getConnection();

        try {
            int idEnseignant = getEnseignantIdByName(newEnseignant);
            int idCours = getCoursIdByName(newCours);
            int idSalle = getSalleIdByName(newSalle);

            String modifyEmploisQuery = "UPDATE EmploisDuTemps SET idEnseignant = ?, idCours = ?, jourSemaine = ?, heureDebut = ?, heureFin = ?, salle_id = ? WHERE id = ?";
            try (PreparedStatement emploisStatement = connection.prepareStatement(modifyEmploisQuery)) {
                emploisStatement.setInt(1, idEnseignant);
                emploisStatement.setInt(2, idCours);
                emploisStatement.setString(3, newJourSemaine);
                emploisStatement.setString(4, newHeureDebut);
                emploisStatement.setString(5, newHeureFin);
                emploisStatement.setInt(6, idSalle);
                emploisStatement.setInt(7, id);
                emploisStatement.executeUpdate();
                System.out.println("Emplois modified successfully!");
            }
        } finally {
            closeConnection(connection);
        }
    }

    public static void deleteEmplois(int id) throws SQLException {
        Connection connection = getConnection();

        try {
            String deleteEmploisQuery = "DELETE FROM EmploisDuTemps WHERE id = ?";
            try (PreparedStatement emploisStatement = connection.prepareStatement(deleteEmploisQuery)) {
                emploisStatement.setInt(1, id);
                emploisStatement.executeUpdate();
                System.out.println("Emplois deleted successfully!");
            }
        } finally {
            closeConnection(connection);
        }
    }

    public static List<String> getAllEmplois() throws SQLException {
        List<String> emplois = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM EmploisDuTemps";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String enseignant = getEnseignantNameById(rs.getInt("idEnseignant"));
                String cours = getCourseIdByName(rs.getInt("idCours"));
                String jourSemaine = rs.getString("jourSemaine");
                String heureDebut = rs.getString("heureDebut");
                String heureFin = rs.getString("heureFin");
                String salle = getSalleNameById(rs.getInt("salle_id"));

                emplois.add("ID: " + id + ", Enseignant: " + enseignant + ", Cours: " + cours
                        + ", Jour de la semaine: " + jourSemaine + ", Heure de début: " + heureDebut
                        + ", Heure de fin: " + heureFin + ", Salle: " + salle);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return emplois;
    }
    public static String getEnseignantNameById(int enseignantId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "SELECT nom FROM Enseignants WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enseignantId);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nom");
            }
        } finally {
            closeConnection(conn);
        }

        return null; // Return null if not found (handle this case appropriately in your code)
    }
    public static String getSalleNameById(int salleId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "SELECT nomSalle FROM SallesDeClasse WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, salleId);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nomSalle");
            }
        } finally {
            closeConnection(conn);
        }

        return null; // Return null if not found (handle this case appropriately in your code)
    }

	private static String getCourseIdByName(int int1) {
		// TODO Auto-generated method stub
		return null;
	}
	public static int getEnseignantIdByName(String enseignantName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "SELECT id FROM Enseignants WHERE nom = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, enseignantName);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } finally {
            closeConnection(conn);
        }

        return -1; // Return -1 if not found (handle this case appropriately in your code)
    }

    public static int getCoursIdByName(String coursName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "SELECT id FROM Cours WHERE nomCours = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, coursName);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } finally {
            closeConnection(conn);
        }

        return -1; // Return -1 if not found (handle this case appropriately in your code)
    }

    public static int getSalleIdByName(String salleName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "SELECT id FROM SallesDeClasse WHERE nomSalle = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, salleName);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } finally {
            closeConnection(conn);
        }

        return -1; // Return -1 if not found (handle this case appropriately in your code)
    }
    
    
    
    public void modifyNote(int idNote, double newNote) throws SQLException {
        String query = "UPDATE Notes SET note = ? WHERE id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, newNote);
            preparedStatement.setInt(2, idNote);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer une note de la base de données
    public void deleteNote(int idNote) {
        try {
            String query = "DELETE FROM Notes WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idNote);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //pour calcler la moyenen et pour afficher 
    public ResultSet getNotesResultSet() throws SQLException {
        String query = "SELECT * FROM Notes";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    // Method to calculate the average of notes
    public double calculateAverage() throws SQLException {
        String query = "SELECT AVG(note) AS average FROM Notes";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getDouble("average");
            }
        }
        return 0.0;
    }
    
    public static List<String> getStudents1() throws SQLException {
        List<String> studentsList = new ArrayList<>();

        // Assuming you have a 'students' table with a column 'student_name'
        String query = "SELECT nom FROM etudiants";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String studentName = resultSet.getString("nom");
                studentsList.add(studentName);
            }
        }

        return studentsList;
    }

    // Method to get the list of teachers from the database
    public static List<String> getTeachers11() throws SQLException {
        List<String> teachersList = new ArrayList<>();

        // Assuming you have a 'teachers' table with a column 'teacher_name'
        String query = "SELECT nom FROM enseignants";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String teacherName = resultSet.getString("nom");
                teachersList.add(teacherName);
            }
        }

        return teachersList;
    }
    public static String getAdminNameByEmail(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Replace "your_admin_table" with the actual table name where admin data is stored
            String query = "SELECT nom FROM administrateurs WHERE email = ?";
         
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Assuming the name column is of type String in the database
                return resultSet.getString("name");
            } else {
                // Handle the case where admin with the given email is not found
                return "John Doe"; // Replace with the actual name or handle accordingly
            }
        } finally {
            // Close the resources in a finally block to ensure they are always closed
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

  
}
