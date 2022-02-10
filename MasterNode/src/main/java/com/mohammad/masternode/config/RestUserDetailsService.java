package com.mohammad.masternode.config;

import com.mohammad.masternode.api.services.UserService;
import com.mohammad.masternode.users.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class RestUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserService service=new UserService();
        User tempUser= service.getUser(username);
        return  RestUser.create(tempUser);
    }
}
