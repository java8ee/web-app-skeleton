package ss;

import ss.bean.LoginBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest http = (HttpServletRequest) request;
        LoginBean bean = (LoginBean) http.getSession().getAttribute("login");
        if (bean == null || !bean.isLoggedIn()) {
            String path = http.getContextPath();
            ((HttpServletResponse) response).sendRedirect(path + "/login.xhtml");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nothing do
    }

    @Override
    public void destroy() {
        // Nothing do
    }
}