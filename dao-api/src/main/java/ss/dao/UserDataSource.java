package ss.dao;

import ss.domain.User;

public interface UserDataSource extends CRUD<User> {
    User getByUsername(String username);
}