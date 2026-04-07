package com.pramps.model;

public class User {
    private final String name;
    private final String login;
    private final String password;
    private final String phone;

    public User(String name, String login, String password, String phone) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
