package se.joeldegerman.javaeewebshop.models.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.joeldegerman.javaeewebshop.models.entity.Address;
import se.joeldegerman.javaeewebshop.models.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {

    private final String username;
    private final String password;
    private final boolean isActive;
    private final List<GrantedAuthority> authorities;
    private final String fullName;
    private final Address address;

    public CustomUserDetail(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isActive = user.isActive();
        this.authorities = new ArrayList<>(user.getUserRole().getGrantedAuthorities());
        this.fullName = user.getFullName();
        this.address = user.getAddress();
    }

    public String getFullName() {
        return fullName;
    }

    public Address getAddress() {
         return address;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isActive;
    }
}
