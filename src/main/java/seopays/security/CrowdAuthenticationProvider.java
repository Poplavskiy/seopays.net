package seopays.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import seopays.util.CaptchaCaptureFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CrowdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CaptchaCaptureFilter captchaCaptureFilter;



    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        String response = captchaCaptureFilter.getUserCaptchaResponse();

        if (name.equals("admin") && password.equals("system")) {
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);

            return auth;
        } else {
            return null;
        }
    }
}