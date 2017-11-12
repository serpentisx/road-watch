
package app.service;

import app.exceptions.HashException;
import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 12 November 2017
 *
 * Provides salted password hashing and password verification utilities
 */
@Service
public class PasswordService {
  
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    // These constants may be changed without breaking existing hashes.
    private static final int SALT_BYTE_SIZE = 24;
    private static final int HASH_BYTE_SIZE = 18;
    private static final int PBKDF2_ITERATIONS = 64000;

    // These constants define the encoding and may not be changed.
    private static final int HASH_SECTIONS = 5;
    private static final int HASH_ALGORITHM_INDEX = 0;
    private static final int ITERATION_INDEX = 1;
    private static final int HASH_SIZE_INDEX = 2;
    private static final int SALT_INDEX = 3;
    private static final int PBKDF2_INDEX = 4;

    public String createHash(String password)
        throws HashException
    {
        return this.createHash(password.toCharArray());
    }

    private String createHash(char[] password)
        throws HashException
    {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        int hashSize = hash.length;

        // format: algorithm:iterations:hashSize:salt:hash
        String parts = "sha1:" +
            PBKDF2_ITERATIONS +
            ":" + hashSize +
            ":" +
            toBase64(salt) +
            ":" +
            toBase64(hash);
        return parts;
    }

    public boolean verifyPassword(String password, String correctHash)
        throws HashException
    {
        return verifyPassword(password.toCharArray(), correctHash);
    }

    private boolean verifyPassword(char[] password, String correctHash)
        throws HashException
    {
        // Decode the hash into its parameters
        String[] params = correctHash.split(":");
        if (params.length != HASH_SECTIONS) {
            throw new HashException(
                "Fields are missing from the password hash."
            );
        }

        // Currently, Java only supports SHA1.
        if (!params[HASH_ALGORITHM_INDEX].equals("sha1")) {
            throw new HashException(
                "Unsupported hash type."
            );
        }

        int iterations = 0;
        try {
            iterations = Integer.parseInt(params[ITERATION_INDEX]);
        } catch (NumberFormatException ex) {
            throw new HashException(
                "Could not parse the iteration count as an integer.",
                ex
            );
        }

        if (iterations < 1) {
            throw new HashException(
                "Invalid number of iterations. Must be >= 1."
            );
        }


        byte[] salt = null;
        try {
            salt = fromBase64(params[SALT_INDEX]);
        } catch (IllegalArgumentException ex) {
            throw new HashException(
                "Base64 decoding of salt failed.",
                ex
            );
        }

        byte[] hash = null;
        try {
            hash = fromBase64(params[PBKDF2_INDEX]);
        } catch (IllegalArgumentException ex) {
            throw new HashException(
                "Base64 decoding of pbkdf2 output failed.",
                ex
            );
        }


        int storedHashSize = 0;
        try {
            storedHashSize = Integer.parseInt(params[HASH_SIZE_INDEX]);
        } catch (NumberFormatException ex) {
            throw new HashException(
                "Could not parse the hash size as an integer.",
                ex
            );
        }

        if (storedHashSize != hash.length) {
            throw new HashException(
                "Hash length doesn't match stored hash length."
            );
        }

        // Compute the hash of the provided password, using the same salt, 
        // iteration count, and hash length
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    private boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
        throws HashException
    {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            throw new HashException(
                "Hash algorithm not supported.",
                ex
            );
        } catch (InvalidKeySpecException ex) {
            throw new HashException (
                "Invalid key spec.",
                ex
            );
        }
    }

    private byte[] fromBase64(String hex) throws IllegalArgumentException {
        return DatatypeConverter.parseBase64Binary(hex);
    }

    private String toBase64(byte[] array) {
        return DatatypeConverter.printBase64Binary(array);
    }
}
