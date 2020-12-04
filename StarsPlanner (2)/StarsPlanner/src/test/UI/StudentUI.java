package test.UI;

import java.text.ParseException;
import java.util.Scanner;

import test.FileIO;
import test.MainApp;
import test.AppLogic.StudentUIMngr;
import test.Models.Student;
/**
 * Boundary Class for Student User Interface
 * @author kevin
 *
 */
public class StudentUI {
	private String input;
	private Scanner myscanner = new Scanner(System.in);
	private Student studentUser;
	private StudentUIMngr studentuimngr;
	private String matricNum;
	private FileIO fileio = new FileIO();
	private boolean inMenu = true;
	/**
	 * Constructor for Student User Interface
	 * @param matricnum
	 */
	public StudentUI(String matricnum){
		//get student from matricnumber
		this.studentUser = MainApp.studentModel.getStudent(matricnum);
		//this.studentUser= studentuimngr.getStudent(matricnum);
		this.matricNum = matricnum;
		//pass student user into studentUImngr
		studentuimngr =  new StudentUIMngr(this.studentUser);
	}
	/**
	 * prints Main Menu for Student User Interface
	 * @throws ParseException
	 */
	public void printMainMenu() throws ParseException {
		while(inMenu) {
			System.out.println("Student: " + studentUser.getId());
			System.out.println("|-------------------------------------------|");
			System.out.println("| Welcome to Stars Planner!                 |");
			System.out.println("| MODE:STUDENT                              |");
			System.out.println("|-------------------------------------------|");
			System.out.println("| 1. Register Course                        |");
			System.out.println("| 2. Drop Course                            |");
			System.out.println("| 3. Print Registered Courses               |");
			System.out.println("| 4. Check Vacancies Available              |");
			System.out.println("| 5. Change Index Number of Course          |");
			System.out.println("| 6. Swap Index Number with Another Student |");
			System.out.println("| 7. Print Course Info                      |");
			System.out.println("| 8. Exit                                   |");
			System.out.println("|-------------------------------------------|\n");
			System.out.print("EnterInput: ");
			this.input = myscanner.nextLine();
			switch(input) {
				case "1":
					registerCourse();
					break;
				case "2":
					dropCourse();
					break;
				case "3":
					printCourseRegistered();
					break;
				case "4":
					checkVacanciesAvailable();
					break;
				case "5":
					changeIndexOfCourse();
					break;
				case "6":
					swapIndex();
					break;
				case "7":
					printCourseInfo();
					break;
				case "8":
					inMenu = false;
					fileio.writeData(MainApp.courseModel);
					fileio.writeData(MainApp.lessonModel);
					fileio.writeData(MainApp.indexNumberModel);
					fileio.writeData(MainApp.studentModel);
					fileio.writeData(MainApp.staffModel);
					fileio.writeData(MainApp.passWordModel);
					//System.out.println("Text files updated successfully");
					break;
				default:
					System.out.println("Wrong input! Please try an input from 1 to 8! \n");
			}
		}
	}
	
	
	/**
	 * register Course in UI for Student User Interface
	 * checks for bad inputs
	 * @throws ParseException
	 */
	public void registerCourse() throws ParseException { 
		String choice;
		String indexnumber;
		System.out.print("Register Course: Enter Index Number \n");
		indexnumber = myscanner.nextLine();
		
		// Index number cannot be empty or containing any non-integer value
		if (MainApp.checkIfEmpty(indexnumber) || MainApp.containsNonDigit(indexnumber))
			return;
		
		
		if(!studentuimngr.hasIndex(indexnumber)) System.out.println("Sorry! This Index Number is not found! \n");
		else {
			String coursecode = studentuimngr.returnCourseInIndexNo(indexnumber);
			if(studentuimngr.getCourse(coursecode).getListOfRegisteredStudents().contains(matricNum))
				System.out.println("Unable to register: Course already registered or in waitlist. \n");
			else {
				if(studentuimngr.checkIfClash(indexnumber)){
					System.out.println("Unable to register: There are clashes in timeslots. \n");
					return;
				}
				if(!studentuimngr.checkVacancy(indexnumber)) {
					System.out.println("This index has no available vacancies \n"
									+ "1. Continue to register (will be placed in waitlist)\n"
									+ "2. Back to Main Menu");
					System.out.print("Enter input:");
					choice = myscanner.nextLine();
					if(choice.equals("1")) studentuimngr.addCourseToWaitlist(indexnumber);
					else return;
				}
				else {
					System.out.println("This index has available vacancies! \n"
							+ "1. Register course \n"
							+ "2. Back to Main Menu");
					System.out.print("Enter input:");
					choice = myscanner.nextLine();
					if(choice.equals("1")) studentuimngr.addCourse(indexnumber);
					else return;
					
					//write to database
					fileio.writeData(MainApp.courseModel);
					fileio.writeData(MainApp.lessonModel);
					fileio.writeData(MainApp.indexNumberModel);
					fileio.writeData(MainApp.studentModel);
					fileio.writeData(MainApp.staffModel);
					fileio.writeData(MainApp.passWordModel);
				}
			}
		}
		
	}
	
