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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String addContact(@ModelAttribute("user") User user, ModelMap model,
                           BindingResult result, HttpServletRequest req) {

        Locale locale = LocaleContextHolder.getLocale();

        ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
        bean.setBasename("messages");
        bean.setDefaultEncoding("UTF-8");
        String regfieldserror = bean.getMessage("label.regfieldserror", null, locale);
        String accountalreadyexistserror = bean.getMessage("label.accountalreadyexistserror", null, locale);
        String captcharror = bean.getMessage("label.captcharror", null, locale);



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
