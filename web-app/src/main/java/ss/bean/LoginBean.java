package ss.bean;

import ss.service.AuthService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 2949043313231508101L;

    private String username;
    private String password;

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
    public String login() throws ServletException, IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) external.getRequest()).getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward((ServletRequest) external.getRequest(), (ServletResponse) external.getResponse());
        context.responseComplete();
        return null;
    }
}