package pl.webd.dawid124.simpleratingservice.security.service.impl;

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
import pl.webd.dawid124.simpleratingservice.users.mapper.UserMapper;
import pl.webd.dawid124.simpleratingservice.users.model.UserModel;


@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

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
}
