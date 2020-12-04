package test;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import test.Models.Course;
import test.Models.CourseModel;
import test.Models.IndexNumber;
import test.Models.IndexNumberModel;
import test.Models.Lesson;
import test.Models.LessonModel;
import test.Models.PasswordModel;
import test.Models.Staff;
import test.Models.StaffModel;
import test.Models.Student;
import test.Models.StudentModel;



/**
 * 
 * @author kevin
 *
 */
public class FileIO {
	/**
	 * reads data from filename.txt file and converts into a List<String>
	 * @param filename
	 * @return
	 */
	public List<String> readData(String filename) { 
		//returns a list of every line in text file
		List<String> result = new ArrayList<String>();
		try(Stream<String> stream = Files.lines(Paths.get(filename))) {
		     result = stream.collect(Collectors.toList());
	     }
		catch(IOException e) {
	        e.printStackTrace();
   		}
		return result;
	}
	
	/**
	 * writes data from courseModel to Course.txt file
	 * @param courseModel
	 */
	public void writeData(CourseModel courseModel) {
		try {
		FileWriter myWriter = new FileWriter("TextDatabase/Course.txt");
		myWriter.append("school_coursecode_noofaus_gertype_vacancyincourse_numberofregisteredstudents_"
				+ "matricnumofregisteredstudents_numberofindexnumbers_indexnumbers\n");
		ArrayList<Course> allCourses = courseModel.getAllCourses();
		for(int i=0; i<allCourses.size(); i++) {
			myWriter.append(allCourses.get(i).getSchool() + "_");
			myWriter.append(allCourses.get(i).getCourseCode() + "_");
			myWriter.append(allCourses.get(i).getNoOfAUS() + "_");
			myWriter.append(allCourses.get(i).getGertype() + "_");
			myWriter.append(allCourses.get(i).getVacancyInCourse() + "_");
			myWriter.append(allCourses.get(i).getListOfRegisteredStudents().size() + "_");
			for(int j=0; j<allCourses.get(i).getListOfRegisteredStudents().size(); j++) {
				myWriter.append(allCourses.get(i).getListOfRegisteredStudents().get(j) + "_");
			}
			myWriter.append(allCourses.get(i).getListOfIndexNumbers().size() + "_");
			for(int j=0; j<allCourses.get(i).getListOfIndexNumbers().size(); j++) {
				myWriter.append(allCourses.get(i).getListOfIndexNumbers().get(j));
				if(j!= allCourses.get(i).getListOfIndexNumbers().size()-1) myWriter.append("_");
			}
			myWriter.append('\n');
		}
		myWriter.close();
	}
	catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	}
	}
	
	/**
	 * writes data from IndexNumberModel to IndexModel.txt file
	 * @param indexNumberModel
	 */
	public void writeData(IndexNumberModel indexNumberModel) {
		try {
			FileWriter myWriter = new FileWriter("TextDatabase/IndexNumber.txt");
			myWriter.append("indexNo_vacancyInIndex_numberofregisteredstudents_matricnumofregisteredstudents_numberofpplinwaitlist_"
					+ "matricnumofpplinwaitlist_numberoflessons_LessonID\n");
			ArrayList<IndexNumber> allIndexNumbers = indexNumberModel.getAllIndexNumbers();
			for(int i=0; i<allIndexNumbers.size(); i++) {
				myWriter.append(allIndexNumbers.get(i).getIndexNo() + "_");
				myWriter.append(allIndexNumbers.get(i).getVacancyInIndex() + "_");
				myWriter.append(allIndexNumbers.get(i).getListOfRegisteredStudents().size() + "_");
				for(int j=0; j<allIndexNumbers.get(i).getListOfRegisteredStudents().size(); j++) {
					myWriter.append(allIndexNumbers.get(i).getListOfRegisteredStudents().get(j) + "_");
				}
				myWriter.append(allIndexNumbers.get(i).getWaitList().size() + "_");
		        for (String item: allIndexNumbers.get(i).getWaitList()) {
		        	myWriter.append(item + "_");
		        }
		        myWriter.append(allIndexNumbers.get(i).getLessons().size() + "_");
		        for(int j=0; j<allIndexNumbers.get(i).getLessons().size(); j++) {
					myWriter.append(allIndexNumbers.get(i).getLessons().get(j));
					if(j!= allIndexNumbers.get(i).getLessons().size()-1) myWriter.append("_");
				}
				myWriter.append('\n');
			}
			myWriter.close();
		}
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		}
	
	/**
	 * writes data from LessonModel to Lesson.txt file
	 * @param lessonModel
	 */
	public void writeData(LessonModel lessonModel) {
		try {
			FileWriter myWriter = new FileWriter("TextDatabase/Lesson.txt");
			myWriter.append("LessonID_lessonType_day_lessonStartTime_lessonEndTime_venue\n");
			ArrayList<Lesson> allLessons = lessonModel.getAllLessons();
			for(int i=0; i<allLessons.size(); i++) {
				myWriter.append(allLessons.get(i).getLessonID() + "_");
				myWriter.append(allLessons.get(i).getLessonType() + "_");
				myWriter.append(allLessons.get(i).getDay() + "_");
				myWriter.append(allLessons.get(i).getLessonStartTime() + "_");
				myWriter.append(allLessons.get(i).getLessonEndTime() + "_");
				myWriter.append(allLessons.get(i).getVenue());
				myWriter.append('\n');
			}
			myWriter.close();
		}
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		}
	
	/**
	 * writes data from StaffModel to Staff.txt file
	 * @param staffModel
	 */
	public void writeData(StaffModel staffModel) {
		try {
			FileWriter myWriter = new FileWriter("TextDatabase/Staff.txt");
			myWriter.append("gender_name_id_nationality\n");
			ArrayList<Staff> allStaff = staffModel.getAllStaff();
			for(int i=0; i<allStaff.size(); i++) {
				myWriter.append(allStaff.get(i).getGender() + "_");
				myWriter.append(allStaff.get(i).getName() + "_");
				myWriter.append(allStaff.get(i).getId() + "_");
				myWriter.append(allStaff.get(i).getNationality());
				myWriter.append('\n');
			}
			myWriter.close();
			
		}
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		}

	/**
	 * writes data from StudentModel to Student.txt file
	 * @param studentModel
	 */
	public void writeData(StudentModel studentModel) {
		try {
			FileWriter myWriter = new FileWriter("TextDatabase/Student.txt");
			myWriter.append("email_gender_name_id_nationality_accessPeriodStart_accessPeriodEnd_numberofregisteredcourses_coursecode_index_status\n");
			ArrayList<Student> allStudent = studentModel.getAllStudents();
			for(int i=0; i<allStudent.size(); i++) {
				myWriter.append(allStudent.get(i).getEmail() + "_");
				myWriter.append(allStudent.get(i).getGender() + "_");
				myWriter.append(allStudent.get(i).getName() + "_");
				myWriter.append(allStudent.get(i).getId() + "_");
				myWriter.append(allStudent.get(i).getNationality() + "_");
				myWriter.append(allStudent.get(i).getAccessPeriodStart() + "_");
				myWriter.append(allStudent.get(i).getAccessPeriodEnd() + "_");
				if(allStudent.get(i).getListOfRegisteredCourses().size() > 0) {
					myWriter.append(allStudent.get(i).getListOfRegisteredCourses().size() + "_");
					for(int j=0; j<allStudent.get(i).getListOfRegisteredCourses().size(); j++) {
						myWriter.append(allStudent.get(i).getListOfRegisteredCourses().get(j) + "_");
						myWriter.append(allStudent.get(i).getListOfRegisteredIndexes().get(j) + "_");
						myWriter.append(allStudent.get(i).getListOfCourseStatus().get(j) + "");
						if(j!= allStudent.get(i).getListOfRegisteredCourses().size()-1) myWriter.append("_");
					}
				}
				else myWriter.append(allStudent.get(i).getListOfRegisteredCourses().size() + "");
				myWriter.append('\n');
			}
			myWriter.close();
		}
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		}
	/**
	 * writes data from PassWordModel to EncryptedPassword.txt file
	 * @param passWordModel
	 */
	public void writeData(PasswordModel passWordModel) {
		try {
			FileWriter myWriter = new FileWriter("TextDatabase/EncryptedPassword.txt");
			myWriter.append("MatricNUM_password_mode\n");
			HashMap<String, List<String>> loginDetails = passWordModel.getLoginDetails();
			for (String key : loginDetails.keySet()) {
				myWriter.append(key + "_");
				myWriter.append(loginDetails.get(key).get(0) + "_");
				myWriter.append(loginDetails.get(key).get(1));
				myWriter.append('\n');
			}
			myWriter.close();
			
		}
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
}
