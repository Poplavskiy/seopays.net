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
    private String userCaptchaCode;


    public static String getToken(HttpSession session) {
        Object val = session.getAttribute("captchaToken");

        return val != null ? val.toString() : null;
    }


    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                 FilterChain chain) throws IOException, ServletException {

        if (req.getParameter("j_captcha") != null) {

            userCaptchaResponse = req.getParameter("j_captcha");

            HttpSession session = req.getSession();

            userCaptchaCode = getToken(session);
        }

        chain.doFilter(req, res);
    }


    public boolean isRightCaptcha() {

        return (userCaptchaCode != null &&  userCaptchaResponse != null) &&
                userCaptchaCode.equals(userCaptchaResponse) &&
                (!userCaptchaCode.isEmpty() && !userCaptchaResponse.isEmpty());
    }
}