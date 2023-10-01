package com.startup.claimizer.common;

import java.util.Arrays;
import java.util.Base64;

public abstract class CommonService {

    public static String encrypt(String clearText) {
        byte[] encode = Base64.getEncoder().encode(clearText.getBytes());
        return Arrays.toString(encode);
    }

    public static String decrypt(String encryptedText) {
        byte[] decode = Base64.getDecoder().decode(encryptedText.getBytes());
        return Arrays.toString(decode);
    }
}
