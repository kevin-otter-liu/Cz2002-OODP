package test.Models;

import java.util.ArrayList;

import test.Enums.GerType;
/**
 * Entity Class for Course
 * @author kevin
 *
 */
public class Course {
	private String school;
	private String courseCode;
	private int NoOfAUS;
	private GerType gerType;
	private int vacancyInCourse;
	
	// will return matric number of students
	private ArrayList<String> listOfRegisteredStudents = new ArrayList<String>();
	// will return index numbers 
	private ArrayList<String> listOfIndexNumbers = new ArrayList<String>();
	
	/**
	 * Constructor for Course
	 * @param school
	 * @param courseCode
	 * @param NoOfAUS
	 * @param gerType
	 * @param vacancyInCourse
	 */
	public Course(String school, String courseCode, int NoOfAUS, GerType gerType, int vacancyInCourse){
		this.setSchool(school);
		this.setCourseCode(courseCode);
		this.setNoOfAUS(NoOfAUS);
		this.setGertype(gerType);
		this.setVacancyInCourse(vacancyInCourse);
	}
	
	
	/**
	 * add matNo of specified student into the course's list of registered students
	 * @param matNo
	 */
	public void addToListOfRegisteredStudents(String matNo) {
		listOfRegisteredStudents.add(matNo);
	}
	/**
	 * adds specified IndexNumber into the course's list of IndexNumber
	 * @param indexNumber
	 */
	public void addToListOfIndexNumbers(String indexNumber) {
		listOfIndexNumbers.add(indexNumber);
	}
	/**
	 * removes student with specified matNo from the course's listOfRegistered students
	 * @param matNo
	 */
	public void removeStudent(String matNo) {
		listOfRegisteredStudents.remove(matNo);
	}

	/**
	 * get School of Course
	 * @return
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * get Course Code of Course
	 * @return
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * get NoOfAus of Course
	 * @return
	 */
	public int getNoOfAUS() {
		return NoOfAUS;
	}
	/**
	 * Get GerType Of Course
	 * @return
	 */
	public GerType getGertype() {
		return gerType;
	}
	/**
	 * get Vacancy in course
	 * @return
	 */
	public int getVacancyInCourse() {
		return vacancyInCourse;
	}
	
	/**
	 * set School Course is allocated to
	 * @param school
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * set CourseCode of Course
	 * @param courseCode
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	/**
	 * set the number of AUS the Course contains
	 * @param noOfAUS
	 */
	public void setNoOfAUS(int noOfAUS) {
		NoOfAUS = noOfAUS;
	}
	/**
	 * Set the Gertype of the Course
	 * @param gerType
	 */
	public void setGertype(GerType gerType) {
		this.gerType = gerType;
	}
	/**
	 * set number of Vacancies in Course
	 * @param vacancyInCourse
	 */
	public void setVacancyInCourse(int vacancyInCourse) {
		this.vacancyInCourse = vacancyInCourse;
	}

	/**
	 * gets the List of IndexNumber
	 * @return
	 */
	public ArrayList<String> getListOfIndexNumbers() {
		return this.listOfIndexNumbers;
	}

	/**
	 * gets the gerType of Course
	 * @return
	 */
	public GerType getGerType() {
		return gerType;
	}

	/**
	 * sets gertype of course
	 * @param gerType
	 */
	public void setGerType(GerType gerType) {
		this.gerType = gerType;
	}

	/**
	 * gets list of Registered students in course
	 * @return
	 */
	public ArrayList<String> getListOfRegisteredStudents() {
		return listOfRegisteredStudents;
	}

}
