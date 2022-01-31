package ServerEncryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class Digester {
    
    public static String getKey(String key1, String key2) {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < key1.length(); i++)
    	    sb.append((char)(key1.charAt(i) ^ key2.charAt(i % key2.length())));
    	
    	
    	
    	String output = ""; 
    	for (int i = 0; i < key1.length()-1; i++) {
    		char xorKey = key1.charAt(i);
    		output += Character.toString((char) (key2.charAt(i) ^ xorKey));
    	}
    	
    	return hexDigest(output, "SHA-256");
    	//return hexDigest(sb.toString(), "SHA-256");
    	
 
    }
    
 
    static String hexDigest(String str, String digestName) {
        try {
            MessageDigest md = MessageDigest.getInstance(digestName);
            
            byte[] digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
            char[] hex = new char[digest.length * 2];
            for (int i = 0; i < digest.length; i++) {
                hex[2 * i] = "0123456789abcdef".charAt((digest[i] & 0xf0) >> 4);
                hex[2 * i + 1] = "0123456789abcdef".charAt(digest[i] & 0x0f);
            }
            return new String(hex);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
