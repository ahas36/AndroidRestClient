package edu.monash.androidrestclient.entity;

/**
 * Created by ahas36 on 11/03/16.
 */
public class Course {

    private Integer courseid;
    private String coursename;

    public Course(Integer courseid, String coursename) {
        this.courseid = courseid;
        this.coursename = coursename;
    }

    public Course() {
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
}

