package ss.bean;

import ss.service.AuthService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 548560872660875433L;

    private String username;
    private String password;

    private String person;

    @ManagedProperty("#{authService}")
    private AuthService authService;

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
        boolean hasPermission = authService.hasPermission(username, password);
        if (hasPermission) {
            return "success?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage("Incorrect login / password");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
}