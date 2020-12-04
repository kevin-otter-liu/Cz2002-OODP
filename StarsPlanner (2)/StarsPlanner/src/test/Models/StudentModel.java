package test.Models;

import java.util.ArrayList;
import java.util.List;

import test.FileIO;
import test.Enums.CourseStatus;
import test.Enums.Gender;
/**
 * StudentModel Class is an Entity class containing many Students
 * @author kevin
 *
 */
public class StudentModel implements Model{
	private FileIO fileIO;
	private ArrayList<Student> allStudents = new ArrayList<Student>();
	
	
	/**
	 * Constructor for StudentModel
	 */
	public StudentModel(){
		populate();
	}
	
	/**
	 * populate the StudentModel with the Student.txt file. Student.txt file contains the database for Students in a .txt file.
	 */
	public void populate() {
		fileIO = new FileIO();
		List<String> result = fileIO.readData("TextDatabase/Student.txt");
		for(int i=1; i<result.size(); i++) {
	    	 String[] extractData = result.get(i).split("_");
	    	 Student tempStudent = new Student(extractData[0],Gender.valueOf(extractData[1]), 
	    			 extractData[2], extractData[3], extractData[4], extractData[5], extractData[6]);
	    	 int numOfRegCourses = Integer.parseInt(extractData[7]);
	    	 for(int j=0; j<numOfRegCourses*3; j+=3) {
	    		 tempStudent.addToListOfRegisteredCourses(extractData[8+j]);
	    		 tempStudent.addToListOfRegisteredIndexes(extractData[9+j]);
	    		 tempStudent.addToListOfCourseStatus(CourseStatus.valueOf(extractData[10+j]) ); 
	    	 }
	    	 allStudents.add(tempStudent);
	     }
	}
	/**
	 * add Student Object into StudentModel
	 * @param student
	 */
	public void addStudent(Student student) {
		allStudents.add(student);
	}
	
	/**
	 * checks if Student with matNo exists in StudentModel
	 * @param matNo
	 * @return
	 */
	public boolean hasObject(String matNo) {
		for(int i=0; i<allStudents.size(); i++) {
			if(allStudents.get(i).getId().equals(matNo)) return true;
		}
		return false;
	}
	
	
	/**
	 * getStudent with specified matNo fromm StudentModel
	 * @param matNo
	 * @return
	 */
	public Student getStudent(String matNo) {
		for(int i=0; i<allStudents.size(); i++) {
			if(allStudents.get(i).getId().equals(matNo)) return allStudents.get(i);
		}
		return null;
	}

	/**
	 * gets all students from StudentModel
	 * @return
	 */
	public ArrayList<Student> getAllStudents(){
		return allStudents;
	}
}
