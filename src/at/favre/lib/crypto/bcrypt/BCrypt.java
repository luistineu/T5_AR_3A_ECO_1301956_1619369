package at.favre.lib.crypto.bcrypt;

//import org.mindrot.jbcrypt.BCrypt;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class BCrypt {
	
	 //static String pwhash;
	 //static String salt;
	 
	//private static final Logger log = LoggerFactory.getLogger(BCrypt.class);

	private final int logRounds;
	
	public BCrypt(int logRounds){
		this.logRounds = logRounds;
	}
	
	public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }
	
	public boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
	
	public static boolean checkpw(java.lang.String password,
            java.lang.String hashed) {
		
		if (BCrypt.checkpw(password, hashed)) {
		    System.out.println("It matches");
			return true;
			}
		else
		    System.out.println("It does not match");
			return false;
	}
	
	/*public boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
        if (BCrypt.checkpw(password, hash)) {
            int rounds = getRounds(hash);
            // It might be smart to only allow increasing the rounds.
            // If someone makes a mistake the ability to undo it would be nice though.
            if (rounds != logRounds) {
                Log.debug("Updating password from {} rounds to {}", rounds, logRounds);
                String newHash = hash(password);
                return updateFunc.apply(newHash);
            }
            return true;
        }
        return false;
    }*/
	
	public static java.lang.String gensalt(int log_rounds){
		return null;		
	}
	
	private int getRounds(String salt) {
        char minor = (char)0;
        int off = 0;

        if (salt.charAt(0) != '$' || salt.charAt(1) != '2')
            throw new IllegalArgumentException ("Invalid salt version");
        if (salt.charAt(2) == '$')
            off = 3;
        else {
            minor = salt.charAt(2);
            if (minor != 'a' || salt.charAt(3) != '$')
                throw new IllegalArgumentException ("Invalid salt revision");
            off = 4;
        }
        
     // Extract number of rounds
        if (salt.charAt(off + 2) > '$')
            throw new IllegalArgumentException ("Missing salt rounds");
        
        return Integer.parseInt(salt.substring(off, off + 2));
	}
	
	
	public static java.lang.String hashpw(java.lang.String password, java.lang.String salt)
			throws java.lang.IllegalArgumentException {
				return password + salt;
				
	}
	
}
