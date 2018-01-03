package algorithms;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA{

        private static Cipher cipher ;
        private static PrivateKey privateKey;
        private static PublicKey publicKey;

        public static void init(String publicKeyPath, String privateKeyPath) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
            cipher = Cipher.getInstance("RSA");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            byte[] publicKeyBytes = Files.readAllBytes(new File(publicKeyPath).toPath());
            X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicKeyBytes);
            publicKey = kf.generatePublic(publicSpec);
            byte[] keyBytes = Files.readAllBytes(new File(privateKeyPath).toPath());
            PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(keyBytes);
            privateKey = kf.generatePrivate(privateSpec);

        }

        public static String encrypt(byte[] input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypted = cipher.doFinal(input);
            return Base64.getEncoder().encodeToString(encrypted);
        }

        public static byte[] decrypt(String base64Input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(base64Input));
            return decrypted;
        }

    }