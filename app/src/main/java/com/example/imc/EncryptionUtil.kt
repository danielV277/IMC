package com.example.imc

import android.os.Build
import androidx.annotation.RequiresApi
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import java.util.Base64


class EncryptionUtil {
    private val ALGORITHM = "AES"
    // Generar una clave secreta
    fun generateKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(ALGORITHM)
        keyGenerator.init(256) // Puedes usar 128, 192 o 256 bits
        return keyGenerator.generateKey()
    }

    // Encriptar texto
    @RequiresApi(Build.VERSION_CODES.O)
    fun encrypt(plainText: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes = cipher.doFinal(plainText.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    // Desencriptar texto
    @RequiresApi(Build.VERSION_CODES.O)
    fun decrypt(encryptedText: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText))
        return String(decryptedBytes)
    }

}