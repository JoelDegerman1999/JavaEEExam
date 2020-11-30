package se.joeldegerman.javaeewebshop.helpers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import se.joeldegerman.javaeewebshop.models.security.CustomUserDetail;

import java.util.Collection;

public class UserHelper {

    public static String getUsernameFromLoggedInUser(SecurityContext securityContext) {

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {

            var details = (CustomUserDetail) securityContext.getAuthentication().getPrincipal();
            return details.getFullName();
        }
        return null;
    }

    public static boolean checkIfUserIsAdmin(SecurityContext context) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            Collection<? extends GrantedAuthority> authorities = context.getAuthentication().getAuthorities();
            for (var role : authorities) {
                if (role.getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
                    return true;
                }
            }
        }
        return false;
    }

}
