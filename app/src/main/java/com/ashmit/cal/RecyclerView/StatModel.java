package com.ashmit.cal.RecyclerView;


//this class is used for the custom arraylist and this is used as a struct class or model for the data types that will be entered into the arraylist

public class StatModel {
    String xWin , oWin , draw;
    int snoCounter;

    StatModel( int snoCounter, String xWin , String oWin , String draw ){
        this.snoCounter = snoCounter;
        this.xWin = xWin;
        this.oWin = oWin;
        this.draw = draw;
    }

}
