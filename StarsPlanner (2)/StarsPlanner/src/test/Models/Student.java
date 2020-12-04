package test.Models;

import java.util.ArrayList;

import test.MainApp;
import test.Enums.CourseStatus;
import test.Enums.Gender;
import test.Notifications.Notification;
import test.Notifications.SendMailTLS;

/**
 * Entity Class for Student
 * @author kevin
 *
 */
public class Student extends Account{
	// Date time format ; Start and End of Access Period
	private String accessPeriodStart; 
	private String accessPeriodEnd;
	// Contains the list of Courses (Course Code)
	private ArrayList<String> listOfRegisteredCourses = new ArrayList<String>();
	// Contains the list of registered index number
	private ArrayList<String> listOfRegisteredIndexes = new ArrayList<String>();
	// Contains the different status for courses
	private ArrayList<CourseStatus> listOfCourseStatus = new ArrayList<CourseStatus>();
	private String email; //only students have, staff don't have
	
	//added by kevin- a list of notification methods
	private ArrayList<Notification> notificationMethods = new ArrayList<Notification>();
	
	/**
	 * Constructor for Student
	 * @param email
	 * @param gender
	 * @param name
	 * @param id
	 * @param nationality
	 * @param accessPeriodStart
	 * @param accessPeriodEnd
	 */
	public Student(String email, Gender gender, String name, String id, String nationality, String accessPeriodStart, String accessPeriodEnd){
		this.setEmail(email);
		this.setGender(gender);
		this.setName(name);
		this.setId(id);
		this.setNationality(nationality);
		this.setAccessPeriodStart(accessPeriodStart);
		this.setAccessPeriodEnd(accessPeriodEnd);
		//initialise TLSEmail object in the notificationMethods
		this.notificationMethods.add(new SendMailTLS(this.email));
	}
	
	/**
	 * remove Course with courseCode from Student's list of registered course
	 * also removes respective index of course and course status aligned to course
	 * @param courseCode
	 * @return
	 */
	public Course removeCourse(String courseCode){
		int index = listOfRegisteredCourses.indexOf(courseCode);
		String removedCourseStr = listOfRegisteredCourses.get(index);
		Course removedCourse = MainApp.courseModel.getCourse(removedCourseStr);
		listOfRegisteredCourses.remove(index);
		listOfRegisteredIndexes.remove(index);
		listOfCourseStatus.remove(index);
		return removedCourse;
	}
	
	/**
	 * gets index number of course in array with String course code
	 * @param courseCode
	 * @return
	 */
	public int getIndexOfArray(String courseCode) {
		int arrayIndex = listOfRegisteredCourses.indexOf(courseCode);
		return arrayIndex;
	}
	
	/**
	 * removes Course from list of registered course of student and returns the Course removed
	 * @param courseCode
	 * @return
	 */
	public Course removeFromRegCourses(String courseCode) {
		Course courseToRemove = MainApp.courseModel.getCourse(courseCode);
		String courseCodeToRem = courseToRemove.getCourseCode();
		this.listOfRegisteredCourses.remove(courseCodeToRem);
		return courseToRemove;
		
	}
	/**
	 * removes from student's list of registered IndexNumber with specified index and returns Index removed in IndexNumber object
	 * @param index
	 * @return
	 */
	public IndexNumber removeIndexFromRegIndex(String index) {
		IndexNumber indexToRemove = MainApp.indexNumberModel.getIndexNumber(index);
		String indexNo = indexToRemove.getIndexNo();
		this.listOfRegisteredIndexes.remove(indexNo);
		return indexToRemove;
	}
	
	/**
	 * gets String indexNumber of Course registered in students registered list with String courseCode
	 * @param courseCode
	 * @return
	 */
	public String indexNoOfCourseRegistered(String courseCode){
		Course course = MainApp.courseModel.getCourse(courseCode);
		ArrayList<String> indexNumbersInCourse = course.getListOfIndexNumbers();
		
		for (String indexNumberInCourse: indexNumbersInCourse) {
			for(String registeredIndexNumber: listOfRegisteredIndexes) {
				if(registeredIndexNumber.equals(indexNumberInCourse)) {
					return registeredIndexNumber;
				}
			}
		}
		return null;
	}
	
	/**
	 * get all notification methods student has
	 * @return
	 */
	public ArrayList<Notification> getNotificationMethods() {
		return notificationMethods;
	}

	/**
	 * set notification methods for  student
	 * @param notificationMethods
	 */
	public void setNotificationMethods(ArrayList<Notification> notificationMethods) {
		this.notificationMethods = notificationMethods;
	}

	/**
	 * set list of registered courses for student
	 * @param listOfRegisteredCourses
	 */
	public void setListOfRegisteredCourses(ArrayList<String> listOfRegisteredCourses) {
		this.listOfRegisteredCourses = listOfRegisteredCourses;
	}

	/**
	 * set list of registerd Indexes of student
	 * @param listOfRegisteredIndexes
	 */
	public void setListOfRegisteredIndexes(ArrayList<String> listOfRegisteredIndexes) {
		this.listOfRegisteredIndexes = listOfRegisteredIndexes;
	}

	/**
	 * set list of course status in student
	 * @param listOfCourseStatus
	 */
	public void setListOfCourseStatus(ArrayList<CourseStatus> listOfCourseStatus) {
		this.listOfCourseStatus = listOfCourseStatus;
	}

	/**
	 * get list of registered courses of student
	 * @return
	 */
	public ArrayList<String> getListOfRegisteredCourses(){
		return listOfRegisteredCourses;
	}
	
	/**
	 * get list of registered indexes of student
	 * @return
	 */
	public ArrayList<String> getListOfRegisteredIndexes(){
		return listOfRegisteredIndexes;
	}
	
	/**
	 * get list of course status of student
	 * @return
	 */
	public ArrayList<CourseStatus> getListOfCourseStatus(){
		return listOfCourseStatus;
	}
	
	/**
	 * get access period end time of student
	 * @return
	 */
	public String getAccessPeriodEnd() {
		return accessPeriodEnd;
	}
	
	/**
	 * get access period start time of student
	 * @return
	 */
	public String getAccessPeriodStart() {
		return accessPeriodStart;
	}
	
	/**
	 * get email address of student
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * add specifiedcoursecode to list of registered courses
	 * @param coursecode
	 */
	public void addToListOfRegisteredCourses(String coursecode) {
		listOfRegisteredCourses.add(coursecode);
	}
	
	/**
	 * add specified index number to list of registered index numbers
	 * @param index
	 */
	public void addToListOfRegisteredIndexes(String index) {
		listOfRegisteredIndexes.add(index);
	}
	
	/**
	 * add specified course status to list of course status
	 * @param coursestatus
	 */
	public void addToListOfCourseStatus(CourseStatus coursestatus) {
		listOfCourseStatus.add(coursestatus);
	}

	/**
	 * set's students access period start time
	 * @param accessPeriodStart
	 */
	public void setAccessPeriodStart(String accessPeriodStart) {
		this.accessPeriodStart = accessPeriodStart;
	}

	/**
	 * sets a students access period end time
	 * @param accessPeriodEnd
	 */
	public void setAccessPeriodEnd(String accessPeriodEnd) {
		this.accessPeriodEnd = accessPeriodEnd;
	}

	/**
	 * sets email address of student
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
