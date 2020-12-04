package test.Models;

import java.util.ArrayList;
import java.util.List;

import test.FileIO;
import test.Enums.GerType;
/**
 * CourseModel Class is an Entity Class that stores all Course objects
 * @author kevin
 *
 */
public class CourseModel implements Model{
	private FileIO fileIO;
	private ArrayList<Course> allCourses = new ArrayList<Course>();
	public CourseModel() {
		populate();
	}
	/**
	 * gets all Courses in the Course Model
	 * @return
	 */
	public ArrayList<Course> getAllCourses(){
		return allCourses;
	}

	/**
	 * populate the CourseModel with the Course.txt file. Course.txt file contains the database for Courses in a .txt file.
	 */
	public void populate() {
		fileIO = new FileIO();
		// Reading Course.txt
		List<String> result = fileIO.readData("TextDatabase/Course.txt");
		for(int i=1; i<result.size(); i++) {
			// Reading of file ; split with "_"
	    	 String[] extractData = result.get(i).split("_");
	    	 Course tempCourse = new Course(extractData[0], extractData[1], Integer.parseInt(extractData[2]), 
	    			 GerType.valueOf(extractData[3]), Integer.parseInt(extractData[4]));
	    	 int numberOfRegisteredStudents = Integer.parseInt(extractData[5]);
	    	 
	    	 for(int j=0; j<numberOfRegisteredStudents; j++) {
	    		 tempCourse.addToListOfRegisteredStudents(extractData[6+j]);
	    	 }
	    	 int numberOfIndexNumbers = Integer.parseInt(extractData[6+numberOfRegisteredStudents]);
	    	 for(int j=0; j<numberOfIndexNumbers; j++) {
	    		 tempCourse.addToListOfIndexNumbers(extractData[7+numberOfRegisteredStudents+j]);
	    	 }
	    	 allCourses.add(tempCourse);
	     }
	}
	
	/**
	 * get Course with the specified course Code 
	 * @param courseCode
	 * @return
	 */
	public Course getCourse(String courseCode) {
		for(int i=0; i<allCourses.size(); i++) {
			if(allCourses.get(i).getCourseCode().equals(courseCode)) return allCourses.get(i);
		}
		return null;
	}
	
	/**
	 * 
	 * @param indexNumber
	 * @return
	 */
	public String returnCourseInIndexNo(String indexNumber) {
		for(int i=0; i<allCourses.size(); i++) {
			ArrayList<String> tempList = allCourses.get(i).getListOfIndexNumbers();
			for(int j=0; j<tempList.size(); j++) {
				if(tempList.get(j).equals(indexNumber)) return allCourses.get(i).getCourseCode();
			}
		}
		return null;
	}
	
	/**
	 * checks if Course with specified course code exists 
	 */
	public boolean hasObject(String coursecode) {
		for(int i=0; i<allCourses.size(); i++) {
			if(allCourses.get(i).getCourseCode().equals(coursecode)) return true;
		}
		return false;
	}
	
	/**
	 * add Course into Course Model
	 * @param course
	 */
	public void addCourse(Course course) {
		allCourses.add(course);
	}
	
	
	
}
