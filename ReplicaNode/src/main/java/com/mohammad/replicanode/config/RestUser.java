package com.mohammad.replicanode.config;

import com.mohammad.replicanode.api.services.UserService;
import com.mohammad.replicanode.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RestUser implements UserDetails {


    private UserService service = new UserService();

    private User user;

    private RestUser(User user) {
        this.user = user;
    }

    public static RestUser create(User user){
        return new RestUser(user);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<User> userGroup = service.getAllUsers();
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
