package seopays.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import seopays.constraints.UserValidator;
import seopays.domain.User;
import seopays.service.UserService;
import seopays.util.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;


@Controller
public class AccountController extends LocaleController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSender ms;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("user") User user, ModelMap model,
                           BindingResult result, HttpServletRequest req,
                           HttpServletResponse resp) {

        String lang = getLocaleLang(req);
        Locale locale = new Locale(lang);

        setLocale(req, resp);

        ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
        bean.setBasename("messages");
        bean.setDefaultEncoding("UTF-8");

        String regfieldserror = bean.getMessage("label.regfieldserror", null, locale);
        String accountalreadyexistserror = bean.getMessage("label.accountalreadyexistserror", null, locale);
        String captcharror = bean.getMessage("label.captcharror", null, locale);

        model.addAttribute("lang", lang);

        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);

        if (result.hasErrors()) {

            model.addAttribute("error", regfieldserror);
            model.addAttribute("username", user.getUsername());

            return "registration";
        }
        else {

            HttpSession session = req.getSession(false);
            Object val = session.getAttribute("captchaToken");

            String captcha_key = val != null ? val.toString() : null;
            String userCaptchaResponse = req.getParameter("captcha");


            if(captcha_key.equals(userCaptchaResponse) &&
                (!captcha_key.isEmpty() && !userCaptchaResponse.isEmpty())) {

                /*TODO Set enabled to false, and restrictions for user role.*/

                if(userService.addUser(user)) { // check is existed account?

                    /*TODO Create templates for emails*/

                    ms.send(user.getUsername(), "Registration", "Hello world!!!");

                    String regisration_success = bean.getMessage("label.regisrationsuccess", null, locale);
                    model.addAttribute("message", regisration_success);



                    return "registration_success";

                } else {

                    model.addAttribute("error", accountalreadyexistserror);
                    model.addAttribute("username", user.getUsername());

                    return "registration";
                }

            }  else {

                model.addAttribute("error", captcharror);
                model.addAttribute("username", user.getUsername());

                return "registration";
            }
        }
    }


    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String getRegistration(ModelMap model,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        String lang = getLocaleLang(request);

        setLocale(request, response);

        model.addAttribute("lang", lang);

        return "registration";
    }


    /*TODO Implement international form*/
    /*TODO Save login after wrong auth*/
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLogin(ModelMap model,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        String lang = getLocaleLang(request);
        setLocale(request, response);
        model.addAttribute("lang", lang);

        return "login";
    }




    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("company", "SEO pays");
        return "hello";
    }


}
