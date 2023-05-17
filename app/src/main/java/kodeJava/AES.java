package kodeJava;
// class untuk mengamankan informasi username dan password pengguna
import android.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    String username, password;
    private static final String ALGORITHM = "AES";
    private static final String KEY = "j4l4nkot3Pak3Lombok"; //Ganti dengan kunci enkripsi yang Anda buat

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public AES(String username, String password ) {
        this.username = username;
        this.password = password;
    }

    public static String encrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue64 = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);
        return encryptedValue64;
    }

    public static String decrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedValue64 = Base64.decode(value, Base64.DEFAULT);
        byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue, "utf-8");
        return decryptedValue;
    }

    private static Key generateKey() throws Exception {
        byte[] keyValue = AES.KEY.getBytes("utf-8");
        Key key = new SecretKeySpec(keyValue, AES.ALGORITHM);
        return key;
    }
}


