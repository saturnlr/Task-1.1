package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    @Override
    public void createUsersTable() {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Users (" +
                "Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "Name VARCHAR(64), " +
                "Last_name VARCHAR(64), " +
                "Age INT(255))")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DROP TABLE IF EXISTS Users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("INSERT INTO Users(Name, Last_name, Age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DELETE FROM Users WHERE Id = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("SELECT * FROM Users")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("Last_name"));
                user.setAge(resultSet.getByte("Age"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("TRUNCATE TABLE Users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
