package com.xiong.user.util;

import org.junit.Test;

import java.io.File;

public class RsaUtilTest {

    private String privateKeyFilePath = System.getProperty("user.dir") + File.separator + "src\\main\\resources\\private_key_rsa";
    private String publicKeyFilePath = System.getProperty("user.dir") + File.separator + "src\\main\\resources\\public_key_rsa";

    @Test
    public void generateKey(){
        try {
            RsaUtil.generateKey(publicKeyFilePath, privateKeyFilePath, RsaUtil.DEFAULT_SECRET, RsaUtil.DEFAULT_KEY_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPublicKey() throws Exception {
        System.out.println(RsaUtil.getPublicKey(publicKeyFilePath));
    }

    @Test
    public void getPrivateKey() throws Exception {
        System.out.println(RsaUtil.getPrivateKey(privateKeyFilePath));
    }

}
