package test.UI;

import java.util.Scanner;

import test.FileIO;
import test.MainApp;
import test.AppLogic.StaffUIMngr;
import test.Enums.Day;
import test.Enums.Gender;
import test.Enums.GerType;
import test.Enums.LessonType;
import test.Enums.Mode;
import test.Models.Staff;

/**
 * Boundary Class for Staff User Interface
 * @author kevin
 *
 */
public class StaffUI {
	private String input;
	private Scanner myscanner = new Scanner(System.in);
	private StaffUIMngr staffuimngr = new StaffUIMngr(this.staffUser);
	private boolean inMenu = true;
	private FileIO fileio = new FileIO();
	private Staff staffUser;
	private String d, m, y;
	
	/**
	 * Print main menu for Staff User Interface
	 */
	public void printMainMenu() {
		while(inMenu) {
			System.out.println("STAFF: ADMIN");
			System.out.println("|-------------------------------------------|");
			System.out.println("| Welcome to Stars Planner!                 |");
			System.out.println("| MODE:STAFF                                |");
			System.out.println("|-------------------------------------------|");
			System.out.println("| 1. Change Access Period                   |");
			System.out.println("| 2. Add Student                            |");
			System.out.println("| 3. Add Course                             |");
			System.out.println("| 4. Check Vacancy for index                |");
			System.out.println("| 5. Print student list within indexnumber  |");
			System.out.println("| 6. Print student list within course       |");
			System.out.println("| 7. Print Course Info                      |");
			System.out.println("| 8. Exit                                   |");
			System.out.println("|-------------------------------------------|\n");
			System.out.print("EnterInput: ");
			this.input = myscanner.nextLine();
			switch(input) {
				case "1":
					changeAccessPeriod();
					break;
				case "2":
					addStudent();
					break;
				case "3":
					addCourse();
					break;
				case "4":
					checkVacancy();
					break;
				case "5":
					printStudentsInIndexNumber();
					break;
				case "6":
					printStudentsInCourse();
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
	 * change AccessPeriod for UI
	 * will call staffUIMngr to changeAcessPeriod for specified student
	 */
	public void changeAccessPeriod() {
		String matricNum;
		String accessPeriodStart = null;
		String accessPeriodEnd = null;
		System.out.print("Change Access Period: \nEnter Matric Number of student: ");
		matricNum = myscanner.nextLine();
		
		// Check if the matric number exists
		if(MainApp.studentModel.hasObject(matricNum)) {
			System.out.println("Access Period format: DD/MM/YYYY");
			for (int i = 0; i < 2; i++) {
				if (i == 0)
					System.out.println("Enter NEW Access Period (Start): ");
				else 
					System.out.println("Enter NEW Access Period (End): ");
				System.out.print("Enter Day:");
				d = myscanner.nextLine();
				if (Integer.parseInt(d) < 10)
					d = "0" + d;
				System.out.print("Enter Month:");
				m = myscanner.nextLine();
				if (Integer.parseInt(m) < 10)
					m = "0" + m;
				System.out.print("Enter Year:");
				y = myscanner.nextLine();
				if (i == 0)
					accessPeriodStart = d + "/" + m + "/" + y;
				else
					accessPeriodEnd = d + "/" + m + "/" + y;
			}	
			staffuimngr.changeAccessPeriod(matricNum, accessPeriodStart, accessPeriodEnd);
			System.out.println("Access Period successfully changed! \n");
		}
		else System.out.println("Student does not exist! \n");
	}
	
	/**
	 * Adds Student for UI
	 * calls staffUIMngr to add specified student
	 */
	public void addStudent() {
		String email = null;
		String password = null;
		Gender gender = null;
		String name = null;
		String id = null;
		String nationality;
		String accessPeriodStart = "";
		String accessPeriodEnd = "";
		System.out.println("Enter the following to add student: ");
		System.out.println("Email, Gender, Name, Matric Num, Nationality, Access Period (Start), Access Period (End), Password\n");
		
		
		//Email
		System.out.print("Email: ");
		email = myscanner.nextLine();
		if (!email.contains("@")) {
			System.out.println("Invalid email! \n");
			return;
		}
		
		//Gender
		System.out.print("Gender (MALE/FEMALE): ");
		gender = Gender.valueOf(myscanner.nextLine());
		
		
		//Name
		// if name contains numbers or name is empty, return
		System.out.print("Name (Cannot have digits):");
		name = myscanner.nextLine();
		if (MainApp.containsDigit(name))
			return;
		else 
			//break if name contains digit
			if(MainApp.checkIfEmpty(name)) 
				return;
	
			
		//ID
		System.out.print("Id (Cannot be empty): ");
		id = myscanner.nextLine();
		
		
		if (!MainApp.checkIfEmpty(id)){
			// do this while ID is NOT empty
			if(MainApp.studentModel.hasObject(id)) {
				//if true, means got existing student
				System.out.println("Error! ID already exist. \n");
				staffuimngr.printAllStudents();
			}
			else {
				
				// Nationality
				System.out.print("Nationality (Cannot have digits): ");
				nationality = myscanner.nextLine();
				if (nationality.isEmpty()) {
					System.out.println("Cannot be empty! \n");
					return;
				}
				if (MainApp.containsDigit(nationality))
					return;
				
				
				// Date must be in dd/MM/yyyy format
				System.out.println("Access Period format: DD/MM/YYYY");
				for (int i = 0; i < 2; i++) {
					if (i == 0)
						System.out.println("Enter Access Period (Start): ");
					else 
						System.out.println("Enter Access Period (End): ");
					System.out.print("Enter Day:");
					d = myscanner.nextLine();
					if (Integer.parseInt(d) < 10)
						d = "0" + d;
					System.out.print("Enter Month:");
					m = myscanner.nextLine();
					if (Integer.parseInt(m) < 10)
						m = "0" + m;
					System.out.print("Enter Year:");
					y = myscanner.nextLine();
					if (i == 0)
						accessPeriodStart = d + "/" + m + "/" + y;
					else
						accessPeriodEnd = d + "/" + m + "/" + y;
					
				}	
				
				System.out.print("Password: ");
				password= myscanner.nextLine();

				staffuimngr.addStudent(email, gender, name, id, nationality, accessPeriodStart, accessPeriodEnd);
				staffuimngr.addLoginDetails(id, password, Mode.STUDENT);

				staffuimngr.printAllStudents();
			}
		
		}
		
		
		
	}
	
	/**
	 * add Course on UI
	 * calls staffUIMngr to add a new Course
	 */
	public void addCourse() { 
		// When course is added, you have to add indexnumbers and lessons too
		String school;
		String coursecode;
		int noofaus;
		GerType gertype;
		int vacancyInCourse = 0;
		int numberofindexnumbers;
		//----
		String indexnumber;
		int vacancyInIndex;
		int numberoflessons;
		//----
		int totalnumberoflessons = 0;
		String LessonID;
		LessonType lessonType;
		Day day;
		String lessonStartTime;
		String lessonEndTime;
		String venue;
		String str_NumAU = null;
		String str_NumI = null;		
		
		System.out.println("Enter the following to add course: ");
		System.out.println("School, Course Code, Number of AU, GerType, Number of Indexes\n");
		
		// School (Cannot be empty and cannot contain digit)
		System.out.print("School: ");
		school = myscanner.nextLine();
		if (school.isEmpty()) {
			System.out.println("Cannot be empty! \n");
			return;
		}
		if (MainApp.containsDigit(school))
			return;
		
		// Course Code (Cannot be empty)
		System.out.print("Course Code: ");
		coursecode = myscanner.nextLine();
		if (MainApp.checkIfEmpty(coursecode))
			return;
		if(MainApp.courseModel.hasObject(coursecode)) System.out.println("Sorry, course code already exist, please enter another course code! \n");
		else {
			// Number of AUs (Cannot contain non-digits)
			System.out.print("Number of AU:");
			str_NumAU = myscanner.nextLine();
			// Check if the string contains non-number
			if (MainApp.containsNonDigit(str_NumAU))
				return;
			else
				noofaus = Integer.parseInt(str_NumAU);
			
			// Ger Type (Enum)
			System.out.print("GerType (CORE/BM/LA/STS): ");
			gertype = GerType.valueOf(myscanner.nextLine());

			// Number of indexes (Cannot contain non-digit)
			System.out.print("Number of Indexes: ");
			str_NumI = myscanner.nextLine();
			if (MainApp.containsNonDigit(str_NumI))
				return;
			else
				numberofindexnumbers = Integer.parseInt(str_NumI);
			
			
			
			String[] indexnumbers = new String[numberofindexnumbers];
			for(int i=0; i<numberofindexnumbers; i++) {
				System.out.print("Enter Index Number: ");
				indexnumbers[i] = myscanner.nextLine();
	
				
				System.out.println("-----Enter Index Number " + indexnumbers[i] + " details-----");
				indexnumber = indexnumbers[i];
				
				System.out.print("Enter number of Vacancy in Index:");
				vacancyInIndex = Integer.parseInt(myscanner.nextLine());
				vacancyInCourse += vacancyInIndex;
				System.out.print("Enter number of Lessons in Index:");
				numberoflessons = Integer.parseInt(myscanner.nextLine());
				
				//adding lessons
				String[] lessons = new String[numberoflessons];
				for(int j=0; j<lessons.length; j++) {
					System.out.println("---------Lesson " + (j+1)+ "---------");
					System.out.print("Enter Lesson ID:");
					lessons[j] = myscanner.nextLine();
					LessonID = lessons[j];
					System.out.print("Enter type of Lesson (LECTURE/LAB/TUTORIAL):");
					lessonType = LessonType.valueOf(myscanner.nextLine());
					System.out.print("Day (MONDAY/TUESDAY...):");
					day = Day.valueOf(myscanner.nextLine());
					System.out.print("Lesson Start Time:");
					lessonStartTime = myscanner.nextLine();
					System.out.print("Lesson End Time:");
					lessonEndTime = myscanner.nextLine();
					System.out.print("Enter Venue:");
					venue = myscanner.nextLine();
					staffuimngr.addLessons(LessonID, lessonType, day, lessonStartTime, lessonEndTime, venue);
					System.out.println("---------------------------");
				}
				staffuimngr.addIndexNumber(indexnumber, vacancyInIndex, lessons);
			}
			staffuimngr.addCourse(school, coursecode, noofaus, gertype, vacancyInCourse, indexnumbers);
			staffuimngr.printAllCourses();
		}
		
		
	}

	
	/**
	 * Checks vacancy in UI
	 * calls staffUIMngr to check vacancy 
	 */
	public void checkVacancy() {
		System.out.print("Enter Index Number: ");
		this.input = myscanner.nextLine();
		staffuimngr.checkVacancy(input);
	}
	
	/**
	 * Prints all students in Index Number specified onto console
	 * calls staffUIMngr to print Students
	 */
	public void printStudentsInIndexNumber() {
		System.out.print("Enter Index Number: ");
		this.input = myscanner.nextLine();
		staffuimngr.printStudentsInIndexNumber(input);
	}
	
	/**
	 * print all students in Course specified
	 * calls staffUIMngr to print Students
	 */
	public void printStudentsInCourse() {
		System.out.println("Enter Course Code: ");
		this.input = myscanner.nextLine();
		staffuimngr.printStudentsInCourse(input);
	}
	
	/**
	 * Prints course info based on coursecode
	 * calls staffUIMNGR to print course info
	 */
	private void printCourseInfo() {
		String coursecode;
		System.out.println("Enter course code that you wish to print: \n");
		coursecode = myscanner.nextLine();
		//check if course code is empty
		if(MainApp.checkIfEmpty(coursecode))
			return;
		//check if course code exist
		if(!staffuimngr.hasCourse(coursecode)) {
			System.out.println("Course code does not exist in database. \n");
			return;
		}
		staffuimngr.printCourseInfo(coursecode);
	}
}
