package test.Models;

import java.util.ArrayList;
import java.util.List;

import test.FileIO;
/**
 * IndexNumberModel Class is an Entity class containing many IndexNumberModel
 * @author kevin
 *
 */
public class IndexNumberModel implements Model{
	private FileIO fileIO;
	private ArrayList<IndexNumber> allIndexNumbers = new ArrayList<IndexNumber>();
	public IndexNumberModel() {
		populate();
	}
	
	/**
	 * gets all index numbers in IndexNumberModel
	 * @return
	 */
	public ArrayList<IndexNumber> getAllIndexNumbers(){
		return allIndexNumbers;
	}

	/**
	 * populate the IndexNumberModel with the IndexNumber.txt file. IndexNumber.txt file contains the database for IndexNumber in a .txt file.
	 */
	public void populate() {
		fileIO = new FileIO();
		List<String> result = fileIO.readData("TextDatabase/IndexNumber.txt");
		for(int i=1; i<result.size(); i++) {
	    	 String[] extractData = result.get(i).split("_");
	    	 IndexNumber tempIndexNumber = new IndexNumber(extractData[0], Integer.parseInt(extractData[1]));
	    	 int noOfRegStudents = Integer.parseInt(extractData[2]);
	    	 for(int j=0; j<noOfRegStudents; j++) {
	    		 tempIndexNumber.addToListOfRegisteredStudents(extractData[3+j]);
	    	 }
	    	 int numStudInWaitList = Integer.parseInt(extractData[2+noOfRegStudents+1]);
	    	 for(int j=0; j<numStudInWaitList; j++) {
	    		 tempIndexNumber.addToWaitList(extractData[4+noOfRegStudents+j]);
	    	 }
	    	 int numOfLessons = Integer.parseInt(extractData[numStudInWaitList+noOfRegStudents+1+1+2]);
	    	 for(int j=0; j<numOfLessons; j++) {
	    		 tempIndexNumber.addToLessons(extractData[numStudInWaitList+noOfRegStudents+5+j]);
	    	 }
	    	 allIndexNumbers.add(tempIndexNumber);
	     }
	}
	
	/**
	 * get IndexNumber of specified String Index Number
	 * @param indexNo
	 * @return
	 */
	public IndexNumber getIndexNumber(String indexNo) {
		for(int i=0; i<allIndexNumbers.size(); i++) {
			if(allIndexNumbers.get(i).getIndexNo().equals(indexNo)) return allIndexNumbers.get(i);
		}
		return null;
	}
	
	/**
	 * checks if IndexNumber with String indexNo exists in IndexNumberModel
	 */
	public boolean hasObject(String indexNo) {
		for(int i=0; i<allIndexNumbers.size(); i++) {
			if(allIndexNumbers.get(i).getIndexNo().equals(indexNo)) return true;
		}
		return false;
	}
	
	/**
	 * adds IndexNumber to IndexNumberModel
	 * @param indexNo
	 */
	public void addIndexNumber(IndexNumber indexNo) {
		allIndexNumbers.add(indexNo);
	}
}
