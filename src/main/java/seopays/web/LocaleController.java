package seopays.web;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import seopays.util.UrlFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleController {


    void setLocale(HttpServletRequest req, HttpServletResponse resp) {

        String lang = (String)req.getAttribute(UrlFilter.LANGUAGE_CODE_ATTRIBUTE_NAME);
        if(lang == null) lang = "en";

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(req);
        localeResolver.setLocale(req, resp, StringUtils.parseLocaleString(lang));
    }
}
