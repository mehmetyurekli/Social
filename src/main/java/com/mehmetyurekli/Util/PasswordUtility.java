package com.mehmetyurekli.Util;

import com.mehmetyurekli.Models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordUtility {

    private static final Argon2 argon = Argon2Factory.create();

    public static String hashPassword(char[] password) {
        return argon.hash(10, 65536, 1, password);
    }

    public static boolean verifyPassword(char[] password, String hashed) {
        return argon.verify(hashed, password);
    }
}
