package controller;
import model.Usuario;

public class BCrypt extends java.lang.Object {
	
	static String pwhash;
	static String salt;
	
	Usuario user = new Usuario();
	
	public static java.lang.String hashpw(java.lang.String password, java.lang.String salt) throws java.lang.IllegalArgumentException{				
		
		return pwhash;
	}
	
	public static java.lang.String gensalt(int log_rounds, java.security.SecureRandom random){
		
		return salt;	
	}
	
	String senha_hash = BCrypt.hashpw(pwhash, BCrypt.gensalt(0, null)); 

}
