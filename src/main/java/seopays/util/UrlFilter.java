package seopays.util;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlFilter implements Filter {

    private static final Pattern localePattern = Pattern.compile("^/([a-z]{2})(/.*)?");
    public static final String LANGUAGE_CODE_ATTRIBUTE_NAME = UrlFilter.class.getName() + ".language";


    public void init(FilterConfig filterConfig) throws ServletException {}


    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest)servletRequest;
        final String url = request.getRequestURI().substring(request.getContextPath().length());
        final Matcher matcher = localePattern.matcher(url);
        if (matcher.matches()) {

            request.setAttribute(LANGUAGE_CODE_ATTRIBUTE_NAME, matcher.group(1));
            request.getRequestDispatcher(matcher.group(2) == null ? "/" :
                    matcher.group(2)).forward(servletRequest, servletResponse);
        }
        else filterChain.doFilter(servletRequest, servletResponse);

    }

    public void destroy() {}
}