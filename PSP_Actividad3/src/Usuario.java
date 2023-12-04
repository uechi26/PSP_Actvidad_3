import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
	
	private String userName;
	private String userPassHashed;
	
	//generar constructores
	public Usuario(String userName, String userPass) {
		this.userName = userName;
		this.userPassHashed = HashPassword(userPass);
	}
	
	//generar getters
	public String getUserName() {
		return userName;
	}

	public String getuserPassHashed() {
		return userPassHashed;
	}
	
	//creamos un metodo que recibe un string(contraseña) y devuelve otro string(contraseña hasheada)
	String HashPassword(String password) {
		try {
			//Creamos un objeto MessageDigest a través del método estático 
			//getInstance() al que se le pasa el tipo de algoritmo que vamos a 
			//utilizar. 
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(password.getBytes());
			return bytesToHex(encodedhash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2* hash.length);
		for(byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length()==1) {
				hexString.append("0");
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
