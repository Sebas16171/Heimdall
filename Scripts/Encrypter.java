package Scripts;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encrypter {

    public Encrypter(){}

    public String encrypt(int method, String text, String key){
        String output = "";
        switch (method) {
            case 0: //B64
                output = Base64.getEncoder().encodeToString(text.getBytes());
                break;
            case 1: // Bwfsh
            try {
                byte[] keyData = (key).getBytes();
                SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
                Cipher cipher = Cipher.getInstance("Blowfish");
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                byte[] hasil = cipher.doFinal(text.getBytes());
                output = new String(Base64.getEncoder().encode(hasil));
            } catch (Exception e) {
                e.printStackTrace();
                output = null;
            }
                break;
        
            default:
                break;
        }
        return output;
    }

    public String decrypt(int method, String text, String key){
        String output = "";
        switch (method) {
            case 0: //B64
                byte[] decodedBytes = Base64.getDecoder().decode(text);
                output = new String(decodedBytes);
                break;
            case 1: //Bwfsh
            try {
                byte[] keyData = (key).getBytes();
                SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
                Cipher cipher = Cipher.getInstance("Blowfish");
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                byte[] hasil = cipher.doFinal(java.util.Base64.getDecoder().decode(text));
                output = new String(hasil);
            } catch (Exception e) {
                e.printStackTrace();
                output = null;
            }
            break;
            default:
                break;
        }
        return output;
    }

    public String[] getAlgorithms(){
        String output[] = {
            "Base64",
            "Blowfish"};
        return output;
    }

}
