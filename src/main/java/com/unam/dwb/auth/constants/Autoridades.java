package com.unam.dwb.auth.constants;

public class Autoridades {
    public static final String[] USER_AUTHORITIES = { "usuario:read" };
    public static final String[] ADMIN_AUTHORITIES = { "usuario:read", "usuario:create", "usuario:update", "usuario:delete" };
}
