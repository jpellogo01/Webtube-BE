package com.Webtube.site.Security;

public enum ApplicationUserPermission {
    CONTENT_READ("content:read"),
    CONTENT_WRITE("content:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String  getPermission(){
        return permission;
    }
}
