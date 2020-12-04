package test.UI;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import test.MainApp;
import test.AppLogic.LoginUIMngr;
import test.Enums.Mode;

/**
 * Boundary Class for Login User Interface
 * @author kevin
 *
 */
public class LoginUI {
	private String userName;
	private String passWord;
	private LoginUIMngr loginUIMngr = new LoginUIMngr();
	/**
	 * prints login menu
	 */
	public void printLogin() {
	    Scanner myscanner = new Scanner(System.in);
	    System.out.println("Login Menu");
	    System.out.print("Username: ");
	    this.userName = myscanner.nextLine();
	    System.out.print("Password: ");
	    
	    //make console
	    Console cons = System.console();
	    if (cons == null) {
	      // We are in the Eclipse IDE.
	    	try {
	    		System.out.println("\nLOG: Running within Eclipse IDE...");
	    		System.out.println("LOG: Password will not be masked");
	    		this.passWord = PasswordHashing.hash(getPasswordWithinEclipse());
	    		//System.out.println("LOG: Password entered");
	    	}catch (IOException e) {
	    		System.err.println("Error getting password" + e.getMessage() + "\n");
	    		System.exit(1);
	    	}
		} else {
	    		this.passWord = PasswordHashing.hash(getPasswordMasked(cons));
	    }
	    
	}

    /**
     * Handles console input when running outside of Eclipse.
     * 
     * @param cons the console to use in order to receive input
     * @return the password input by the user
     */
	private String getPasswordMasked(Console cons)
    {
        char[] passwd;
        while (true) {
            passwd = cons.readPassword();
            if (passwd != null) {
                if (passwd.length > 0) {
                    return new String(passwd);
                } else {
                    System.out.println("Invalid input\n");
                }
            }
        }
    }
	
	/**
     * Handles console input when running inside of Eclipse
     * 
     * @return the password input by the user
     * @throws IOException if password is zero-length
     */
	public static String getPasswordWithinEclipse() 
            throws IOException 
    {
        // In Eclipse IDE
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String password = reader.readLine();
        if (password != null) {
            if (password.length() <= 0) {
                System.out.println("Invalid input\n");
                throw new IOException("Error reading in password \n");
            }
        }
        return password;
    }

	
	/**
	 * checks if user with userName is in the PassWordModel
	 * if id is in the PassWordModel, returns the encrypted password
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public boolean checkIfInputIsInDatabase(String userName, String passWord) {
		return loginUIMngr.checkIfInputIsInDatabase(userName, passWord);
	}
	
	/**
	 * returns Mode of the specified username
	 * @param userName
	 * @return
	 */
	public Mode returnMode(String userName) {
		return loginUIMngr.returnMode(userName);
	}
	
	
	/**
	 * get the String UserName from the user
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * get the String encrypted Password from the user
	 * @return
	 */
	public String getPassword() {
		return passWord;
	}

}
