package com.wayneleo.quickstart.sample.rsa;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * RSA安全编码组件
 * 
 * @author http://www.voidcn.com/article/p-rgdmwcxu-rc.html
 * @version 1.0
 */
public class RSAUtil {
    /**
     * 非对称加密密钥算法
     */
    public static final String      KEY_ALGORITHM_RSA = "RSA";
    /**
     * 公钥
     */
    private static final String     RSA_PUBLIC_KEY    = "RSAPublicKey";
    /**
     * 私钥
     */
    private static final String     RSA_PRIVATE_KEY   = "RSAPrivateKey";
    /**
     * RSA密钥长度 默认1024位， 密钥长度必须是64的倍数， 范围在512至65536位之间。
     */
    private static final int        KEY_SIZE          = 1024;
    private static Map<String, Key> keyMap;
    static {
        Security.insertProviderAt( new BouncyCastleProvider(), 1 );
    }

    /**
     * 私钥解密
     * 
     * @param data 待解密数据
     * @param key 私钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey( byte[] data, byte[] key ) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec( key );
        KeyFactory keyFactory = KeyFactory.getInstance( KEY_ALGORITHM_RSA );
        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate( pkcs8KeySpec );
        // 对数据解密
        Cipher cipher = Cipher.getInstance( keyFactory.getAlgorithm() );
        cipher.init( Cipher.DECRYPT_MODE, privateKey );
        int blockSize = cipher.getBlockSize();
        if ( blockSize > 0 ) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream( 64 );
            int j = 0;
            while ( data.length - j * blockSize > 0 ) {
                bout.write( cipher.doFinal( data, j * blockSize, blockSize ) );
                j++;
            }
            return bout.toByteArray();
        }
        return cipher.doFinal( data );
    }

    /**
     * 公钥解密
     * 
     * @param data 待解密数据
     * @param key 公钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decryptByPublicKey( byte[] data, byte[] key ) throws Exception {
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec( key );
        KeyFactory keyFactory = KeyFactory.getInstance( KEY_ALGORITHM_RSA );
        // 生成公钥
        PublicKey publicKey = keyFactory.generatePublic( x509KeySpec );
        // 对数据解密
        Cipher cipher = Cipher.getInstance( keyFactory.getAlgorithm() );
        cipher.init( Cipher.DECRYPT_MODE, publicKey );
        return cipher.doFinal( data );
    }

    /**
     * 公钥加密
     * 
     * @param data 待加密数据
     * @param key 公钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey( byte[] data, byte[] key ) throws Exception {
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec( key );
        KeyFactory keyFactory = KeyFactory.getInstance( KEY_ALGORITHM_RSA );
        PublicKey publicKey = keyFactory.generatePublic( x509KeySpec );
        // 对数据加密
        Cipher cipher = Cipher.getInstance( keyFactory.getAlgorithm() );
        cipher.init( Cipher.ENCRYPT_MODE, publicKey );
        int blockSize = cipher.getBlockSize();
        if ( blockSize > 0 ) {
            int outputSize = cipher.getOutputSize( data.length );
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0, remainSize = 0;
            while ( ( remainSize = data.length - i * blockSize ) > 0 ) {
                int inputLen = remainSize > blockSize ? blockSize : remainSize;
                cipher.doFinal( data, i * blockSize, inputLen, raw, i * outputSize );
                i++;
            }
            return raw;
        }
        return cipher.doFinal( data );
    }

    /**
     * 私钥加密
     * 
     * @param data 待加密数据
     * @param key 私钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey( byte[] data, byte[] key ) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec( key );
        KeyFactory keyFactory = KeyFactory.getInstance( KEY_ALGORITHM_RSA );
        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate( pkcs8KeySpec );
        // 对数据加密
        Cipher cipher = Cipher.getInstance( keyFactory.getAlgorithm() );
        cipher.init( Cipher.ENCRYPT_MODE, privateKey );
        int blockSize = cipher.getBlockSize();
        if ( blockSize > 0 ) {
            int outputSize = cipher.getOutputSize( data.length );
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0, remainSize = 0;
            while ( ( remainSize = data.length - i * blockSize ) > 0 ) {
                int inputLen = remainSize > blockSize ? blockSize : remainSize;
                cipher.doFinal( data, i * blockSize, inputLen, raw, i * outputSize );
                i++;
            }
            return raw;
        }
        return cipher.doFinal( data );
    }

    /**
     * 取得私钥
     * 
     * @param keyMap 密钥Map
     * @return key 私钥
     * @throws Exception
     */
    public static PrivateKey getPrivateKey() {
        if ( null == keyMap ) {
            throw new NullPointerException( "还没有初始化" );
        }
        return ( PrivateKey ) keyMap.get( RSA_PRIVATE_KEY );
    }

    /**
     * 取得公钥
     * 
     * @param keyMap 密钥Map
     * @return key 公钥
     * @throws Exception
     */
    public static PublicKey getPublicKey() {
        if ( null == keyMap ) {
            throw new NullPointerException( "还没有初始化" );
        }
        return ( PublicKey ) keyMap.get( RSA_PUBLIC_KEY );
    }

    /**
     * 初始化密钥
     * 
     * @param seed 种子
     * @return Map 密钥Map
     * @throws Exception
     */
    public static Map<String, Key> initKey( String seed ) throws Exception {
        if ( null == keyMap ) {
            return initKey( seed.getBytes() );
        }
        return keyMap;
    }

    /**
     * 初始化密钥
     * 
     * @param byte[] seed 种子
     * @return Map 密钥Map
     * @throws Exception
     */
    private synchronized static Map<String, Key> initKey( byte[] seed ) throws Exception {
        if ( null != keyMap ) {
            return keyMap;
        }
        // 实例化密钥对生成器
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance( KEY_ALGORITHM_RSA );
        // 初始化密钥对生成器
        keyPairGen.initialize( KEY_SIZE, new SecureRandom( seed ) );
        // 生成密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 公钥
        RSAPublicKey publicKey = ( RSAPublicKey ) keyPair.getPublic();
        // 私钥
        RSAPrivateKey privateKey = ( RSAPrivateKey ) keyPair.getPrivate();
        // 封装密钥
        keyMap = new HashMap<String, Key>( 2 );
        keyMap.put( RSA_PUBLIC_KEY, publicKey );
        keyMap.put( RSA_PRIVATE_KEY, privateKey );
        return keyMap;
    }

    public static void main( String[] args ) throws Exception {
        byte[] dataString = "测试 - wayne".getBytes( "UTF-8" );
        String key = "key";
        initKey( key );
        PublicKey publicKey = getPublicKey();
        PrivateKey privateKey = getPrivateKey();
        byte[] encode = encryptByPublicKey( dataString, publicKey.getEncoded() );
        byte[] decode = decryptByPrivateKey( encode, privateKey.getEncoded() );
        System.out.println( "\n公钥: " + Base64.encodeBase64String( publicKey.getEncoded() ) );
        System.out.println( "\n私钥: " + Base64.encodeBase64String( privateKey.getEncoded() ) );
        System.out.println( "\n未加密的数据: " + new String( dataString, "UTF-8" ) );
        System.out.println( "\n加密后的数据: " + Base64.encodeBase64String( encode ) );
        System.out.println( "\n解密后的数据: " + new String( decode, "UTF-8" ) );
    }
}
