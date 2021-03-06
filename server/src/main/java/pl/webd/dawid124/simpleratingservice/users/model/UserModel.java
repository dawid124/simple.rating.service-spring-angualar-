package pl.webd.dawid124.simpleratingservice.users.model;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;
import pl.webd.dawid124.simpleratingservice.security.model.Role;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserModel implements Serializable {

    private long id;
    private String username;
    private String email;
    private String password;
    private Role role;

    private Timestamp lastPasswordResetDate;
    private boolean enabled;

    public UserModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public boolean valid() {
        return StringUtils.hasText(username)
                && StringUtils.hasText(password)
                && EmailValidator.getInstance().isValid(email);
    }
}
