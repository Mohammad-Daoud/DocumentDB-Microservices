package com.mohammad.masternode.users;


import com.mohammad.masternode.security.SHA512;
import com.mohammad.masternode.utils.IdGenerator;

import javax.validation.constraints.NotEmpty;


public class User {
    private final static SHA512 HASHING = new SHA512();
    private int userID;
    @NotEmpty(message = " * please enter a username")
    private String username;

    @NotEmpty(message = "* Please enter user password")
    private String password;
    @NotEmpty(message = "* please enter the user role 'ROLE_ADMIN' or 'ROLE_USER'")
    private Role role;

    public User(){

    }
    private User(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.role = builder.role;
        this.userID = builder.USER_ID;
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private Role role;

        private final int USER_ID = IdGenerator.getInstance().generateKey();



        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = HASHING.encode(password);;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return HASHING.encode(password);
    }

    public Role getRole() {
        return role;
    }

    public int getUserID() {
        return userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = HASHING.encode(password);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" +
                "userID:" + userID +
                ", username:'" + username + '\'' +
                ", password:'" + password + '\'' +
                ", role:" + role +
                '}';
    }
}