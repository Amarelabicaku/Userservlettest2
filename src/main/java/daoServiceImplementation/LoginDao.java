package daoServiceImplementation;


import model.User;
import model.UserDhePoste;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {
    private static final String checkEmail = "Select *from users where email=? and password=? ";


    protected Connection getConnnection() {
        Connection connection = null;
        try {
            String jdbcURL = "jdbc:mysql://127.0.0.1:3306/demoi";
            String jdbcname = "root";
            String jdbcpassword = "root";
            connection = DriverManager.getConnection(jdbcURL, jdbcname, jdbcpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public boolean checkEmail(String email, String password) {


        try (Connection connection = getConnnection();
             PreparedStatement preparedStatement = connection.prepareStatement(checkEmail)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }
}
