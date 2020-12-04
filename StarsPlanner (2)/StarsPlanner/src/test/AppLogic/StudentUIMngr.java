package test.AppLogic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Date;
import java.text.ParseException;

import test.MainApp;
import test.Enums.CourseStatus;
import test.Enums.Day;
import test.Models.Course;
import test.Models.IndexNumber;
import test.Models.Lesson;
import test.Models.Student;
import test.UI.PasswordHashing;

/**
 * Controller class that manages boundary class, StudentUI
 * @author kevin
 *
 */
public class StudentUIMngr {
	private Student studentUser;


	/**
	 * Constructor for StudentUIMngr
	 * @param studentUser
	 */
	public StudentUIMngr(Student studentUser){
		this.studentUser = studentUser;
	}
	
	/**
	 * check If the indexNumber's schedule clashes with your registered indexes' timetable
	 * @param indexnumber
	 * @return
	 * @throws ParseException
	 */
	public boolean checkIfClash(String indexnumber) throws ParseException {
		
		ArrayList<String> lessonInfo = new ArrayList<String>();
		ArrayList<String> studentLessonInfo = new ArrayList<String>();
		
		// Add day, starttime, endtime into lessonInfo array list
		IndexNumber selectedIndexNumber = MainApp.indexNumberModel.getIndexNumber(indexnumber);
		ArrayList<String> lessonIds = selectedIndexNumber.getLessons();
		for(int i=0; i<lessonIds.size(); i++) {
			Lesson tempLesson = MainApp.lessonModel.getLesson(lessonIds.get(i));
			lessonInfo.add(tempLesson.getDay().toString());
			lessonInfo.add(tempLesson.getLessonStartTime());
			lessonInfo.add(tempLesson.getLessonEndTime());
		}
		
		// Add day, starttime, endtime into studentLessonInfo array list
		ArrayList<String> studentInfo = studentUser.getListOfRegisteredIndexes();
		for(int i=0; i<studentInfo.size(); i++) {
			IndexNumber tempIndexNumber = MainApp.indexNumberModel.getIndexNumber(studentInfo.get(i));
			ArrayList<String> tempLessonIds = tempIndexNumber.getLessons();
			for(int j=0; j<tempLessonIds.size(); j++) {
				Lesson tempLesson = MainApp.lessonModel.getLesson(tempLessonIds.get(j));
				studentLessonInfo.add(tempLesson.getDay().toString());
				studentLessonInfo.add(tempLesson.getLessonStartTime());
				studentLessonInfo.add(tempLesson.getLessonEndTime());	
			}
		}
		
		for(int i=0; i<lessonInfo.size(); i+=3) {
			for(int j=0; j<studentLessonInfo.size(); j+=3) {
				if(lessonInfo.get(i).equals(studentLessonInfo.get(j))) {
					//if day is same, compare start and end time
					SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
					Date startTimeIndex = sdf.parse(lessonInfo.get(i+1));
					Date endTimeIndex = sdf.parse(lessonInfo.get(i+2));
					Date startTimeStudent = sdf.parse(studentLessonInfo.get(j+1));
					Date endTimeStudent = sdf.parse(studentLessonInfo.get(j+2));
					
					// comparison
					if(startTimeIndex.compareTo(endTimeStudent) <= 0 && startTimeIndex.compareTo(startTimeStudent) >= 0) {
						//clash!
						return true;
					}
					else if (endTimeIndex.compareTo(endTimeStudent) <= 0 && endTimeIndex.compareTo(startTimeStudent) >= 0) {
						//clash!
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * check if there is vacancy in the indexnumber specified
	 * @param indexnumber
	 * @return
	 */
	public boolean checkVacancy(String indexnumber) {
		IndexNumber selectedIndexNumber = MainApp.indexNumberModel.getIndexNumber(indexnumber);
		String coursecode = MainApp.courseModel.returnCourseInIndexNo(indexnumber);
		System.out.println(coursecode + " " + selectedIndexNumber.getIndexNo() + "  Available vacancies:" + selectedIndexNumber.getVacancyInIndex());
		if(selectedIndexNumber.getVacancyInIndex() > 0) return true;
		else return false;
	}
	
	/**
	 * Adds course with indexNumber specified for the student
	 * @param indexnumber
	 */
	public void addCourse(String indexnumber) {
		//need to update student, course and indexnumber
		
		String coursecode = MainApp.courseModel.returnCourseInIndexNo(indexnumber);
		
		//add course code,indexnumber and course status to student object in model
		studentUser.addToListOfRegisteredCourses(coursecode);
		studentUser.addToListOfRegisteredIndexes(indexnumber);
		studentUser.addToListOfCourseStatus(CourseStatus.REGISTERED);
		
		//add to course in model the student registered
		Course selectedcourse = MainApp.courseModel.getCourse(coursecode);
		//get matnum from student
		String matNo = studentUser.getId();
		
		//by right this shouldnt be needed. aka if we add indexnumber. then it should auto add course in indexnumber. add to course's list of students registered
		selectedcourse.addToListOfRegisteredStudents(matNo);
		selectedcourse.setVacancyInCourse(selectedcourse.getVacancyInCourse() - 1);

		//add to indexNUmber in model's registered list of students ,the studentUser
		IndexNumber selectedindexnumber = MainApp.indexNumberModel.getIndexNumber(indexnumber);
		selectedindexnumber.addToListOfRegisteredStudents(matNo);
		selectedindexnumber.setVacancyInIndex(selectedindexnumber.getVacancyInIndex() - 1);
		System.out.println("Course added successfully! \n");
	}
	
	/**
	 * Adds course with specified indexnumber to waitlist of student
	 * updates student in studentModel the list of course registered, list of registered indexNumbers and courseStatus
	 * updates to course in courseModel the list of registered students
	 * updates to index in indexModel's registered list of students
	 * @param indexnumber
	 */
	public void addCourseToWaitlist(String indexnumber) {
		
		//gets course with specified indexnumber
		String coursecode = MainApp.courseModel.returnCourseInIndexNo(indexnumber);
		
		//add to studentUser in the studentmodel the coursecode registered, the indexnumber,and the coursesstatus waitlist
		studentUser.addToListOfRegisteredCourses(coursecode);
		studentUser.addToListOfRegisteredIndexes(indexnumber);
		studentUser.addToListOfCourseStatus(CourseStatus.WAITLIST);

		
		
		//update to course in coursemodel the list of registered students
		String matNo = studentUser.getId();
		Course selectedcourse = MainApp.courseModel.getCourse(coursecode);
		selectedcourse.addToListOfRegisteredStudents(matNo);
		

		//update waitlist of students in index in IndexModel the 
		IndexNumber selectedindexnumber = MainApp.indexNumberModel.getIndexNumber(indexnumber);
		selectedindexnumber.addToWaitList(matNo);
		System.out.println("You are being put on the waitlist! A notification will be sent to you if you are added to the course. \n");
	}
	
	/**
	 * prints all course registered by current student logged in
	 */
	public void printCourseRegistered() {
		
		ArrayList<String> registeredcourses = studentUser.getListOfRegisteredCourses();
		ArrayList<String> registeredindexes = studentUser.getListOfRegisteredIndexes();
		ArrayList<CourseStatus> coursestatus = studentUser.getListOfCourseStatus();
		if(registeredcourses.size() == 0) 
			System.out.println("There are no courses registered. \n");
		else {
			for(int i=0; i<registeredcourses.size(); i++) {
				System.out.println("------------------");
				String coursecode = registeredcourses.get(i);
				Course tempcourse = MainApp.courseModel.getCourse(coursecode);
				String indexnumber = registeredindexes.get(i);
				IndexNumber tempindex = MainApp.indexNumberModel.getIndexNumber(indexnumber);
				CourseStatus tempcoursestatus = coursestatus.get(i);
				ArrayList<String> tempLessonIds = tempindex.getLessons();
				System.out.println(tempcourse.getCourseCode() + "\t" + tempcourse.getGertype() + "\t" + tempindex.getIndexNo() + "\t" + tempcoursestatus);
				System.out.println();
				System.out.println("TYPE/VENUE/DAY/StartTime/EndTime");
				for(int j=0; j<tempLessonIds.size(); j++) {
					Lesson tempLesson = MainApp.lessonModel.getLesson(tempLessonIds.get(j));
					System.out.println(tempLesson.getLessonType() + "\t" + tempLesson.getVenue() + "\t" + tempLesson.getDay() + "\t" + tempLesson.getLessonStartTime() + "\t" + tempLesson.getLessonEndTime());
				}
			}
		}
	}
	
	
	/**
	 * check vacancies available for specified indexNumber
	 * @param indexnumber
	 */
	public void checkVacanciesAvailable(String indexnumber) {
		IndexNumber selectedindexnumber = MainApp.indexNumberModel.getIndexNumber(indexnumber);
		if(selectedindexnumber != null) {
			System.out.println("Number of vacancies in " + selectedindexnumber.getIndexNo() + ": " + selectedindexnumber.getVacancyInIndex() + 
					"/" + (selectedindexnumber.getVacancyInIndex()+selectedindexnumber.getListOfRegisteredStudents().size()) + 
					"[vacancy/total size]");
			System.out.println("Number of people in waitlist: " + selectedindexnumber.getWaitList().size());
		}
		
		else System.out.println("Error: Index number not found. \n");
	}
	
	
	/**
	 * Changes indexNumber of your current Index to the new Index
	 * @param currentindex
	 * @param newindex
	 */
	public void changeIndexOfCourse(String currentindex, String newindex) {
		
		boolean currentindexisinstudent = false;
		int arrayindex = 0;
		String matNo = studentUser.getId();
		ArrayList<String> registeredindexes = studentUser.getListOfRegisteredIndexes();
		
		//checks if current index is registered under your name
		for(int i=0; i<registeredindexes.size(); i++) {
			if(registeredindexes.get(i).equals(currentindex)) {
				currentindexisinstudent = true;
				arrayindex = i;
				break;
			}
		}
		
		if(currentindexisinstudent) {
			//checks if new index exists in database
			if(MainApp.indexNumberModel.hasObject(newindex)){
				//need to check if old and new index have same coursecode
				if(MainApp.courseModel.returnCourseInIndexNo(currentindex).equals(MainApp.courseModel.returnCourseInIndexNo(newindex))){
					//check if new index number has vacancies
					if(MainApp.indexNumberModel.getIndexNumber(newindex).getVacancyInIndex() > 0) {
						//change indexnumber in student
						registeredindexes.set(arrayindex, newindex);
						//update vacancy in old index
						IndexNumber oldindex = MainApp.indexNumberModel.getIndexNumber(currentindex);
						oldindex.setVacancyInIndex(oldindex.getVacancyInIndex()+1);
						ArrayList<String> listofstudentsinindex = oldindex.getListOfRegisteredStudents();
						for(int i=0; i<listofstudentsinindex.size(); i++) {
							if(listofstudentsinindex.get(i).equals(matNo)) {
								listofstudentsinindex.remove(i);
								break;
							}
						}
						//update vacancy in new index
						IndexNumber tempnewindex = MainApp.indexNumberModel.getIndexNumber(newindex);
						tempnewindex.setVacancyInIndex(tempnewindex.getVacancyInIndex()-1);
						tempnewindex.getListOfRegisteredStudents().add(matNo);
						
						System.out.println("Index number is changed successfully! Press 3 to check registered courses!");
					}
					else System.out.println("Error: New index does not have enough vacancies! \n");
				}
				else System.out.println("Error: Current index and New index do not share the same course code \n");
			}
			else System.out.println("Error: Index is not found. \n");
		}
		else System.out.println("Error: Current index is not registered. \n");
	}
	
	/**
	 * swaps Index with a peer
	 * @param yourindex
	 * @param peer_username
	 * @param peer_password
	 * @param peer_index
	 */
	public void swapIndex(String yourindex, String peer_username, String peer_password, String peer_index) {
		//check if yourindex is in studentuser
		boolean indexisinstudentuser = false;
		boolean indexisinpeer = false;
		int arrayindex_studentuser = 0;
		int arrayindex_peer = 0;
		String studentUserMatNo = studentUser.getId();
		
		ArrayList<String> registeredindexes = studentUser.getListOfRegisteredIndexes();
		ArrayList<String> peerregisteredindexes;
		//checks if current index is registered under your name
		for(int i=0; i<registeredindexes.size(); i++) {
			if(registeredindexes.get(i).equals(yourindex)) {
				indexisinstudentuser = true;
				arrayindex_studentuser = i;
				break;
			}
		}
		if(!indexisinstudentuser) {
			System.out.println("Current index is not registered under your name \n");
			return;
		}
		else {
			//check if peer exist
			String matricnumberofpeer = peer_username;
			if(matricnumberofpeer == null) {
				System.out.println("Invalid input for peer username \n");
				return;
			}
			else {
				Student peer = MainApp.studentModel.getStudent(matricnumberofpeer);
				//check if peer exists
				if(peer == null) {
					System.out.println("Username does not exist. \n");
					return;
				}
				//check if peer password is mapped to peer username
				if(!MainApp.passWordModel.checkIfInputIsInDatabase(peer_username, PasswordHashing.hash(peer_password))) {
					System.out.println("Peer's password is not correct \n");
					return;
				}
				//check if peer_index is in peer
				peerregisteredindexes = peer.getListOfRegisteredIndexes();
				for(int i=0; i<peerregisteredindexes.size(); i++) {
					if(peerregisteredindexes.get(i).equals(peer_index)) {
						indexisinpeer = true;
						arrayindex_peer = i;
						break;
					}
				}
				if(!indexisinpeer) {
					System.out.println("Peer does not have that index!");
					return;
				}
				else {
					//need to check if user and peer index have same coursecode
					if(!MainApp.courseModel.returnCourseInIndexNo(yourindex).equals(MainApp.courseModel.returnCourseInIndexNo(peer_index))) {
						System.out.println("Error: Your index and peer index do not share the same course code \n");
						return;
					}
					//do the swapping
					//student needs to be updated
					peerregisteredindexes.set(arrayindex_peer, yourindex);
					registeredindexes.set(arrayindex_studentuser, peer_index);
					//your indexnumber needs to be updated
					IndexNumber yours = MainApp.indexNumberModel.getIndexNumber(yourindex);
					ArrayList registeredstudentsinyours = yours.getListOfRegisteredStudents();
					for(int i=0; i<registeredstudentsinyours.size(); i++) {
						if(registeredstudentsinyours.get(i).equals(studentUserMatNo)) {
							registeredstudentsinyours.set(i, matricnumberofpeer);
							break;
						}
					}
					//peer's indexnumber needs to be updated
					IndexNumber peer1 = MainApp.indexNumberModel.getIndexNumber(peer_index);
					ArrayList registeredstudentsinpeer = peer1.getListOfRegisteredStudents();
					for(int i=0; i<registeredstudentsinpeer.size(); i++) {
						if(registeredstudentsinpeer.get(i).equals(matricnumberofpeer)) {
							registeredstudentsinpeer.set(i, studentUserMatNo);
							break;
						}
					}
					
				}
				System.out.println("Swap Success! \n");
			}
		}
	}

	/**
	 * drops Course with specified coursecode from student's registered or waitlist
	 * @param courseCode
	 */
	public void dropCourse(String courseCode) {
		String matNewlyRegStudent;
		String courseCodeRemoved;
		String indexNumOfDroppedCourse;
		String matNo = studentUser.getId();
		Course droppedCourse;
		int indexOfDudeCourseStatus;
		CourseStatus coursestatus;
		

		
		int arrayIndex = studentUser.getIndexOfArray(courseCode);
		coursestatus = studentUser.getListOfCourseStatus().get(arrayIndex);
		indexNumOfDroppedCourse = studentUser.indexNoOfCourseRegistered(courseCode);
		//remove course
		droppedCourse = studentUser.removeCourse(courseCode);
		
		droppedCourse.removeStudent(matNo);
		//getting indexNumber of dropped course
		
		IndexNumber myindexnumber = MainApp.indexNumberModel.getIndexNumber(indexNumOfDroppedCourse);
		
		
		if(myindexnumber.getWaitList().size() >0 && coursestatus == CourseStatus.REGISTERED) {
			myindexnumber.getListOfRegisteredStudents().remove(matNo); //removed student from indexnumber
			
			matNewlyRegStudent = myindexnumber.getWaitList().remove(); //pop dude from waitlist
			myindexnumber.getListOfRegisteredStudents().add(matNewlyRegStudent); //add dude into list
			//update dude's details
			Student newlyRegStudent = MainApp.studentModel.getStudent(matNewlyRegStudent); 
			indexOfDudeCourseStatus = newlyRegStudent.getListOfRegisteredCourses().indexOf(courseCode); 
			//set dude course status into registered
			newlyRegStudent.getListOfCourseStatus().set(indexOfDudeCourseStatus, CourseStatus.REGISTERED); 
			//Notify the dude
			IndexNumber droppedIndex = MainApp.indexNumberModel.getIndexNumber(indexNumOfDroppedCourse);
			droppedIndex.getNewlyRegistered().add(matNewlyRegStudent);
			droppedIndex.notifyObservers();
			
			
		}
		else if(myindexnumber.getWaitList().size() >0 && coursestatus == CourseStatus.WAITLIST) {
			//when the guy that wants to drop is in waitlist
			matNewlyRegStudent = myindexnumber.getWaitList().remove(); //pop dude from waitlist
		}
		else {
			myindexnumber.getListOfRegisteredStudents().remove(matNo); //removed student from indexnumber
			//when waitlist is empty
			myindexnumber.setVacancyInIndex(myindexnumber.getVacancyInIndex()+1);
			//update course vacancy
			droppedCourse.setVacancyInCourse(droppedCourse.getVacancyInCourse()+1);
		}
	}

	/**
	 * print course info based on course code
	 * @param courseCode
	 */
	public void printCourseInfo(String courseCode) {
		Course selectedCourse = MainApp.courseModel.getCourse(courseCode);
		ArrayList<String> indexNumbersInSelectedCourse = selectedCourse.getListOfIndexNumbers();
		System.out.println(courseCode);
		for(int i=0; i<indexNumbersInSelectedCourse.size(); i++) {
			String indexNumber = indexNumbersInSelectedCourse.get(i);
			System.out.println("INDEX " + indexNumber + ":");
			IndexNumber tempIndex = MainApp.indexNumberModel.getIndexNumber(indexNumber);
			ArrayList<String> lessonsInIndexNumber = tempIndex.getLessons();
			for(int j=0; j<lessonsInIndexNumber.size(); j++) {
				String lessonID = lessonsInIndexNumber.get(j);
				Lesson tempLesson = MainApp.lessonModel.getLesson(lessonID);
				System.out.println(tempLesson.getLessonType() + "(" + tempLesson.getDay() + ")" + "(" + tempLesson.getLessonStartTime() + ")"
						+ "(" + tempLesson.getLessonEndTime() + ")");
			}
		}
		
	}
	
	/**
	 * get student from student Model
	 * @param matNo
	 * @return
	 */
	public Student getStudent(String matNo){
		return MainApp.studentModel.getStudent(matNo);
	}
	/**
	 * checks if indexnumber exists in indexNumberModel
	 * @param indexNum
	 * @return
	 */
	public boolean hasIndex(String indexNum) {
		return MainApp.indexNumberModel.hasObject(indexNum);
	}
	/**
	 * gets Course from respective indexnumber
	 * @param indexnumber
	 * @return
	 */
	public String returnCourseInIndexNo(String indexnumber) {

		return MainApp.courseModel.returnCourseInIndexNo(indexnumber);
	}
	/**
	 * get Course from the coursecode
	 * @param coursecode
	 * @return
	 */
	public Course getCourse(String coursecode) {
		return MainApp.courseModel.getCourse(coursecode);
	}
	/**
	 * check if courseCode exists in the courseModel
	 * @param coursecode
	 * @return
	 */
	public boolean hasCourse(String coursecode) {
		return MainApp.courseModel.hasObject(coursecode);
	}
	
}
