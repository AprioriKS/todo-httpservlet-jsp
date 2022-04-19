package todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import todo.exception.DataProcessingException;
import todo.model.User;
import todo.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {
    public int registerEmployee(User employee) {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        String insertUsersSql = "INSERT INTO users"
                + "  (first_name, last_name, username, password) VALUES "
                + " (?, ?, ?, ?);";

        int result = 0;
        try (Connection connection = connectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertUsersSql)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUsername());
            preparedStatement.setString(4, employee.getPassword());
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataProcessingException("Can't add user " + employee, e);
        }
        return result;
    }

}
