package daoServiceImplementation;

import daoService.IuserDao;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IuserDao {

    private static final String INSERT_USER_SQL ="INSERT INTO users" + "(name,email,country,password,address,surname) VALUES"+ "(?,?,?,?,?,?);";
    private static final String SELECT_USER_BY_ID="select id,name,email,country,address,surname from users where id=?;";
    private static final String SELECT_ALL_USER="select id,  name,email,country,address,surname from users;";
    private static final String DELETE_USER_SQL="delete from  users where id=?;";
    private static final String UPDATE_USER_SQL="update users set name=?,email=?,country=?,password=?, address =?,surname=? where id=?;";
    protected Connection getConnnection(){
        Connection connection=null;
        try{
             String jdbcURL="jdbc:mysql://127.0.0.1:3306/demoi";
             String jdbcname ="root";
             String jdbcpassword="root";
             connection= DriverManager.getConnection(jdbcURL,jdbcname,jdbcpassword);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void insertUser(User user ) throws SQLException {
        try(Connection connection=getConnnection();
            PreparedStatement preparedStatement= connection.prepareStatement(INSERT_USER_SQL)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getSurname());
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        }
        public boolean updateUser(User user) throws SQLException {
            boolean rowupdated;
            try(Connection connection= getConnnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);){
                statement.setString(1, user.getName());
                statement.setString(2,user.getEmail());
                statement.setString(3,user.getCountry());
                statement.setInt(4,user.getId());
                statement.setString(5, user.getPassword());
                statement.setString(6, user.getAddress());
                statement.setString(7, user.getSurname());
                rowupdated=statement.executeUpdate()>0;
            }

return rowupdated;

    }
    public User selectUser(int id) {
        User user =null;
        try(Connection connection= getConnnection();

       PreparedStatement preparedStatement= connection.prepareStatement(SELECT_USER_BY_ID); ){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
          ResultSet rs = preparedStatement.executeQuery();
          while (rs.next()){
            String name=  rs.getString("name");
            String email=rs.getString("email");
            String country= rs.getString("country");

            String address=rs.getString("address");
            String surname= rs.getString("surname");
            user = new User(id,name,email,country,address,surname);
          }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user;}
    public List<User> selectAllUser (){
        List<User> users= new ArrayList<>();
        try{
            Connection connection=getConnnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_USER);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id= rs.getInt("id");
                String name=rs.getString("name");
                String  email=rs.getString("email");
                String country=rs.getString("country");

                String address= rs.getString("address");
                String surname=rs.getString("surname");
                users.add(new User(id,name,email,country,address,surname));

            }
        } catch (SQLException e) {
            e.printStackTrace();
}
        return users;
    }
    public boolean deleteuser(int id ) throws SQLException {
        boolean rowdeleted;
        try (Connection connection=getConnnection();
        PreparedStatement statement= connection.prepareStatement(DELETE_USER_SQL);){
            statement.setInt(1,id);
            rowdeleted= statement.executeUpdate() >0;

        }
        return rowdeleted;
    }
}
