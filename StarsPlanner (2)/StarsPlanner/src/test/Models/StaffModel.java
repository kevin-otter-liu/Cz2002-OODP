package test.Models;

import java.util.ArrayList;
import java.util.List;

import test.FileIO;
import test.Enums.Gender;

/**
 * StaffModel Class is an Entity class containing many Staff
 * @author kevin
 *
 */
public class StaffModel implements Model{
	private FileIO fileIO;
	private ArrayList<Staff> allStaff = new ArrayList<Staff>();
	public StaffModel() {
		populate();
	}
	/**
	 * get all Staff in the StaffModel
	 * @return
	 */
	public ArrayList<Staff> getAllStaff(){
		return allStaff;
	}

	/**
	 * populate the StaffModel with the Staff.txt file. Staff.txt file contains the database for Staff in a .txt file.
	 */
	public void populate() {
		fileIO = new FileIO();
		List<String> result = fileIO.readData("TextDatabase/Staff.txt");
		for(int i=1; i<result.size(); i++) {
	    	 String[] extractData = result.get(i).split("_");
	    	 Staff tempStaff = new Staff(Gender.valueOf(extractData[0]), 
	    			 extractData[1], extractData[2], extractData[3]);
	    	 allStaff.add(tempStaff);
	     }
	}
	
	//Checks if Staff with staff id exists in StaffModel
	public boolean hasObject(String primaryKey) {
		for(int i=0; i<allStaff.size(); i++) {
			if(allStaff.get(i).getId().equals(primaryKey)) return true;
		}
		return false;
	}
}
