package com.example.projecttest2.Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Course {
    private String title;
    private String mainTopics;
    private String prerequisites;
    private String daysOfWeek;
    private String timeOfDay;
    private String Is_Available;
    private String Available_Until;
    private Integer numberOfStudents;
    private Integer courseNumber;

    public Course(String title, String mainTopics, String prerequisites, String daysOfWeek, String timeOfDay, String is_Available, String available_Until) {
        this.title = title;
        this.mainTopics = mainTopics;
        this.prerequisites = prerequisites;
        this.daysOfWeek = daysOfWeek;
        this.timeOfDay = timeOfDay;
        Is_Available = is_Available;
        Available_Until = available_Until;
    }

    public Course(String title, String mainTopics, String prerequisites, String daysOfWeek, String timeOfDay, String is_Available, String available_Until, Integer courseNumber) {
        this.title = title;
        this.mainTopics = mainTopics;
        this.prerequisites = prerequisites;
        this.daysOfWeek = daysOfWeek;
        this.timeOfDay = timeOfDay;
        Is_Available = is_Available;
        Available_Until = available_Until;
        this.courseNumber = courseNumber;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getIs_Available() {
        return Is_Available;
    }

    public void setIs_Available(String is_Available) {
        Is_Available = is_Available;
    }

    public String getAvailable_Until() {
        return Available_Until;
    }

    public void setAvailable_Until(String available_Until) {
        Available_Until = available_Until;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainTopics() {
        return mainTopics;
    }

    public void setMainTopics(String mainTopics) {
        this.mainTopics = mainTopics;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public boolean isCourseAvailable() {
        if(getAvailable_Until()==null)
            return false;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(getAvailable_Until(), dateFormatter);
        return getIs_Available().equals("true") && localDate.isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        String numberOfStudentStr =  (numberOfStudents != null) ? ", Number Of students='" + numberOfStudents  + '\'' : "";
        return "Course{" +
                "title='" + title + '\'' +
                ", mainTopics='" + mainTopics + '\'' +
                ", prerequisites='" + prerequisites + '\'' +
                ", daysOfWeek='" + daysOfWeek + '\'' +
                ", timeOfDay='" + timeOfDay + '\'' +
                ", Is_Available='" + Is_Available + '\'' +
                ", Available_Until='" + Available_Until + '\'' +
                numberOfStudentStr +
                ", courseNumber=" + courseNumber +
                '}';
    }
}
