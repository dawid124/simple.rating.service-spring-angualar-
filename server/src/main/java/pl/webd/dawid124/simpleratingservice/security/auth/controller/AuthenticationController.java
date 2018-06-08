package pl.webd.dawid124.simpleratingservice.security.auth.controller;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.webd.dawid124.simpleratingservice.security.auth.TokenBasedAuthentication;
import pl.webd.dawid124.simpleratingservice.security.auth.http.JwtAuthenticationRequest;
import pl.webd.dawid124.simpleratingservice.security.common.TokenHelper;
import pl.webd.dawid124.simpleratingservice.security.model.PasswordChanger;
import pl.webd.dawid124.simpleratingservice.security.model.SecurityUser;
import pl.webd.dawid124.simpleratingservice.security.model.UserData;
import pl.webd.dawid124.simpleratingservice.security.service.impl.CustomUserDetailsService;
import pl.webd.dawid124.simpleratingservice.users.model.UserModel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping( value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE )
public class AuthenticationController {

    private TokenHelper tokenHelper;
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService userDetailsService;

    public AuthenticationController(TokenHelper tokenHelper, AuthenticationManager authenticationManager,
                                    CustomUserDetailsService userDetailsService) {
        this.tokenHelper = tokenHelper;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
            throws AuthenticationException {

        try {
            Authentication authentication = getAuthenticate(authenticationRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            String token = tokenHelper.generateToken(securityUser.getUsername());

            return ResponseEntity.ok(token);
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    private Authentication getAuthenticate(@RequestBody JwtAuthenticationRequest authRequest) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerNewUser(@RequestBody UserModel userModel)
            throws AuthenticationException, IOException {

        if (!userModel.isValid()) {
            return new ResponseEntity<>("USERNAME_OR_EMAIL_NOT_VALID", HttpStatus.BAD_REQUEST);
        }

        return userDetailsService.createAndReturnResponseEntity(userModel);
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PREMIUM_USER','ROLE_ADMIN')")
    public ResponseEntity<?> authenticate(Principal user) {

        SecurityUser securityUser = (SecurityUser) ((TokenBasedAuthentication) user).getPrincipal();

        UserData userData = new UserData(securityUser.getUsername(), securityUser.getEmail(), securityUser.getAuthoritie());

        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PREMIUM_USER','ROLE_ADMIN')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
        Map<String, String> result = new HashMap<>();
        result.put( "result", "success" );
        return ResponseEntity.accepted().body(result);
    }
}