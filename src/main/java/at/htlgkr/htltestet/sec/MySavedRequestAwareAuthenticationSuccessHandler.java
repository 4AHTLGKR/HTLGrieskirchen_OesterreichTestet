package at.htlgkr.htltestet.sec;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        return "/internal/barcode_reading";
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        final String screeningStationId = request.getParameter("screeningStationId");
        request.getSession().setAttribute("screeningStationId", screeningStationId);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
