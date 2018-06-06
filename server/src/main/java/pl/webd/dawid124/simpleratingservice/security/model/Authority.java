package pl.webd.dawid124.simpleratingservice.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private Role name;


    public Authority(Role name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.toString();
    }

    @JsonIgnore
    public Role getName() {
        return name;
    }

    public void setName(Role name) {
        this.name = name;
    }
}
