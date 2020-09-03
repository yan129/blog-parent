package com.xiong.user.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@Component
public class RsaKeyProperties {

    @Value("${rsa.key.privateKey}")
    private String privateKeyPath;

    @Value("${rsa.key.publicKey}")
    private String publicKeyPath;

    // 读取文件里的秘钥
    private PublicKey publicKey;
    private PrivateKey privateKey;

    // 在对象构造完成之后执行
    @PostConstruct
    public void createRsaKey() throws Exception {
        privateKeyPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() +  privateKeyPath;
        publicKeyPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() +  publicKeyPath;
        privateKey = RsaUtil.getPrivateKey(privateKeyPath);
        publicKey = RsaUtil.getPublicKey(publicKeyPath);
    }

}
