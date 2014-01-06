package seopays.web;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import seopays.util.UrlFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleController {


    String getLocaleLang(HttpServletRequest req) {
        String lang = (String)req.getAttribute(UrlFilter.LANGUAGE_CODE_ATTRIBUTE_NAME);
        if(lang == null) lang = "en";

        return lang;
    }

    void setLocale(HttpServletRequest req, HttpServletResponse resp) {

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(req);
        localeResolver.setLocale(req, resp, StringUtils.parseLocaleString(getLocaleLang(req)));
    }
}
