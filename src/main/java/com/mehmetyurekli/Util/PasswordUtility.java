package com.mehmetyurekli.Util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordUtility { //it is used to hash and verify user's passwords

    private static final Argon2 argon = Argon2Factory.create();

    public static String hashPassword(char[] password) {
        return argon.hash(10, 65536, 1, password);
    }

    public static boolean verifyPassword(char[] password, String hashed) {
        return argon.verify(hashed, password);
    }
}
