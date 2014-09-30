package ss.bean;

import ss.domain.User;
import ss.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "users")
@ViewScoped
public class UsersBean implements Serializable {
    @ManagedProperty("#{userService}")
    private UserService userService;

    public List<User> getList() {
        return userService.list();
    }

    /* SERVICES */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}