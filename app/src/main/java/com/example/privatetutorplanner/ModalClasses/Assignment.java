package com.example.privatetutorplanner.ModalClasses;

public class Assignment {

    private String title ;
    private String modulename ;
    private int qu ;
    private int mark ;
    private String date ;

    public Assignment(String title, String modulename, int qu, int mark, String date) {
        this.title = title;
        this.modulename = modulename;
        this.qu = qu;
        this.mark = mark;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getModulename() {
        return modulename;
    }

    public int getQu() {
        return qu;
    }

    public int getMark() {
        return mark;
    }

    public String getDate() {
        return date;
    }
}
