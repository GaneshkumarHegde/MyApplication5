package com.example.windows.myapplication;

public class Movie {
    private String Title;
    private String About;
    private String Language;
    private int[] screens;
    private int[] genre;
    public String name;
    public String url;
    public int id;
    public Movie(){

    }
    public Movie(String name, String url) {
        this.name = name;
        this.url= url;
    }
    public Movie(String About,String Language,String Title,int id,String url)
    {
        this.About = About;
        this.url = url;
        this.Title = Title;
        this.Language = Language;
        this.id = id;
    }


    public String getTitle() {
        return Title;
    }

    public int getID(){
        return id;
    }


    public String getLanguage() {
        return Language;
    }



    public String getAbout(){

        return About;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }


}