package test.Models;

import java.util.ArrayList;
import java.util.List;

import test.FileIO;
import test.Enums.Day;
import test.Enums.LessonType;
/**
 * LessonModel Class is an Entity class containing many Lesson
 * @author kevin
 *
 */
public class LessonModel implements Model{
	private FileIO fileIO;
	private ArrayList<Lesson> allLesson =new ArrayList<Lesson>();
	public LessonModel() {
		populate();
	}
	/**
	 * get all lessons in LessonModel
	 * @return
	 */
	public ArrayList<Lesson> getAllLessons(){
		return allLesson;
	}

	/**
	 * populate the LessonModel with the Lesson.txt file. Lesson.txt file contains the database for Lessons in a .txt file.
	 */
	public void populate() {
		fileIO = new FileIO();
		List<String> result = fileIO.readData("TextDatabase/Lesson.txt");
		for(int i=1; i<result.size(); i++) {
	    	 String[] extractData = result.get(i).split("_");
	    	 Lesson tempLesson = new Lesson(extractData[0], LessonType.valueOf(extractData[1]), Day.valueOf(extractData[2]), extractData[3], extractData[4], extractData[5]);
	    	 allLesson.add(tempLesson);
	     }
	}
	/**
	 * add Lesson to LessonModel
	 * @param lesson
	 */
	public void addLesson(Lesson lesson) {
		allLesson.add(lesson);
	}
	
	/**
	 * get Lesson from lesson id
	 * @param lessonId
	 * @return
	 */
	public Lesson getLesson(String lessonId) {
		for(int i=0; i<allLesson.size(); i++) {
			if(allLesson.get(i).getLessonID().equals(lessonId)) return allLesson.get(i);
		}
		return null;
	}
}
