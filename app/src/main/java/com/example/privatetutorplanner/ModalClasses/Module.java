package com.example.privatetutorplanner.ModalClasses;

public class Module {

    private int id;
    private String moduleName;

    public Module(String moduleName) {
        this.moduleName = moduleName;
    }

    public Module(int id, String moduleName) {
        this.id = id;
        this.moduleName = moduleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
