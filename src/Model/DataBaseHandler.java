package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseHandler {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/estbm_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static boolean registerUser(String nom, String prenom, int age, String adresse, int anneeNaissance, String matricule, String username, char[] password, String userType) {
        String insertQuery = "INSERT INTO " + userType + " (nom, prenom, age, adresse, anneeNaissance, matricule, email, motDePasse) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, adresse);
            preparedStatement.setInt(5, anneeNaissance);
            preparedStatement.setString(6, matricule);
            preparedStatement.setString(7, username);
            preparedStatement.setString(8, new String(password));

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Method to check user login credentials against the database
    public static boolean checkLogin(String username, char[] password, String userType) {
        String selectQuery = "SELECT * FROM " + userType + " WHERE email = ? AND motDePasse = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, new String(password));

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}
