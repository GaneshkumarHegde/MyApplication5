package com.example.windows.myapplication;

public class Movie {
    public String title;
    public String about;
    public String language;
    public String[] screens;
    public String[] genre;
    public Movie(String title,String about,String language,String[] screens,String[] genre)
    {
        this.about = about;
        this.genre = genre;
        this.title = title;
        this.language = language;
        this.screens = screens;
    }


    public String getTitle() {
        return title;
    }



    public String getLanguage() {
        return language;
    }



    public String getAbout(){

        return about;
    }

    public String[] getScreens(){
        return screens;
    }

    public String[] getGenre(){
        return genre;
    }


}