package seopays.util;

import org.springframework.security.web.DefaultRedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class QueryStringPropagateRedirectStrategy extends DefaultRedirectStrategy {

    public void sendRedirect(HttpServletRequest request,
                             HttpServletResponse response, String url) throws IOException {

        String urlWithOriginalQueryString = "/"+CaptchaCaptureFilter.lang + url + "?" + "error=true";
        super.sendRedirect(request, response, urlWithOriginalQueryString );
    }

}