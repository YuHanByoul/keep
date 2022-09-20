package com.kbrainc.plum.rte.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * AES256 암/복호화 유틸 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.crypto
 * - CryptoAES256.java
 * </pre> 
 *
 * @ClassName : CryptoAES256
 * @Description : AES256 암/복호화 유틸 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
public class CryptoAES256 {
    private String iv;
    private Key keySpec;
    
    
    /**
    * Desc : Constructor of CryptoAES256.java class
    * @param key 암/복호화 대칭키
    * @throws UnsupportedEncodingException
    */
    public CryptoAES256(String key) throws UnsupportedEncodingException {
        this.iv = key.substring(0, 16);
        
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int length = b.length;
        
        if (length > keyBytes.length) {
           length = keyBytes.length; 
        }
        
        System.arraycopy(b, 0, keyBytes, 0, length);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        
        this.keySpec = keySpec;
    }
    
    /**
    * 암호화를 수행한다.
    *
    * @Title       : encode 
    * @Description : 암호화를 수행한다
    * @param str 암호화할 평문문자열
    * @return String 암호화된 문자열
    * @throws UnsupportedEncodingException 예외
    * @throws NoSuchAlgorithmException 예외
    * @throws NoSuchPaddingException 예외
    * @throws InvalidKeyException 예외
    * @throws InvalidAlgorithmParameterException 예외
    * @throws IllegalBlockSizeException 예외
    * @throws BadPaddingException 예외
    */
    public String encode(String str) throws UnsupportedEncodingException, 
            NoSuchAlgorithmException, 
            NoSuchPaddingException, 
            InvalidKeyException, 
            InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, 
            BadPaddingException {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
        
        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
        
        return enStr;
    }
    
    /**
    * 복호화를 수행한다.
    *
    * @Title       : decode 
    * @Description : 복호화를 수행한다.
    * @param str 암호화된 문자열
    * @return String 복호화된 평문문자열
    * @throws UnsupportedEncodingException 예외
    * @throws NoSuchAlgorithmException 예외
    * @throws NoSuchPaddingException 예외
    * @throws InvalidKeyException 예외
    * @throws InvalidAlgorithmParameterException 예외
    * @throws IllegalBlockSizeException 예외
    * @throws BadPaddingException 예외
    */
    public String decode(String str) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, 
            NoSuchPaddingException, 
            InvalidKeyException, 
            InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, 
            BadPaddingException {
        
        try {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
            
            byte[] byteStr = Base64.decodeBase64(str.getBytes());
            return new String(c.doFinal(byteStr), "UTF-8");
        } catch (NullPointerException e) {
            return null;
        }
    }
    
    /**
    * 클라이언트 -> 서버(암호화) -> DB, DB -> 서버(복호화) -> 클라이언트 호출간 암호화된 Vo클래스 필드의 setter에서 호출해야하는 메서드.
    *
    * @Title       : autoConvert 
    * @Description : 클라이언트 -> 서버(암호화) -> DB, DB -> 서버(복호화) -> 클라이언트 호출간 암호화된 Vo클래스 필드의 setter에서 호출해야하는 메서드.
    * @param str
    * @return
    * @throws Exception
    * @return String
    */
    public String autoConvert(String str) throws Exception {
        try {
            String decStr = decode(str);
            
            if ("".equals(decStr)) {
                return encode(str);
            }
            return decStr;
        } catch(IllegalBlockSizeException e) {
            return encode(str);
        } catch(NullPointerException e) {
            return null;
        }
    }
    /*
    // 암복호화 사용예시
    public static void main(String[] args) {
        try {
            String encStr = new CryptoAES256("aes256-test-keyfdsfsdafsdafds가!").encode("나야나!");
            System.out.println(encStr);
            System.out.println(new CryptoAES256("aes256-test-keyfdsfsdafsdafds가!").decode(encStr));
            System.out.println("[" + new CryptoAES256("ILoveSkyJOB2019!#%").autoConvert(null) + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
