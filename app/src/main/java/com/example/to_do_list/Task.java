package com.example.to_do_list;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    //Any task will have a certain ID, Date, Tag, Course, and Title Associated with it.
    //ID is an int, Date, Tag, Course and Title will be a String.
    private int id;
    private String tag;
    private String course;
    private String title;
    private Date date;

    public Task(){
        long current = System.currentTimeMillis();

        this.tag = "";
        this.course = "";
        this.title = "";
        this.date = new java.sql.Date(current);
    }

    public Task(Date date, String tag, String title, String course){

        this.tag = tag;
        this.title = title;
        this.date = date;
        this.course = course;
    }

    public void changeDate(Task task, Date newDate){
        task.date = newDate;
    }

    public Date getDate(Task task){
        return task.date;
    }

    public String getTag(Task task){
        return task.tag;
    }

    public String getCourse(Task task){
        return task.course;
    }

    public String getTitle(Task task){
        return task.title;
    }











}
