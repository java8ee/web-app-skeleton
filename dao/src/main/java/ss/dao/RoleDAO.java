package ss.dao;

import org.springframework.stereotype.Repository;
import ss.domain.Role;

@Repository
public class RoleDAO extends AbstractDAO<Role> implements RoleDataSource {
    public RoleDAO() {
        super(Role.class);
    }
}