	/**
	 * drop course in UI for Student User Interface
	 * checks for bad input
	 */
	public void dropCourse() {
		String input;
		printCourseRegistered();
		System.out.println(" ");
		System.out.print("Drop course: Enter the Course code \n");
		input = myscanner.nextLine();
		// Check if student has that course
		Student mystudent =  studentuimngr.getStudent(matricNum);
		
		boolean studentHasCourse = mystudent.getListOfRegisteredCourses().contains(input);
		if(!studentHasCourse) 
			System.out.println("Invalid Course Code entered \n");
		else {
			studentuimngr.dropCourse(input);
			System.out.println("Course successfully dropped. \n");
			//write to database
			fileio.writeData(MainApp.courseModel);
			fileio.writeData(MainApp.lessonModel);
			fileio.writeData(MainApp.indexNumberModel);
			fileio.writeData(MainApp.studentModel);
			fileio.writeData(MainApp.staffModel);
			fileio.writeData(MainApp.passWordModel);
		}
	}
	
	
	
	/**
	 * prints all Courses registered by student in UI for Student User Interface
	 */
	public void printCourseRegistered() {
		studentuimngr.printCourseRegistered();
	}
	
	/**
	 * check vacancies available in UI for Student User Interface
	 * calls studentUIMngr to check vacancies available
	 */
	public void checkVacanciesAvailable() {
		String indexnumber;
		System.out.print("Index number of course you wish to check vacancies for:");
		indexnumber = myscanner.nextLine();
		studentuimngr.checkVacanciesAvailable(indexnumber);
	}
	
	/**
	 * change Index of Course specified in UI for Student User Interface
	 * calls studentUIMngr to change Index of Course specified
	 */
	public void changeIndexOfCourse() {
		String currentindex;
		String newindex;
		// Need to checks if current index is registered under his name
		System.out.print("Enter CURRENT Index Number:");
		currentindex = myscanner.nextLine();
		// Need to check if new index have vacancies
		System.out.print("Enter NEW Index Number:");
		newindex = myscanner.nextLine();
		// +1 to vacancy for current index, -1 for new index
		// Changing of index
		studentuimngr.changeIndexOfCourse(currentindex, newindex);
	
		//write to database
		fileio.writeData(MainApp.courseModel);
		fileio.writeData(MainApp.lessonModel);
		fileio.writeData(MainApp.indexNumberModel);
		fileio.writeData(MainApp.studentModel);
		fileio.writeData(MainApp.staffModel);
		fileio.writeData(MainApp.passWordModel);
	}
	
	/**
	 * Swaps Index in UI for Student User Interface
	 * calls studentUIMngr to swap Index with Peer
	 */
	public void swapIndex() {
		String yourindex;
		String peer_username;
		String peer_password;
		String peer_index;
		// Check if index number is registered under student
		System.out.print("Enter your Index Number: \n");
		yourindex = myscanner.nextLine();
		if (MainApp.checkIfEmpty(yourindex) || MainApp.containsNonDigit(yourindex))
			return;
		// need to check if peer's username exist
		System.out.print("Enter Peer's username: \n");
		peer_username = myscanner.nextLine();
		// need to check if the password is correct (must link to the username)
		System.out.print("Enter Peer's password: \n");
		peer_password = myscanner.nextLine();
		// need to check if the peer has that index registered
		System.out.print("Peer's index: \n");
		peer_index = myscanner.nextLine();
		if (MainApp.checkIfEmpty(yourindex) || MainApp.containsNonDigit(peer_index))
			return;
		// Swapping of index with peer
		studentuimngr.swapIndex(yourindex, peer_username, peer_password, peer_index);
		
		//write to database
		fileio.writeData(MainApp.courseModel);
		fileio.writeData(MainApp.lessonModel);
		fileio.writeData(MainApp.indexNumberModel);
		fileio.writeData(MainApp.studentModel);
		fileio.writeData(MainApp.staffModel);
		fileio.writeData(MainApp.passWordModel);
	}
	
	/**
	 * Prints course info based on coursecode
	 * calls studentUIMngr to print course info
	 */
	public void printCourseInfo() {
		String coursecode;
		System.out.println("Enter course code that you wish to print: \n");
		coursecode = myscanner.nextLine();
		//check if course code is empty
		if(MainApp.checkIfEmpty(coursecode))
			return;
		//check if course code exist
		if(!studentuimngr.hasCourse(coursecode)) {
			System.out.println("Course code does not exist in database. \n");
			return;
		}
		studentuimngr.printCourseInfo(coursecode);
	}
	
}
