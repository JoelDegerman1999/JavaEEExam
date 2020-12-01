package se.joeldegerman.javaeewebshop.security;

public enum EUserAuthorities {
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write"),
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),
    ORDER_READ("order:read"),
    ORDER_WRITE("order:write");

    private final String authorities;

    EUserAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getAuthorities() {
        return authorities;
    }
}
