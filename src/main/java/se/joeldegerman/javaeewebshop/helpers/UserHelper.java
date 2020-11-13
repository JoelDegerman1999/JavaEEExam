package se.joeldegerman.javaeewebshop.helpers;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import se.joeldegerman.javaeewebshop.models.security.UserDetail;

public class UserHelper {

    public static String getUsernameFromLoggedInUser(SecurityContext securityContext){

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {

        var details = (UserDetail)securityContext.getAuthentication().getPrincipal();
        return details.getFullName();
        }
        return null;
    }

}
