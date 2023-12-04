import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Simetrico {
	
	private SecretKey claveSecreta;
	private Cipher cifrador;
	private String fraseEncriptadaBase64;
	
	public Simetrico () {
		try {
			//generar una clave secreta
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			claveSecreta = keyGenerator.generateKey();
			
			//Inicializar el cifrador
			cifrador = Cipher.getInstance("AES");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void encriptarFrase(String frase) {
		try {
			cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta);
			byte[] fraseEncriptada = cifrador.doFinal(frase.getBytes());
			//nuevo Base64
			fraseEncriptadaBase64 = Base64.getEncoder().encodeToString(fraseEncriptada);
			System.out.println("Frase encripatada y guardada en memoria");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void desencriptarFrase() {
		try {
			if (fraseEncriptadaBase64 != null) {
				cifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
				byte[] fraseEncriptada = Base64.getDecoder().decode(fraseEncriptadaBase64);
				byte[] fraseDesencriptada = cifrador.doFinal(fraseEncriptada);
				System.out.println("\nFrase encriptada base 64:  " + new String(fraseEncriptadaBase64));
				System.out.println("\nFrase desencriptada : " + new String(fraseDesencriptada));
			} else {
				System.out.println("no hay frase encriptada en memoria");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
