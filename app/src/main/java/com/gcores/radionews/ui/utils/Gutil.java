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


    //转换时分秒
    public static String convertToSencondAudio(int seconds){
        String secStr;
        String minStr;
        int sec = seconds % 60;
        int min = (seconds/ 60)%60;
        if (min<10){
            minStr = "0"+min;
        }else{
            minStr = min+"";
        }
        if (sec<10){
            secStr = "0"+sec;
        }else{
            secStr = sec+"";
        }

     /*   if (min<10){

            return "0"+min + ":" + sec+"";
        }*/
//        int hours = (seconds/60)/60;
        return  minStr + ":" + secStr+"";
    }

}
