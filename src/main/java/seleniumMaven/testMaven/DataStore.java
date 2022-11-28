package seleniumMaven.testMaven;

public class DataStore {

	private static String guru99User;
	private static String guru99Pass;
	
	private DataStore() {
		
		
	}
	

	public static void setUser(String user) {
		
		guru99User = user;
		
	}

	public static void setPass(String pass) {
		
		guru99Pass = pass;
		
	}
	
	public static String getUser() {
		
		return guru99User;
		
	}

	
	public static String getPass() {
		
		return guru99Pass;
		
	}
	
}
