package test;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;


import test.Models.*;
import test.UI.*;
import test.Enums.*;

public class MainApp {

	private static LoginUI loginUI;
	private static StaffUI staffUI;
	private static StudentUI studentUI;
	public static LessonModel lessonModel = new LessonModel();
	public static IndexNumberModel indexNumberModel = new IndexNumberModel();
	public static CourseModel courseModel = new CourseModel();
	public static StaffModel staffModel = new StaffModel();
	public static StudentModel studentModel = new StudentModel();
	public static PasswordModel passWordModel = new PasswordModel();
	/*
	 * MAIN APPLICATION 
	 */

	public static void main(String[] args) throws ParseException {
		while(true) {
			loginUI = new LoginUI();
			loginUI.printLogin();

	        
			if(loginUI.checkIfInputIsInDatabase(loginUI.getUserName(), loginUI.getPassword())) {
				if(loginUI.returnMode(loginUI.getUserName())== Mode.STAFF) {
					System.out.println("Login successfully! \n");
					staffUI = new StaffUI();
					staffUI.printMainMenu();
				}
				else {
					String matricnum = loginUI.getUserName();
					if (checkAccessPeriod(matricnum)) {
						System.out.println("Login successfully! \n");
						studentUI = new StudentUI(matricnum);
						studentUI.printMainMenu();
					}		
				}
			}
			else System.out.println("Login unsuccessful. \nIncorrect Username/Password, please try again. \n");
		}
	}
	
	/**
	 * check if Student's Stars Access Period is currentTime
	 * @param matricnum
	 * @return
	 * @throws ParseException
	 */
	public static boolean checkAccessPeriod(String matricnum) throws ParseException{

		Student selectedStudent = MainApp.studentModel.getStudent(matricnum);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = sdf.parse(selectedStudent.getAccessPeriodStart());
		Date endDate = sdf.parse(selectedStudent.getAccessPeriodEnd());
        
        // gives you the current DateTime
        Date curDate = Calendar.getInstance().getTime();
        
        // shows the start and end dates of access period
        System.out.println("Access Period Start Date : " + sdf.format(startDate));
        System.out.println("Access Period End Date : " + sdf.format(endDate));

        if (curDate.compareTo(endDate) > 0) {
            System.out.println("Access period is over\n");
            return false;
        } else if (curDate.compareTo(endDate) <= 0 && curDate.compareTo(startDate) >= 0) {
        	// Currently in access period
            return true;
        } else if (curDate.compareTo(startDate) < 0) {
            System.out.println("Access period has not opened\n");
            return false;
        } else
        	return false;
        
	}
	
	/**
	 * Checks whether there is a digit in String
	 * @param s
	 * @return
	 */
	public static final boolean containsDigit(String s) {
		boolean containsDigit = false;
		
		if (s != null && !s.isEmpty()) {
			for (char c :s.toCharArray()) {
				if (containsDigit = Character.isDigit(c)) {
					System.out.println("Invalid input! \n");
					System.out.println("");
					break;
				}
			}
		}
		
		return containsDigit;
	}
	
	/**
	 * Checks if the String contains non-digits
	 * @param s
	 * @return
	 */
	public static final boolean containsNonDigit(String s) {
		boolean containsNonDigit = false;
		if (s != null && !s.isEmpty()) {
			for (char c :s.toCharArray()) {
				if (containsNonDigit = !Character.isDigit(c)) {
					System.out.println("Invalid input! \n");
					System.out.println("");
					break;
				}
			}
		}
		return containsNonDigit;
	}
	
	/**
	 * Check if the String contains only whitespace
	 * @param b
	 * @return
	 */
	public static boolean checkIfEmpty(String b) {
		if (b.isEmpty() || b.equals(" ")) {
			System.out.println("Empty/invalid input! \n");
			return true;
		}
		else
			return false;
			
	}
}
