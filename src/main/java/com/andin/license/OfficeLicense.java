package com.andin.license;

import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.andin.utils.ConstantUtil;

public class OfficeLicense {
	
    private static Logger logger = LoggerFactory.getLogger(OfficeLicense.class);
	
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";  
    
    /**
          * 将bytes数组进行base64加密
     * @param bytes
     * @return
     */
    public static String base64Encode(byte[] bytes){
        Base64 base64 = new Base64();
        return base64.encodeToString(bytes);
    }
    
    /**
          * 将加密字符串进行base64解密
     * @param base64Code
     * @return
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        Base64 base64 = new Base64();
        return base64.decode(base64Code);
    }  
    
    /**
     * AES加密
     * @param content 待加密字符串
     * @param encryptKey 加密秘钥
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {  
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }
    
    /**
     * AES解密
     * @param encryptContent 待解密字符串
     * @param decryptKey 解密秘钥
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String encryptContent, String decryptKey) throws Exception {  
        return aesDecryptByBytes(base64Decode(encryptContent), decryptKey);
    }
    
    /**
     * AES将字符串加密成byte数组
     * @param content
     * @param encryptKey
     * @return
     * @throws Exception
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128);  
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));  
        return cipher.doFinal(content.getBytes("utf-8"));  
    }
    
    /**
     * AES将byte数组解密成字符串
     * @param encryptBytes
     * @param decryptKey
     * @return
     * @throws Exception
     */
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128);  
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);  
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
        return new String(decryptBytes);  
    }
    
    /**
          * 通过key生成lincense
     * @param key
     * @return
     * @throws Exception
     */
    private static String getLicenseByKey(String key) throws Exception{
    	String license = null;
    	JSONObject json = new JSONObject();
    	json.put(ConstantUtil.COM_KEY, ConstantUtil.COM_VALUE);
    	json.put(ConstantUtil.CREATE_TIME, new Date().getTime());
    	license = aesEncrypt(json.toJSONString(), key);
    	return license;
    }
    
    /**
          * 通过key检测license是否成功
     * @param license
     * @param key
     * @return
     */
    public static boolean checkLicense(String license, String key) {
    	boolean result = false;
    	try {
			String data = aesDecrypt(license, key);
	        JSONObject info = JSONObject.parseObject(data);
	        String company = info.getString(ConstantUtil.COM_KEY);
	        if(ConstantUtil.COM_VALUE.equals(company)) {
	        	result = true;
	        }
    	} catch (Exception e) {
    		logger.error("***OfficeLicense.checkLicense method executed is error: ", e);
		}
    	return result;
    }
	
    
    public static void main(String[] args) throws Exception {
    	String key = "43agtjqtl2wluub6sofm7x39ubyheulm";
        String encrypt = getLicenseByKey(key);
        System.out.println("en key is: " + encrypt);
        boolean result = checkLicense(encrypt, key);
        System.out.println(result);
    }
}