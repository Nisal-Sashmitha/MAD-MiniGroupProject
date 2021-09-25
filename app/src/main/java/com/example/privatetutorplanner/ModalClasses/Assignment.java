package com.example.privatetutorplanner.ModalClasses;

public class Assignment {

    private int id ;
    private String title ;
    private String modulename ;
    private int qu ;
    private int mark ;
    private String date ;

    public Assignment(int id, String title, String modulename, int qu, int mark, String date) {
        this.id = id;
        this.title = title;
        this.modulename = modulename;
        this.qu = qu;
        this.mark = mark;
        this.date = date;
    }



    public Assignment(String title, String modulename, int qu, int mark, String date) {
        this.title = title;
        this.modulename = modulename;
        this.qu = qu;
        this.mark = mark;
        this.date = date;
    }

    public int getId() {
        return id;
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
