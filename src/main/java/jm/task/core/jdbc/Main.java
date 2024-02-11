package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Andrey", "Zaikin", (byte) 26);
        userDao.saveUser("Arseny", "Solotyushin", (byte) 27);
        userDao.saveUser("Daniil", "Saltykov", (byte) 27);
        userDao.saveUser("Vlad", "Zayc", (byte) 27);
        List<User> userList = userDao.getAllUsers();
        System.out.println(userList);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
