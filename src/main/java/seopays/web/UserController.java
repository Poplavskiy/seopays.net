package seopays.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import seopays.domain.User;
import seopays.service.UserService;

import java.util.Map;

@Controller
public class UserController {
/*

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("user") User user,
                             BindingResult result) {

        userService.addUser(user);

        return "redirect:/user/index";
    }



    @RequestMapping("/user/index")
    public String listUsers(Map<String, Object> map) {

        map.put("user", new User());

        return "user";
    }


*/



}
