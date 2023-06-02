package cn.itcast.user.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Component
public class RSAEncryption {
    RSAEncryption(){
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encrypt(String data, PublicKey publicKey) throws Exception {

        Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        //传入加密公钥并初始化加密操作
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        //数据加密，并返回加密后的字节数组
        byte[] encryptedBytes = encryptCipher.doFinal(data.getBytes());

        String encryptedTextBase64 = Base64.getEncoder().encodeToString(encryptedBytes);

//        System.out.println(encryptedTextBase64);

        //返回base64位编码后的字符串
        return encryptedTextBase64;
    }


    //接受加密后的字符串和密钥，对字符串进行解密
    public static String decrypt(String encryptedData, PrivateKey privateKey) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        //加入私钥
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        //数据解密
        byte[] decryptedBytes = decryptCipher.doFinal(decodedBytes);

        String decryptedText = new String(decryptedBytes);
        System.out.println("Decrypted text: " + decryptedText);

        return decryptedText;
    }

    public static PublicKey getPublicKey(String modulus,String exponent){
        BigInteger modulusBigInt = new BigInteger(modulus);
        BigInteger publicExponentBigInt = new BigInteger(exponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulusBigInt, publicExponentBigInt);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
        }
        System.out.println("publicKey is null");
        return null;
    }

    public static PrivateKey getPrivateKey(String modulus,String exponent){
        // Convert modulus and publicExponent to BigInteger
        BigInteger modulusBigInt = new BigInteger(modulus);
        BigInteger publicExponentBigInt = new BigInteger(exponent);
        // Create RSAPublicKeySpec using modulus and publicExponent
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulusBigInt, publicExponentBigInt);

        try {
            // Generate RSA public key from key spec
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {
            // Handle exception
        }

        System.out.println("publicKey is null");
        return null;
    }
}
