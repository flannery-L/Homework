package com.example.chapter3.homework;

public class TestData {
    String name;
    int imageID;
    String content;
    String time;
    public TestData(String name, int imageID,String content,String time)
    {
        this.name=name;
        this.imageID=imageID;
        this.content=content;
        this.time=time;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getImageID(){
        return imageID;
    }
    public void setImageID(int imageID){
        this.imageID=imageID;
    }
}
