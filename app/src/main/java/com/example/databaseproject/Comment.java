package com.example.databaseproject;

public class Comment {
    private int id;
    private String name;
    private String description;

    public Comment(String name,String description){
        this.name=name;
        this.description=description;
    }

    public Comment(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
