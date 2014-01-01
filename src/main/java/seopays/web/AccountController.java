package seopays.web;

import org.springframework.beans.factory.annotation.Autowired;
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


@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSender ms;

    @ResponseBody
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("user") User user,
                             BindingResult result) {

        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return "registration";
        }
        else {

            if(userService.addUser(user)) { // check is existed account?


                ms.send(user.getUsername(), "Registration", "Hello world!!!");

                return "registration";

            } else
                return "registration";
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
