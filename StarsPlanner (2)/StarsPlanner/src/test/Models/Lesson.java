package test.Models;

import test.Enums.Day;
import test.Enums.LessonType;
/**
 * Entity Class for Lessons
 * @author kevin
 *
 */
public class Lesson {
	private String LessonID;
	private LessonType lessonType;
	private Day day;
	private String lessonStartTime;
	private String lessonEndTime;
	private String venue;
	
	/**
	 * constructor of Lesson
	 * @param LessonID
	 * @param lessonType
	 * @param day
	 * @param lessonStartTime
	 * @param lessonEndTime
	 * @param venue
	 */
	public Lesson(String LessonID, LessonType lessonType, Day day, String lessonStartTime, String lessonEndTime, String venue){
		this.setLessonID(LessonID);
		this.lessonType = lessonType;
		this.setDay(day);
		this.setLessonStartTime(lessonStartTime);
		this.setLessonEndTime(lessonEndTime);
		this.setVenue(venue);
	}
	/**
	 * get LessonType of Lesson
	 * @return
	 */
	public LessonType getLessonType() {
		return lessonType;
	}
	/**
	 * get lesson id of Lesson
	 * @return
	 */
	public String getLessonID() {
		return LessonID;
	}
	/**
	 * get day of lesson
	 * @return
	 */
	public Day getDay() {
		return day;
	}
	/**
	 * get lesson start time of lesson
	 * @return
	 */
	public String getLessonStartTime() {
		return lessonStartTime;
	}
	/**
	 * get venue of lesson
	 * @return
	 */
	public String getVenue() {
		return venue;
	}
	
	/**
	 * set lesson id for lesson
	 * @param lessonID
	 */
	public void setLessonID(String lessonID) {
		LessonID = lessonID;
	}
	
	/**
	 * set day of lesson
	 * @param day
	 */
	public void setDay(Day day) {
		this.day = day;
	}
	
	/**
	 * set start time of lesson
	 * @param lessonStartTime
	 */
	public void setLessonStartTime(String lessonStartTime) {
		this.lessonStartTime = lessonStartTime;
	}
	
	/**
	 * get lesson end time
	 * @return
	 */
	public String getLessonEndTime() {
		return lessonEndTime;
	}
	
	/**
	 * set lesson end time
	 * @param lessonEndTime
	 */
	public void setLessonEndTime(String lessonEndTime) {
		this.lessonEndTime = lessonEndTime;
	}
	
	/**
	 * set lesson venue
	 * @param venue
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
}
