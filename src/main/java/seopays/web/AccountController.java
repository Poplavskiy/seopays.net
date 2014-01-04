package seopays.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import seopays.constraints.UserValidator;
import seopays.domain.User;
import seopays.service.UserService;
import seopays.util.MailSender;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;


@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSender ms;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public User addContact(@ModelAttribute("user") User user, ModelMap model,
                           BindingResult result) {

        Locale locale = LocaleContextHolder.getLocale();

        ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
        bean.setBasename("messages");
        bean.setDefaultEncoding("UTF-8");
        String regfieldserror = bean.getMessage("label.regfieldserror", null, locale);
        String accountalreadyexistserror = bean.getMessage("label.accountalreadyexistserror", null, locale);

        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);

        if (result.hasErrors()) {

            model.addAttribute("error", regfieldserror);
            model.addAttribute("username", user.getUsername());

            return user;
        }
        else {

            if(userService.addUser(user)) { // check is existed account?

                ms.send(user.getUsername(), "Registration", "Hello world!!!");

                return user;

            } else {

                model.addAttribute("error", accountalreadyexistserror);
                model.addAttribute("username", user.getUsername());

                return user;
            }

        }
    }





    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String getRegistration(ModelMap model) {
//        model.addAttribute("company", "SEO pays");

        return "registration";
    }




    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("company", "SEO pays");
        return "hello";
    }


}
