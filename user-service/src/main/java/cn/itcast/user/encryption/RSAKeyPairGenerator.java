package cn.itcast.user.encryption;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@Component
public class RSAKeyPairGenerator {
    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // 设置密钥长度

//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
//        keyPairGenerator.initialize(2048);

//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
//        keyPairGenerator.initialize(256);

//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DiffieHellman");
//        keyPairGenerator.initialize(256);
        return keyPairGenerator.generateKeyPair();
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateRSAKeyPair();
            System.out.println("RSA Public Key: " + keyPair.getPublic());
            System.out.println("RSA Private Key: " + keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
