package pl.webd.dawid124.simpleratingservice.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.webd.dawid124.simpleratingservice.users.model.UserModel;
import java.sql.Timestamp;
import java.util.*;

public class SecurityUser implements UserDetails {

    private long userId;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Timestamp lastPasswordResetDate;
    private boolean enabled;


    public SecurityUser() {
    }

    public SecurityUser(UserModel userModel) {
        this.userId = userModel.getUserId();
        this.username = userModel.getUsername();
        this.email = userModel.getEmail();
        this.password = userModel.getPassword();
        this.enabled = userModel.isEnabled();
        this.lastPasswordResetDate = userModel.getLastPasswordResetDate();
        setAuthoritie(userModel.getRole());
    }

    public boolean isAdmin() {

        for (GrantedAuthority grant : authorities) {
            String role = grant.getAuthority();
            if (Role.ROLE_ADMIN.equals(Role.valueOf(role))) {
                return true;
            }
        }

        return false;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAuthoritie(Role role) {
        this.authorities = Collections.singleton(new Authority(role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public Role getAuthoritie() {
        if (this.authorities.isEmpty()) {
            return null;
        }
        return Role.valueOf(this.authorities.iterator().next().getAuthority());
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}
