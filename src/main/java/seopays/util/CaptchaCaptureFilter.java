package seopays.util;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CaptchaCaptureFilter extends OncePerRequestFilter {

    private String userCaptchaResponse;
    private HttpServletRequest request;

    public static String getToken(HttpSession session) {
        Object val = session.getAttribute("captchaToken");

        return val != null ? val.toString() : null;
    }


    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                 FilterChain chain) throws IOException, ServletException {

        logger.debug("Captcha capture filter");

        // Assign values only when user has submitted a Captcha value.
        // Without this condition the values will be reset due to redirection
        // and CaptchaVerifierFilter will enter an infinite loop

        if(req != null) {
            HttpSession session = req.getSession();

            String val = getToken(session);
            if(val != null) {
                String var = val;
            }
        }

        if (req.getParameter("captchaToken") != null) {
            request = req;
            userCaptchaResponse = req.getParameter("captchaToken");
        }

        logger.debug("userResponse: " + userCaptchaResponse);

        // Proceed with the remaining filters
        chain.doFilter(req, res);
    }

    public String getUserCaptchaResponse() {
        return userCaptchaResponse;
    }

    public void setUserCaptchaResponse(String userCaptchaResponse) {
        this.userCaptchaResponse = userCaptchaResponse;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}