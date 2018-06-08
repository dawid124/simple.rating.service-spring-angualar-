package pl.webd.dawid124.simpleratingservice.security.model;

import java.io.Serializable;

/**
 * @author Dawid.Gaik on 08.06.2018.
 */
public class UserData implements Serializable {

    private String username;
    private String email;
    private Role role;

    public UserData() {
    }

    public UserData(String username, String email, Role role) {
        this.username = username;
        this.email = email;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
