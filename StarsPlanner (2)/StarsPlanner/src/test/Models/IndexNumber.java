package test.Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import test.MainApp;
/**
 * Entity Class of Index Number of Course
 * @author kevin
 *
 */
public class IndexNumber implements Subject{
	private String indexNo;
	// Number of vacancies in the index
	private int vacancyInIndex;
	// Contains list of registered students (Matric Number of students)
	private ArrayList<String> listOfRegisteredStudents = new ArrayList<String>();
	// Contains list of students in the waitlist (Matric Number of students)
	private Queue<String> waitList = new LinkedList<>();
	
	private Queue<String>newlyRegistered = new LinkedList<>();
	// Contains the list of the type of lessons (LessonID)
	private ArrayList<String> Lessons = new ArrayList<String>(); 
	
	/**
	 * gets the Queue of newly Registered students who were just popped into the waitlist and does not have email sent to them yet
	 * @return
	 */
	public Queue<String> getNewlyRegistered() {
		return newlyRegistered;
	}

	/**
	 * sets the Queue of newly Registered students who were just popped into the waitlist and does not have email sent to them yet
	 * @param newlyRegistered
	 */
	public void setNewlyRegistered(Queue<String> newlyRegistered) {
		this.newlyRegistered = newlyRegistered;
	}

	/**
	 * sets list or lessons in a index number
	 * @param lessons
	 */
	public void setLessons(ArrayList<String> lessons) {
		Lessons = lessons;
	}

	/**
	 * Constructor for Index Number
	 * @param indexNo
	 * @param vacancyInIndex
	 */
	public IndexNumber(String indexNo, int vacancyInIndex)  {
		this.setIndexNo(indexNo);
		this.setVacancyInIndex(vacancyInIndex);
	}
	
	/**
	 * Notifies Observers, students who are newly registered from waitlist by sending email to them once there exists a student in
	 * the newly registered list
	 */
	public void notifyObservers() {
		if (!newlyRegistered.isEmpty()) {
			for(String MatNo : newlyRegistered) {
				Student student = MainApp.studentModel.getStudent(MatNo);
				student.getNotificationMethods().get(0).sendNotification();
				newlyRegistered.remove();
			}
		}
		
	}
	/**
	 * gets list of registered students in Index Number
	 * @return
	 */
	public ArrayList<String> getListOfRegisteredStudents(){
		return listOfRegisteredStudents;
	}
	
	/*
	 * gets the waitList of students in Index Number
	 */
	public Queue<String> getWaitList(){
		return waitList;
	}
	
	/*
	 * gets lessons of the Index Number
	 */
	public ArrayList<String> getLessons(){
		return Lessons;
	}
	
	/**
	 * gets String Index Number of the Index Number
	 * @return
	 */
	public String getIndexNo() {
		return indexNo;
	}
	
	/**
	 * gets vacancy in Indexn Number
	 * @return
	 */
	public int getVacancyInIndex() {
		return vacancyInIndex;
	}
	
	/**
	 * Sets String Index Number for the Index Number
	 * @param indexNo
	 */
	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}
	/**
	 * set vacancy in Index Number
	 * @param vacancyInIndex
	 */
	public void setVacancyInIndex(int vacancyInIndex) {
		this.vacancyInIndex = vacancyInIndex;
	}
	
	/**
	 * add matric Number of student to list of registered students of Index Number
	 * @param matricnum
	 */
	public void addToListOfRegisteredStudents(String matricnum) {
		listOfRegisteredStudents.add(matricnum);
	}
	
	/**
	 * add matric Number of student to waitList of IndexNumber
	 * @param matricnum
	 */
	public void addToWaitList(String matricnum) {
		waitList.add(matricnum);
	}
	
	/**
	 * add lesson id to list of lesson ids in Index Number
	 * @param lessonid
	 */
	public void addToLessons(String lessonid) {
		Lessons.add(lessonid);
	}




}
