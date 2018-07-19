package ru.innopolis.stc9.saturn.db.security;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Component
public class MD5hashImpl implements MD5hash {
    private static final  String SALT = "v#X&6$jSl!)(q?>Bv";
    final Logger loggerFileInf = Logger.getLogger("fileinf");

    public String getSalt() {
        return SALT;
    }

    @Override
    public String getHash(String str) {
        MessageDigest m = null;

        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();

        } catch (NoSuchAlgorithmException e) {
            loggerFileInf.error(e.getMessage());
        }

        try {
            assert m != null;
            m.update(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            loggerFileInf.error(e.getMessage());
        }

        String s2 = new BigInteger(1, m.digest()).toString(16);
        StringBuilder sb = new StringBuilder(32);

        for (int i = 0, count = 32 - s2.length(); i < count; i++) {
            sb.append("0");
        }

        return sb.append(s2).toString();
    }

}
