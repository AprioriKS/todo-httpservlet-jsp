package todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import todo.exception.DataProcessingException;
import todo.model.Login;
import todo.util.ConnectionUtil;

public class LoginDaoImpl implements LoginDao {
    public boolean validate(Login login) {
        boolean status = false;

        ConnectionUtil connectionUtil = new ConnectionUtil();
        try (Connection connection = connectionUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("select * from users where username = ? "
                            + "and password = ? ")) {
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            throw new DataProcessingException("Can't login " + login, e);
        }
        return status;
    }
}
