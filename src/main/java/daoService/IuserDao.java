package daoService;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IuserDao {
    public void insertUser(User user) throws SQLException;

    public boolean updateUser(User user) throws SQLException;

    public User selectUser(int id);

    public List<User> selectAllUser();

    public boolean deleteuser(int id) throws SQLException;
}

