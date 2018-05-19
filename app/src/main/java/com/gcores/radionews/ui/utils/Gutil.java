package com.gcores.radionews.ui.utils;

public class Gutil {

    //转换时分秒
    public static String convertToSencond(int seconds){
        int sec = seconds % 60;
        int min = (seconds/ 60)%60;
        int hours = (seconds/60)/60;
        if (hours>0){
            return  hours +":"+ min + "\'" + sec+"\"";
        }
        return  min + "\'" + sec+"\"";
    }

}
