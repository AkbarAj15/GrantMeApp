package kodeJava;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static final String KEY = "Akbar Abdurrahman Jafaruddin Indriani Antika Diana Liku Nur" +
            "nur faizih aspar muharni Fadiyah Nur aulia sari Rezki Nurul " +
            "Jariah S Intam ahmad dani setiawan"; // key AES, ubah dengan key Anda sendiri
    private static final String INIT_VECTOR = "ABCDEFGHIJKLMNOP"; // inisialisasi vektor, ubah dengan vektor Anda sendiri

    // Mengubah kunci AES menjadi 256 bit
    private static final String AES_KEY_256_BIT = KEY.substring(0, 32);

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());
            SecretKeySpec keySpec = new SecretKeySpec(AES_KEY_256_BIT.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());
            SecretKeySpec keySpec = new SecretKeySpec(AES_KEY_256_BIT.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

            byte[] original = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}