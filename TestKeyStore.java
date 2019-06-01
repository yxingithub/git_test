 
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
/**
 * @author Administrator
 * 使用以下命令生成keystore文件
 * alias:别名csdn
 * keypass:别名密码 123456
 * storepass:秘钥库的密码:888999
 * keytool -genkey -alias csdn -keypass 123456 -keyalg RSA -keysize 1024 -validity 365 -keystore D:/csdn_server.keystore -storepass 888999
 */
public class TestKeyStore {
	public static void main(String[] args) throws Exception {
		//读取keystore文件转换为keystore密钥库对象
		FileInputStream fis = new FileInputStream("ceshi_server.keystore");
		//因为生成证书的类型为JKS 也有其他的格式
		KeyStore keyStore = KeyStore.getInstance("JKS");
		//该密钥库的密码"888999"
		String storepass="888999";
		keyStore.load(fis, storepass.toCharArray());
		fis.close();
		// 从keystore中读取证书和私钥
		String alias = "ceshi";//别名
		String keypass = "123456"; //别名密码
		Certificate certificate = keyStore.getCertificate(alias);
		//读取公钥对象
		PublicKey publicKey = certificate.getPublicKey();
		System.out.println("提取的公钥为___:\n"+encodeBase64(publicKey.getEncoded()));
		
		//读取私钥对象
		PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias,keypass.toCharArray());
		System.out.println("提取的私钥为___:\n"+encodeBase64(privateKey.getEncoded()));
		System.out.println("===="+decodeBase64(encodeBase64(privateKey.getEncoded())));
	}
	// 对字符密码加密  
    public static String encodeBase64(byte[] byt) throws Exception {  
        // 1.将传递进来的字符串密码 转换为字节数组 放到base64加密工具里 生产出一个加了密的字符串  
       String base64Str = new BASE64Encoder().encode(byt);  
        
        return base64Str;  
    }  
  
    // 对密文字符串解密  
    public static String decodeBase64(String privateKeyStr) throws Exception {  
        // 根据加了密的字符串 使用base64的解密工具里 获取原来的明文字符串密码  
    	 BASE64Decoder base64Decoder = new BASE64Decoder();
         byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
         PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
         KeyFactory keyFactory = KeyFactory.getInstance("RSA");
         PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
         return privateKey.toString();
    }  	
}
