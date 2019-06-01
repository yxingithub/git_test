import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;


 

 
public class RSAUtils {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtils.class);
    public static final String KEY_ALGORITHM = "RSA";
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";
    public static final int KEY_SIZE = 1024;
    public static final String PLAIN_TEXT = "MANUTD is the greatest club in the world";
    public static final String DEFAULT_PRIVATE_KEY =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIiP4E8HdMu0SqXreXoTEzAvsRm6fLHnIO4v8WIvf45XjsHOtCjMNU9jMtVmKvaqoTzJWD1JJCCjm5UdBtsAOFPg3PSYVN7H3wx7u+OMsHFr6xZsjJRbPpYYClZshKSo4jomIqMZWq6DjykEboh47D9iIUTeI5oixuN7qwLioJ1NAgMBAAECgYB0ZDQpB1DbgamR6rWZfwJ+slc+d3xp4HVnrQMNzS/pcOTieDuC/jF9nS2pRi/eFqXf+jez3E/nZapoF9pM884NHG1FQLrRRUpqgOdeqZpSAga/jUMb7DmvsqKcBZe6c1D4q1ku6YR2JYgVjhDj/ZK54xD5Ch5JO40zNv4hqdj4QQJBAPa+bnossKvnKjPkbbUfE+SyA4ys7WYlrw8TCiDkRnrHIdywD4tQcEmQk6UjnFeRLjfkNfzGLiSxLZZBcg96rnECQQCNr1RUy/aBlkFGE+U+j1+bfHBD0vqdylfm/78OXWdPOxSjI9+nj8+SSZYG5VBoN4JOHawfYeOHz35R+K9MUsKdAkEAiiewRho1xdrGR430W4COK/P8hszgk9wvu7oGZspKKD7NV/sAiRm99YLNGJ7q0CELBuJx1BnHBmZqRwJX8hDYEQJABz1/bLnjhem8ui0IGPNQqmxofD7KycduSIKnK7/AKL9XJaMOqcxiihIylXWgb0Lu6LkBc7UX4HfM97lik97izQJAPefOOr6nZ701Re3RbbvGsGJ67nHM9C/N2Y9ayFMcfJoeEb8P2WRYvmxIykJwaE3Iv1UBGW8rs4FLessOskKofw==";
    public static final String DEFAULT_PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCIj+BPB3TLtEql63l6ExMwL7EZunyx5yDuL/FiL3+OV47BzrQozDVPYzLVZir2qqE8yVg9SSQgo5uVHQbbADhT4Nz0mFTex98Me7vjjLBxa+sWbIyUWz6WGApWbISkqOI6JiKjGVqug48pBG6IeOw/YiFE3iOaIsbje6sC4qCdTQIDAQAB";
 
    /**
     * 获取公钥
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
            return restorePublicKey(publicKey.getEncoded());
 
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (IOException e) {
            throw new Exception("公钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }
 
    /**
          * 还原公钥，X509EncodedKeySpec 用于构建公钥的规范
          *
          * @param keyBytes
          * @return
          */
    public static PublicKey restorePublicKey(byte[] keyBytes) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception("");
        }
    }
 
    /**
     * 通过私钥KEY获取解密私钥
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return restorePrivateKey(privateKey.getEncoded());
 
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (IOException e) {
            throw new Exception("公钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }
 
    /**
     * 获取私钥
     * @param keyBytes
     * @return
     */
    public static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
            return privateKey;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error("获取私钥失败", e);
        }
        return null;
    }
 
    /**
          * 加密，三步走。
          *
          * @param key
          * @param plainText
          * @return
          */
    public static byte[] RSAEncode(PublicKey key, byte[] plainText) {
 
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(plainText);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error("加密失败", e);
        }
        return null;
 
    }
 
    /**
    * 解密，三步走。
     *
     * @param key
     * @param encodedText
     * @return
     */
    public static byte[] RSADecode(PrivateKey key, byte[] encodedText) {
 
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(encodedText);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error("解密失败", e);
        }
        return null;
 
    }
 
    public static void main(String[] ar) throws Exception {
        String str = "{\"a\":\"欢迎来到chacuo\"}";
        System.out.println("待加密报文：" + str);
        PublicKey publicKey = loadPublicKey(DEFAULT_PUBLIC_KEY);
        PrivateKey privateKey = loadPrivateKey(DEFAULT_PRIVATE_KEY);
        
       // System.out.println("公匙:"+publicKey.toString());
       // System.out.println("私匙:"+privateKey.toString());
//        String encodeStr = Base64.encodeBase64String(RSAEncode(publicKey, str.getBytes()));
//        System.out.println("加密后密文:" + encodeStr);
//        String decodeStr = new String(RSADecode(privateKey, Base64.decodeBase64(encodeStr)));
//        System.out.println("解密后密文:" + decodeStr);
 
 
        String randomKey = String.valueOf(System.currentTimeMillis());
        System.out.println("随机KEY randomKey:"+randomKey);
 
        String cipherText = AESUtils.encrypt(str,randomKey);
        System.out.println("AES加密请求内容cipherText:"+cipherText);
       
        byte[] cipherKey = Base64.encodeBase64(RSAEncode(publicKey, randomKey.getBytes()));
        System.out.println("RSA加密后的key:"+new String(cipherKey));
 
        System.out.println("请求内容:{\"encryptStr\":\"" + cipherText+"\",\"aesKey\":\""+cipherKey+"\"}");
 
        String key = new String(RSADecode(privateKey, Base64.decodeBase64(cipherKey)));
        System.out.println("RSA私钥解密后的key:"+key);
 
        String decodeData = AESUtils.decrypt(cipherText,key);
        System.out.println("AES解密后:"+decodeData);
    }
}

