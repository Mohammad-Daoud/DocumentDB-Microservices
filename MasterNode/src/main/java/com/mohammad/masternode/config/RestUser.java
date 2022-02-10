package com.mohammad.masternode.config;

import com.mohammad.masternode.api.services.UserService;
import com.mohammad.masternode.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RestUser implements UserDetails {


    private final UserService SERVICE = new UserService();

    private User user;

    private RestUser(User user) {
        this.user = user;
    }

    public static RestUser create(User user){
        return new RestUser(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<User> userGroup = SERVICE.getAllUser();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (User tempUser : userGroup ) {
            authorities.add(new SimpleGrantedAuthority(tempUser.getRole().toString()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return true;
    }
}
