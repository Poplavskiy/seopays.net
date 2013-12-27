package seopays.web;

import com.github.cage.Cage;
import com.github.cage.GCage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class CaptchaController {

    private static final Cage cage = new GCage();

    protected static void markTokenUsed(HttpSession session, boolean used) {
        session.setAttribute("captchaTokenUsed", used);
    }

    public static void generateToken(HttpSession session) {
        String token = cage.getTokenGenerator().next();

        session.setAttribute("captchaToken", token);
        markTokenUsed(session, false);
    }

    protected static boolean isTokenUsed(HttpSession session) {
        return !Boolean.FALSE.equals(session.getAttribute("captchaTokenUsed"));
    }


    public static String getToken(HttpSession session) {
        Object val = session.getAttribute("captchaToken");

        return val != null ? val.toString() : null;
    }


    @RequestMapping(value = "/captcha",method = RequestMethod.GET)
    public void printCaptcha(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        generateToken(session);

        String token = session != null ? getToken(session) : null;
        if (token == null || isTokenUsed(session)) {

            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Captcha not found.");

            return;
        }

        setResponseHeaders(resp);
        markTokenUsed(session, true);
        cage.draw(token, resp.getOutputStream());
    }


    protected void setResponseHeaders(HttpServletResponse resp) {
        resp.setContentType("image/" + cage.getFormat());
        resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        resp.setDateHeader("Last-Modified", time);
        resp.setDateHeader("Date", time);
        resp.setDateHeader("Expires", time);
    }


}
