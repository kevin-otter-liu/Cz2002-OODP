package test.AppLogic;

import java.util.ArrayList;

import test.MainApp;
import test.Enums.Day;
import test.Enums.Gender;
import test.Enums.GerType;
import test.Enums.LessonType;
import test.Enums.Mode;
import test.Models.Course;
import test.Models.IndexNumber;
import test.Models.Lesson;
import test.Models.Staff;
import test.Models.Student;

/**
 * Controller Class of StaffUI Class
 * @author kevin
 *
 */
public class StaffUIMngr {


private Staff staffUser;	
	

	/**
	 * Constructor for StaffUIMngr
	 * @param staffUser
	 */
	public StaffUIMngr(Staff staffUser){
		this.staffUser= staffUser;
	}
	/**
	 * change Access Period of the student with matricnum to new accessPeriod
	 * @param matricnum
	 * @param accessPeriodStart
	 * @param accessPeriodEnd
	 */
	public void changeAccessPeriod(String matricnum, String accessPeriodStart, String accessPeriodEnd) {
		Student tempstudent = MainApp.studentModel.getStudent(matricnum);
		tempstudent.setAccessPeriodStart(accessPeriodStart);
		tempstudent.setAccessPeriodEnd(accessPeriodEnd);
		
	}
	/**
	 * Adds new Student into studentModel
	 * @param email
	 * @param gender
	 * @param name
	 * @param id
	 * @param nationality
	 * @param accessPeriodStart
	 * @param accessPeriodEnd
	 */
	public void addStudent(String email, Gender gender, String name, String id, String nationality, String accessPeriodStart, String accessPeriodEnd) {
		Student tempstudent = new Student(email, gender, name, id, nationality, accessPeriodStart, accessPeriodEnd);
		MainApp.studentModel.addStudent(tempstudent);
	}
	
	/**
	 * Adds new id, mode, password into passWordModel
	 * @param id
	 * @param password
	 * @param mode
	 */
	public void addLoginDetails(String id, String password, Mode mode) {
		MainApp.passWordModel.addLoginDetail(id, password, mode);
	}
	
	/**
	 * print all students in the studentModel database
	 */
	public void printAllStudents() {
		System.out.println("-----All Students-----");
		ArrayList<Student> temp = MainApp.studentModel.getAllStudents();
		for(int i=0; i<temp.size(); i++) {
			System.out.println(temp.get(i).getId() + " " + temp.get(i).getName());
		}
		System.out.println("----------------------\n");
	}
	
	/**
	 * Adds new Course into the courseModel
	 * @param school
	 * @param coursecode
	 * @param noofaus
	 * @param gertype
	 * @param vacancyInCourse
	 * @param indexnumbers
	 */
	public void addCourse(String school, String coursecode, int noofaus, GerType gertype, int vacancyInCourse, String[] indexnumbers) {
		Course tempcourse = new Course(school, coursecode, noofaus, gertype, vacancyInCourse);
		for(int i=0; i<indexnumbers.length; i++) {
			tempcourse.addToListOfIndexNumbers(indexnumbers[i]);
		}
		MainApp.courseModel.addCourse(tempcourse);
	}
	
	/**
	 * Adds new IndexNumber into the indexNumberModel
	 * @param indexNo
	 * @param vacancyInIndex
	 * @param lessons
	 */
	public void addIndexNumber(String indexNo, int vacancyInIndex, String[] lessons) {
		IndexNumber tempindex = new IndexNumber(indexNo, vacancyInIndex);
		for(int i=0; i<lessons.length; i++) {
			tempindex.addToLessons(lessons[i]);
		}
		MainApp.indexNumberModel.addIndexNumber(tempindex);
	}
	
	/**
	 * Adds new Lesson into the lessonModel 
	 * @param LessonID
	 * @param lessonType
	 * @param day
	 * @param lessonStartTime
	 * @param lessonEndTime
	 * @param venue
	 */
	public void addLessons(String LessonID, LessonType lessonType, Day day, String lessonStartTime, String lessonEndTime, String venue) {
		Lesson templesson = new Lesson(LessonID, lessonType, day, lessonStartTime, lessonEndTime, venue);
		MainApp.lessonModel.addLesson(templesson);
	}
	
	/**
	 * prints all courses in courseModel to console
	 */
	public void printAllCourses() {
		ArrayList<Course> allCourses = MainApp.courseModel.getAllCourses();
		System.out.println("-----All Courses-----");
		for(int i=0; i<allCourses.size(); i++) {
			Course temp = allCourses.get(i);
			System.out.println(temp.getCourseCode() + " " + temp.getSchool());
		}
		System.out.println("---------------------\n");
	}
	
	/**
	 * checks indexnumber vacancy of the indexnumber
	 * @param indexnumber
	 */
	public void checkVacancy(String indexnumber) {
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
	 * prints all students registered to specified indexNumber
	 * @param indexnumber
	 */
	public void printStudentsInIndexNumber(String indexnumber) {
		IndexNumber selectedindexnumber = MainApp.indexNumberModel.getIndexNumber(indexnumber);
		if(selectedindexnumber != null){
			ArrayList<String> studentsinindexnumber = selectedindexnumber.getListOfRegisteredStudents();
			System.out.println("Matric numbers of students in " + indexnumber + ":");
			for(int i=0; i<studentsinindexnumber.size(); i++) {
				System.out.println(studentsinindexnumber.get(i));
			}
			System.out.println(" ");
		}
		else System.out.println("Error: Index number not found. \n");
	}
	
	/**
	 * prints all students in specified Course
	 * @param coursecode
	 */
	public void printStudentsInCourse(String coursecode) {
		Course selectedcourse = MainApp.courseModel.getCourse(coursecode);
		if(selectedcourse != null) {
			ArrayList<String> studentsincourse = selectedcourse.getListOfRegisteredStudents();
			System.out.println("Students in " + coursecode + ":");
			for(int i=0; i<studentsincourse.size(); i++) {
				String matricnum = studentsincourse.get(i);
				Student tempstudent = MainApp.studentModel.getStudent(matricnum);
				if(tempstudent != null) System.out.println(tempstudent.getName() + " " + tempstudent.getGender() + " " + tempstudent.getNationality());
			}
			System.out.println(" ");
		}
		else System.out.println("Error: Course Code not found. \n");
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
	 * checks if course exists in the CourseModel
	 * @param coursecode
	 * @return
	 */
	public boolean hasCourse(String coursecode) {
		return MainApp.courseModel.hasObject(coursecode);
	}
	
	
}
