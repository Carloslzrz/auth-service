package com.unam.dwb.auth.constants;

import static com.unam.dwb.auth.constants.Autoridades.ADMIN_AUTHORITIES;
import static com.unam.dwb.auth.constants.Autoridades.USER_AUTHORITIES;;

public enum Rol {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);

    private String[] authorities;

    Rol(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
