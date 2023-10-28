package com.example.lab5_sql;

public class Subject {
    private long id; // Unique subject ID
    private String name;

    public Subject(long id, String name, String studyHours, String teacher, String schedule, String successRate) {
        this.id = id;
        this.name = name;
        this.studyHours = studyHours;
        this.teacher = teacher;
        this.schedule = schedule;
        this.successRate = successRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudyHours() {
        return studyHours;
    }

    public void setStudyHours(String studyHours) {
        this.studyHours = studyHours;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    private String studyHours;
    private String teacher;
    private String schedule;
    private String successRate;

    // Constructors, getters, setters, and other methods go here
}
