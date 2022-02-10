package com.mohammad.masternode.config;

import com.mohammad.masternode.security.SHA512;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint entryPoint;
    @Autowired
    private RestUserDetailsService userDetailsService;


    @Bean
    public AuthenticationProvider authProvider(){
        RestDaoAuthentication provider=new RestDaoAuthentication();
        provider.setPasswordEncoder(sha512PasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(authorize ->{
                    authorize.antMatchers("/master/**").hasAuthority("ROLE_ADMIN");
                })
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic().authenticationEntryPoint(entryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterAfter(new RestFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    public SHA512 sha512PasswordEncoder() {
        return new SHA512();
    }

}
