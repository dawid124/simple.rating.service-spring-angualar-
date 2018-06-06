package pl.webd.dawid124.simpleratingservice.security.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.webd.dawid124.simpleratingservice.security.common.TokenHelper;
import pl.webd.dawid124.simpleratingservice.security.model.SecurityUser;
import pl.webd.dawid124.simpleratingservice.security.service.impl.CustomUserDetailsService;


@Component
public class AuthenticationService {

    private TokenHelper tokenHelper;
    private CustomUserDetailsService userDetailsService;

    public AuthenticationService(TokenHelper tokenHelper, CustomUserDetailsService userDetailsService) {
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    public boolean assignPermissionByTokenAndDeviceId(String requestToken) {
        if (requestToken == null) {
            return false;
        }

        String username = tokenHelper.getUsernameFromToken(requestToken);

        return username != null && checkAndAssignAuthentication(requestToken, username);
    }

    private boolean checkAndAssignAuthentication(String requestToken, String username) {
        SecurityUser securityUser = userDetailsService.loadUserByUsername(username);

        if (tokenHelper.validateToken(requestToken, securityUser) && securityUser.isEnabled()) {

            TokenBasedAuthentication authentication = new TokenBasedAuthentication(securityUser);
            authentication.setToken(requestToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        }

        return false;
    }

}
