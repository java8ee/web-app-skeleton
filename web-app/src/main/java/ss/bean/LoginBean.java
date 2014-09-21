package ss.bean;

import ss.service.AuthService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 548560872660875433L;

    private String username;
    private String password;

    private String person;

    @ManagedProperty("#{authService}")
    private transient AuthService authService;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerson() {
        return authService.getPerson(username);
    }

    /* SERVICES */
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    /* ACTIONS */
    public String login() {
        return authService.hasPermission(username, password) ? "success" : null;
    }

    public String logout() {
        reset();
        return "/";
    }

    private void reset() {
        username = null;
        password = null;
        person = null;
    }
}