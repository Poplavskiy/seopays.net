package seopays.util;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UrlFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest hsRequest = (HttpServletRequest) request;

        String url = hsRequest.getRequestURI().substring(hsRequest.getContextPath().length());

        //This is read from a .properties file actually, but for now it's ok
        String supportedLanguages = "/ru/,/en/";
        List<String> listOfLanguages = Arrays.asList(supportedLanguages.split(","));

        //If the URL already contains any of the allowed language identifiers, we continue with the original flow
        for(String language : listOfLanguages)
        {
            if(StringUtils.startsWithIgnoreCase(url, language))
            {
                filterChain.doFilter(request, response);
            }
        }

        //If not, we just have to add /en/ at start of the URL
        RequestDispatcher dispatcher = request.getRequestDispatcher("/en/".concat(url));
        dispatcher.forward(request, response);
    }

    public void destroy() {}
}