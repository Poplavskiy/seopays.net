package seopays.constraints;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import seopays.domain.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean supports(Class aClass) {

        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        String username = user.getUsername();
        String password = user.getPassword();

        if(username != null) {

            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(username);

            if(username.isEmpty() || !matcher.matches() ||
                    username.length() > 50) {
                errors.rejectValue("username", "your_error_code");
            }

            if(password == null || password.isEmpty() || password.length() > 50) {
                errors.rejectValue("password", "your_error_code");
            }
        } else {
            errors.rejectValue("username", "your_error_code");
        }
    }
}
