package com.nan.weaccessglobal.model.interfaces;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    AMBASSADOR_READ("management:read"),
    AMBASSADOR_UPDATE("management:update"),
    AMBASSADOR_CREATE("management:create"),
    AMBASSADOR_DELETE("management:delete")
    ;

    @Getter
    private final String permission;
}
