package se.joeldegerman.javaeewebshop.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static se.joeldegerman.javaeewebshop.security.EUserAuthorities.*;

public enum EUserRole {
    CUSTOMER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(CATEGORY_READ, CATEGORY_WRITE, PRODUCT_READ, PRODUCT_WRITE, ORDER_READ, ORDER_WRITE));

    private final Set<EUserAuthorities> authorities;

    EUserRole(Set<EUserAuthorities> authorities) {
        this.authorities = authorities;
    }

    public Set<EUserAuthorities> getAuthorities() {
        return authorities;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authoritySet = getAuthorities().stream()
                .map(authorities -> new SimpleGrantedAuthority(authorities.getAuthorities())).collect(Collectors.toSet());
        authoritySet.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authoritySet;
    }
}
