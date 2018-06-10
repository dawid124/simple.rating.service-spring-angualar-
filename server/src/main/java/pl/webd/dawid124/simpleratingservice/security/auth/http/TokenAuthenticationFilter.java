package pl.webd.dawid124.simpleratingservice.security.auth.http;

import org.springframework.web.filter.OncePerRequestFilter;
import pl.webd.dawid124.simpleratingservice.security.auth.AuthenticationService;
import pl.webd.dawid124.simpleratingservice.security.common.TokenHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationService authenticationService;
    private TokenHelper tokenHelper;

    public TokenAuthenticationFilter(AuthenticationService authenticationService, TokenHelper tokenHelper) {
        this.authenticationService = authenticationService;
        this.tokenHelper = tokenHelper;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String authToken = tokenHelper.getTokenFromHeader(request);
        authenticationService.assignPermissionByTokenAndDeviceId(authToken);

        chain.doFilter(request, response);
    }
}