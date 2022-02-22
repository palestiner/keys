package org.palestiner.keys.app;

import com.mulesoft.modules.configuration.properties.api.EncryptionAlgorithm;
import com.mulesoft.modules.configuration.properties.api.EncryptionMode;
import org.mule.encryption.Encrypter;
import org.mule.encryption.exception.MuleEncryptionException;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class SecureTool {

    private Encrypter createEncrypter(String algorithm, String mode, String key, boolean useRandomIVs) {
        return EncryptionAlgorithm.valueOf(algorithm).getBuilder().forKey(key).using(EncryptionMode.valueOf(mode)).useRandomIVs(useRandomIVs).build();
    }

    private String decrypt(String value, String algorithm, String mode, String key, boolean useRandomIVs) throws MuleEncryptionException {
        Encrypter encrypter = createEncrypter(algorithm, mode, key, useRandomIVs);
        return new String(encrypter.decrypt(Base64.getDecoder().decode(value)));
    }

    private String encrypt(String value, String algorithm, String mode, String key, boolean useRandomIVs) throws MuleEncryptionException {
        Encrypter encrypter = createEncrypter(algorithm, mode, key, useRandomIVs);
        return new String(Base64.getEncoder().encode(encrypter.encrypt(value.getBytes())));
    }

    private byte[] decrypt(byte[] value, String algorithm, String mode, String key, boolean useRandomIVs) throws MuleEncryptionException {
        Encrypter encrypter = createEncrypter(algorithm, mode, key, useRandomIVs);
        return encrypter.decrypt(Base64.getDecoder().decode(value));
    }

    private byte[] encrypt(byte[] value, String algorithm, String mode, String key, boolean useRandomIVs) throws MuleEncryptionException {
        Encrypter encrypter = createEncrypter(algorithm, mode, key, useRandomIVs);
        return Base64.getEncoder().encode(encrypter.encrypt(value));
    }

    public String applyOverString(String action, String algorithm, String mode, String key, boolean useRandomIVs, String value) throws MuleEncryptionException {
        return action.equals("encrypt") ? encrypt(value, algorithm, mode, key, useRandomIVs) : decrypt(value, algorithm, mode, key, useRandomIVs);
    }


    public String encrypt(String key, String value) {
        return execute("encrypt", key, value);
    }

    public String decrypt(String key, String value) {
        return execute("decrypt", key, value);
    }

    private String execute(String action, String key, String value) {
        try {
            boolean useRandomIVs = false;
            String algorithm = "AES";
            String mode = "CBC";
            return applyOverString(action, algorithm, mode, key, useRandomIVs, value);
        } catch (MuleEncryptionException e) {
            throw new RuntimeException("Some problem:)", e);
        }
    }
}
