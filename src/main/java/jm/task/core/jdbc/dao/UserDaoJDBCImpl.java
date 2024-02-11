package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = util.getConnect(); Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE `kata`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT(3) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8\n" +
                    "COLLATE = utf8_unicode_ci;");

        } catch (SQLException e) {
            System.out.println("Таблица уже существует");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = util.getConnect(); Statement statement = connection.createStatement()) {
            statement.execute("drop table kata.users");
        } catch (SQLException e) {
            System.out.println("Таблица уже удалена");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.getConnect();
             PreparedStatement statement = connection.prepareStatement("insert into kata.users (name, lastName, age) values(?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = util.getConnect();
             PreparedStatement statement = connection.prepareStatement("delete from kata.users where id=?")) {
            statement.setInt(1, (int) id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = util.getConnect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM kata.users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Таблицы не существует");
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = util.getConnect();
             Statement statement = connection.createStatement()) {
            statement.execute("delete from kata.users");
        } catch (SQLException e) {
            System.out.println("Таблицы не существует");
        }
    }
}
