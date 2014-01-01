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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public HttpEntity<String> addContact(@ModelAttribute("user") User user,
                             BindingResult result) {

        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return new ResponseEntity<String>("error", HttpStatus.OK);
        }
        else {

            if(userService.addUser(user)) { // check is existed account?


                ms.send(user.getUsername(), "Registration", "Hello world!!!");

                return new ResponseEntity<String>("ok", HttpStatus.OK);

            } else
                return new ResponseEntity<String>("error", HttpStatus.OK);
        }
    }





    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("company", "SEO pays");
        return "hello";
    }


}
