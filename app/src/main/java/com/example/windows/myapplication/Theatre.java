package com.example.windows.myapplication;

public class Theatre {
    public long id;
    public long rate;
    public long showTime;

    Theatre(){

    }
    Theatre(long id,long rate,long showTime){
        this.id = id;
        this.rate = rate;
        this.showTime = showTime;
    }
    public long getID(){
        return id;
    }
    public long getRate(){
        return rate;

    }
    public long getShowTime(){
        return showTime;
    }
}
