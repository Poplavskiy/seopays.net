package seopays.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import seopays.service.UserService;
import seopays.util.CaptchaCaptureFilter;

import java.util.ArrayList;
import java.util.List;



public class CrowdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CaptchaCaptureFilter captchaCaptureFilter;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (userService.login(username, password) &&
            captchaCaptureFilter.isRightCaptcha()) {

            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);

            return auth;
        } else {
            return null;
        }
    }
}