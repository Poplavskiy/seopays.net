package seopays.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import seopays.constraints.UserValidator;
import seopays.domain.User;
import seopays.service.UserService;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;


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

            if(userService.addUser(user)) // check is existed account?
                return new ResponseEntity<String>("ok", HttpStatus.OK);
            else
                return new ResponseEntity<String>("error", HttpStatus.OK);
        }
    }
}
