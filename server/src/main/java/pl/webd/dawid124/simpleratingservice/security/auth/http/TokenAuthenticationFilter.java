package pl.webd.dawid124.simpleratingservice.security.auth.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.webd.dawid124.simpleratingservice.security.auth.AuthenticationService;
import pl.webd.dawid124.simpleratingservice.security.common.TokenHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    public static final String WEBSOCKET_PREFIX = "/ws";
    private final Log logger = LogFactory.getLog(this.getClass());

    private AuthenticationService authenticationService;
    private TokenHelper tokenHelper;

    public TokenAuthenticationFilter(AuthenticationService authenticationService, TokenHelper tokenHelper) {
        this.authenticationService = authenticationService;
        this.tokenHelper = tokenHelper;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String authToken;
        if (isRequestForWebSocketHandshake(request)) {
            authToken = tokenHelper.getTokenFromParam(request);
        } else {
            authToken = tokenHelper.getTokenFromHeader(request);
        }

        authenticationService.assignPermissionByTokenAndDeviceId(authToken);

        chain.doFilter(request, response);
    }

    private boolean isRequestForWebSocketHandshake(HttpServletRequest request) {
        return WEBSOCKET_PREFIX.equals(request.getServletPath());
    }
}