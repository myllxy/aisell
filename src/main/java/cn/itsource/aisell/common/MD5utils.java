package cn.itsource.aisell.common;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author myllxy
 * @create 2019-12-15 11:12
 */
public class MD5utils {
    public static final String SALT = "itsource";
    public static final String ALGORITHMNAME = "MD5";
    public static final Integer HASHITERATIONS = 10;

    public static String afterEncrypt(String username) {
        SimpleHash simpleHash = new SimpleHash(ALGORITHMNAME, username, SALT, HASHITERATIONS);
        return simpleHash.toString();
    }
}
