package com.mohammad.demo.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties("_objectID")
public class User {


    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("userTodo")
    private String userTodo;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserTodo() {
        return userTodo;
    }

    public void setUserTodo(String userTodo) {
        this.userTodo = userTodo;
    }

    @Override
    public String toString() {
        return "{" +
                " username:'" + username + '\'' +
                ", password:'" + password + '\'' +
                ", userTodo:'" + userTodo + '\'' +
                '}';
    }
}
