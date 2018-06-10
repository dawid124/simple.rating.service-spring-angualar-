package pl.webd.dawid124.simpleratingservice.type.model;

import org.springframework.util.StringUtils;

public class Type {

    private long id;
    private String name;
    private String descriptions;

    public Type() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean valid() {
        return StringUtils.hasText(name);
    }
}
