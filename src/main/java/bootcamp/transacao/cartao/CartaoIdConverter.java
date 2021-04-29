package bootcamp.transacao.cartao;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class CartaoIdConverter implements AttributeConverter<String, String> {

    private static final String ALGO = "AES";

    private final Key key;
    private final Cipher cipher;

    public CartaoIdConverter(@Value("bootcamp.aes-key") String SECRET) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.key = new SecretKeySpec(SECRET.getBytes(), ALGO);
        this.cipher = Cipher.getInstance(ALGO);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.toBase64String(cipher.doFinal(attribute.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.decode(dbData)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
