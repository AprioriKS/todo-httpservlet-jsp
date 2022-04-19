package todo.dao;

import todo.model.Login;

public interface LoginDao {
    boolean validate(Login login);
}
