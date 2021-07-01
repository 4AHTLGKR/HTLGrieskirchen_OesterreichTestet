package at.htlgkr.htltestet.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String screeningStationId = request.getParameter("screeningStationId");
        request.getSession().setAttribute("screeningStationId", screeningStationId);
        System.out.println("Filter" + screeningStationId);
        // You can do your stuff here
        return super.attemptAuthentication(request, response);
    }
}
