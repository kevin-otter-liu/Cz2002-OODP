package test.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import test.FileIO;
import test.Enums.Mode;
import test.UI.PasswordHashing;
/**
 * PassWordModel Class is an Entity class containing many userid,password,mode mappings
 * @author kevin
 *
 */
public class PasswordModel implements Model{
	private FileIO fileIO;
	private HashMap<String, List<String>> loginDetails = new HashMap<String, List<String>>(); //matNo will map to [passWord, mode]
	public PasswordModel(){
		populate();
	}
	
	/**
	 * get login database
	 * @return
	 */
	public HashMap<String, List<String>> getLoginDetails(){
		return loginDetails;
	}
	/**
	 * populate the PassWordModel with the EncryptedPassword.txt file. EncryptedPassword.txt file contains the database for all user's login
	 * details in a .txt file.
	 */
	public void populate() {
		fileIO = new FileIO();
		List<String> result = fileIO.readData("TextDatabase/EncryptedPassword.txt");
		for(int i=1; i<result.size(); i++) {
			String[] extractData = result.get(i).split("_");
			List<String> temp = new ArrayList<String>();
			temp.add(extractData[1]);
			temp.add(extractData[2]);
	    	loginDetails.put(extractData[0], temp);
	     }
	}
	
	/**
	 * adds a new user's login details in the PassWordModel
	 * @param matNo
	 * @param passWord
	 * @param mode
	 */
	public void addLoginDetail(String matNo, String passWord, Mode mode) {
		List<String> temp = new ArrayList<String>();
		temp.add(PasswordHashing.hash(passWord));
		temp.add(mode.name());
		loginDetails.put(matNo, temp);
	}

	/**
	 * checks if user with id is in the PassWordModel
	 * if id is in the PassWordModel, returns the encrypted password
	 * @param id
	 * @param passWord
	 * @return
	 */
	public boolean checkIfInputIsInDatabase(String id, String passWord) {
		if(loginDetails.get(id)==null) {
			return false;
		}
		else return passWord.equals(loginDetails.get(id).get(0));
	}
	
	/**
	 * gets the mode of the user with specified id
	 * @param id
	 * @return
	 */
	public Mode getMode(String id) {
		return Mode.valueOf(loginDetails.get(id).get(1));
	}
	
}
