package at.htlgkr.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final SecurityUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(SecurityUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpSession session) {
        session.setAttribute(
                "error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION")
        );
        return "login";
    }

    @GetMapping("/freePage")
    public String freePage(HttpSession session) {
        return "freePage";
    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        }
        return error;
    }
}
