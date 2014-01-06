package seopays.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloController extends LocaleController {

    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    public String printWelcome(ModelMap model, HttpServletRequest req,
                               HttpServletResponse resp) {

        String lang = getLocaleLang(req);

        setLocale(req, resp);

        model.addAttribute("lang", lang);
        model.addAttribute("company", "SEO pays");

        return "hello";
    }
}