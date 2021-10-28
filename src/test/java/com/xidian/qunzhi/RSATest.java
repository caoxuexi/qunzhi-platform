package com.xidian.qunzhi;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@RunWith(SpringRunner.class)
//因为引入了websocket，所以需要指定webEnvironment，不然测试通不过打包会失败
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RSATest {
    @Test
    public void generateKeyBytes() throws Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        //生成公钥和私钥对
        KeyPair keyPair =keyPairGenerator.generateKeyPair();
        //获取公钥和私钥对象
        RSAPublicKey publicKey =(RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey=(RSAPrivateKey) keyPair.getPrivate();

        log.info("private key: [{}]", Base64.encodeBase64String(privateKey.getEncoded()));
        log.info("public key:[{}]", Base64.encodeBase64String(publicKey.getEncoded()));
    }
}
