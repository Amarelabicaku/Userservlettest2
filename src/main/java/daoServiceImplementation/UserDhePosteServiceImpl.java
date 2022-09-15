package daoServiceImplementation;

import daoService.UserDhePosteService;
import model.User;
import model.UserDhePoste;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDhePosteServiceImpl implements UserDhePosteService {

    private static final String Select_Post = "Select *from users JOIN postim1 ON users.id=postim1.id;";
    private static final String Select_Userbyemail = "Select user.id from users user where user.email=?";
    private static final String doPost = "INSERT INTO postim1 (description, date, id) VALUES (?, ?, ?);";
    private static final String UPDATEpost="update postim1 set description=? where postime_id=?;";
    private static final String DELETEpost="Delete  from  postim1 where postime_id=?;";
    private static final String Selectpostby="Select description from postim1 where postime_id=?";
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

    @Override
    public List<UserDhePoste> getAllPosts() {
        List<UserDhePoste> userspostim1 = new ArrayList<>();
        try {
            Connection connection = getConnnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Select_Post);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int postime_id = rs.getInt("postime_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String description = rs.getString("description");
                String address = rs.getString("address");
                Date date = rs.getDate("date");
                String surname =rs.getString("surname");
                userspostim1.add(new UserDhePoste(id, postime_id, name, email, country, description, address, date,surname));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userspostim1;
    }


    public void doposts(UserDhePoste userDhePoste, String email) {
        try (Connection connection = getConnnection();
             PreparedStatement preparedStatement = connection.prepareStatement(doPost)) {
            UserDhePosteServiceImpl userDhePosteService=new UserDhePosteServiceImpl();
        User user = userDhePosteService.getUserByEmail(email);
        if (user != null){
            preparedStatement.setString(1, userDhePoste.getDescription());
            preparedStatement.setDate(2,userDhePoste.getDate());
            preparedStatement.setInt(3, user.getId());


            preparedStatement.executeUpdate();
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByEmail(String email) {
        User user = null;

        try (Connection connection = getConnnection();

             PreparedStatement preparedStatement = connection.prepareStatement(Select_Userbyemail);) {
            preparedStatement.setString(1, email);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                user = new User(id);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public boolean updatepost(UserDhePoste userDhePoste) throws SQLException {
        boolean rowupdated = false;
        Connection connection= null;
        try{
            connection= getConnnection();
            PreparedStatement statement = connection.prepareStatement(UPDATEpost);
            statement.setInt(1,userDhePoste.getPostime_id());
            statement.setString(2, userDhePoste.getDescription());
            rowupdated=statement.executeUpdate()>0;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return rowupdated;
    }
    public boolean Deletepost(int postime_id ) throws SQLException {
        boolean rowdeleted2;
        try (Connection connection=getConnnection();
             PreparedStatement statement= connection.prepareStatement(DELETEpost);){
            statement.setInt(1,postime_id);
            rowdeleted2= statement.executeUpdate() >0;

        }
        return rowdeleted2;
    }
    public UserDhePoste selectpostby(int postime_id) {
        UserDhePoste userDhePoste =null;
        try(Connection connection= getConnnection();

            PreparedStatement preparedStatement= connection.prepareStatement(Selectpostby); ){
            preparedStatement.setInt(1,postime_id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
               String description = rs.getString("description");
                userDhePoste = new UserDhePoste(postime_id,description);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userDhePoste;
    }
}