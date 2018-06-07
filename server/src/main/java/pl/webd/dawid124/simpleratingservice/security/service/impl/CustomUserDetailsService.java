package pl.webd.dawid124.simpleratingservice.security.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.security.model.Role;
import pl.webd.dawid124.simpleratingservice.security.model.SecurityUser;
import pl.webd.dawid124.simpleratingservice.users.maper.UserMapper;
import pl.webd.dawid124.simpleratingservice.users.model.UserModel;


@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;


    public CustomUserDetailsService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No userEngine found with username '%s'.", username));
        } else {
            return new SecurityUser(user);
        }
    }

    public ResponseEntity<?> createAndReturnResponseEntity(UserModel user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(Role.ROLE_USER);

        try {
            userMapper.insertUser(user);
        } catch (NonTransientDataAccessException ex) {
            return new ResponseEntity<>("EMAIL_EXISTS", HttpStatus.CONFLICT);
        }


        return ResponseEntity.ok("CREATED");
    }

    public void changePassword(String oldPassword, String newPassword) {

//        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
//        String username = currentUser.getName();
//
//        if (authenticationManager != null) {
//            LOGGER.debug("Re-authenticating userEngine '" + username + "' for password change request.");
//
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
//        } else {
//            LOGGER.debug("No authentication manager set. can't change Password!");
//
//            return;
//        }
//
//        LOGGER.debug("Changing password for userEngine '" + username + "'");
//        userMapper.setPasswordByEmail(passwordEncoder.encode(newPassword), username);
    }
}
