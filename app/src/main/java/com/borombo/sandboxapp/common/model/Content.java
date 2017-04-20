package com.borombo.sandboxapp.common.model;

/**
 * Created by Phantom on 20/04/2017.
 */

public class Content {

    private int id;
    private String name;
    private String description;
    private int icon;
    private Class<?> contentMainClass;

    public Content(int id, String name, String description, int icon, Class<?> contentMainClass) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.contentMainClass = contentMainClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class<?> getContentMainClass() {
        return contentMainClass;
    }

    public void setContentMainClass(Class<?> contentMainClass) {
        this.contentMainClass = contentMainClass;
    }
}
