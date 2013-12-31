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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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

            if(userService.addUser(user)) { // check is existed account?

                sendEmail();

                return new ResponseEntity<String>("ok", HttpStatus.OK);

            } else
                return new ResponseEntity<String>("error", HttpStatus.OK);
        }
    }


    protected void sendEmail() {

        final String username = "office@seopays.net";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "seopays.net");
        props.put("mail.smtp.port", "25");

        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "*");


        javax.mail.Session session = javax.mail.Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("office@seopays.net"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("vladimir.sidorenko@dune-hd.com"));
            message.setSubject("Registration new account");
            message.setText("Hello, User,"
                    + "\n\n you has been registered in our website seopays.net!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("company", "SEO pays");
        return "hello";
    }


}
