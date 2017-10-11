package com.sample.rest.demo.springbootrest.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public CustomUserDetails(User user){
        if(null!=user){
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.authorities = user.getAuthorities();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @SuppressWarnings("unused")
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() { return this.password; }

    @SuppressWarnings("unused")
    public void setPassword(String password) { this.password = password; }

    @Override
    public String getUsername() { return this.username; }

    @SuppressWarnings("unused")
    public void setUsername(String username) { this.username = username; }

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
