package cn.itcast.gateway.encryption;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@Component
public class AesEncryptionExample {

    //指定AES算法
    private static final String AES_ALGORITHM = "AES";

    //指定ECB模式和PKCS5Padding填充方案
    private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";


    //接受加密的数据和密钥，并返回加密后的数据
    public static String encrypt(String data, String key) throws Exception {
        //获取AES密钥包装器实例
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), AES_ALGORITHM);

        //获取加密实例
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);

        //初始化加密实例
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        //使用diFinal对数组进行数据加密，并返回字节数组
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());

        //返回base64位编码后的字符串
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }


    //接受加密后的字符串和密钥，对字符串进行解密
    public static String decrypt(String encryptedData, String key) throws Exception {

        //获取AES解密包装器
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), AES_ALGORITHM);

        //获取解密实例
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);

        //初始化解密实例
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        //还原base64编码的字符串为字节数组
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);

        //对字节数组进行解码
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        //返回字符串对应的数据
        return new String(decryptedBytes);
    }

//    public static void main(String[] args) throws Exception {
//        String data = "Hello, World!";
//        String key = "0123456789ABCDEF";
//
//        String encryptedData = encrypt(data, key);
//        System.out.println("Encrypted data: " + encryptedData);
//
//        String decryptedData = decrypt(encryptedData, key);
//        System.out.println("Decrypted data: " + decryptedData);
//    }
}
