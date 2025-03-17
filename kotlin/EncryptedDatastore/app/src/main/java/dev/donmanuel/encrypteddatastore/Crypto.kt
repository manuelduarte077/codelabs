package dev.donmanuel.encrypteddatastore

import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object Crypto {
    /**
     * The transformation used for encryption and decryption.
     *
     * AES - Advanced Encryption Standart is most secure algorithm for encryption.
     *
     * GCM - Galois Counter Mode is used for authentication and integrity.
     *
     *  NoPadding - No padding is not needed for encryption because it can
     * cause issues like padding oracle attacks.
     */
    private const val TRANSFORMATION = "AES/GCM/NoPadding"

    /** The length of the authentication tag used for GCM. */
    private const val TAG_LENGTH = 128

    /** The length of the initialization vector used for
     *  to ensure the randomness of the first encrypted
     *  block of data, preventing identical plaintexts
     *  from encrypting to the same ciphertext.
     *
     * */
    private const val IV_LENGTH = 12

    /** The list of hash algorithms used to generate the secret key. */
    private val hashAlgorithms = mutableListOf("SHA3-256", "SHA2-256", "SHA-256")

    val cipher = Cipher.getInstance(TRANSFORMATION)
    val ivParameterSpec = IvParameterSpec(ByteArray(IV_LENGTH))
    val gcmParameterSpec = GCMParameterSpec(TAG_LENGTH, ivParameterSpec.iv)

    /**
     * Generates a secret key from a given text.
     * The key is generated using most secure available hash algorithm.
     * If the algorithm is not available, it will try the next one.
     * @param text The text to generate the key from.
     * @return The generated secret key.
     */
    fun generateAESKeyFromText(text: String): SecretKey {
        var messageDigest: MessageDigest? = null
        while (messageDigest == null) {
            try {
                messageDigest = MessageDigest.getInstance(hashAlgorithms.first())
            } catch (e: Exception) {
                if (hashAlgorithms.isEmpty()) {
                    throw e
                }
                hashAlgorithms.removeAt(0)
            }
        }

        val textByteArray = text.toByteArray()
        messageDigest.update(textByteArray)
        val hashedBytes = messageDigest.digest()

        val result = SecretKeySpec(hashedBytes, "AES")
        return result
    }

    /**
     * Encrypts the given data using AES/GCM/NoPadding algorithm.
     * @param data The data to encrypt.
     * @param secretKey The secret key to use for encryption.
     * @return The encrypted data.
     */
    fun encrypt(
        data: ByteArray,
        secretKey: SecretKey = generateAESKeyFromText("secretKeyfalanfap@doas92h")
    ): ByteArray {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec)
        return cipher.doFinal(data)
    }

    /**
     * Decrypts the given data using AES/GCM/NoPadding algorithm.
     * @param data The data to decrypt.
     * @param secretKey The secret key to use for decryption.
     * @return The decrypted data.
     */
    fun decrypt(
        data: ByteArray,
        secretKey: SecretKey = generateAESKeyFromText("secretKeyfalanfap@doas92h")
    ): ByteArray {
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec)
        return cipher.doFinal(data)
    }
}