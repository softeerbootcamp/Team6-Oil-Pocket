package com.kaspi.backend.util.encrypt;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

public class PasswordUtil {

    public static String makeEncryptPw(String plainPw) {
        return BCrypt.hashpw(plainPw, BCrypt.gensalt());
    }

    public static boolean matchPw(String encryptPw,String plainPw) {
        return BCrypt.checkpw(plainPw, encryptPw);
    }
}
