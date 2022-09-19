package com.example.bakeryrecipe.validation;

import java.time.LocalTime;
import java.util.regex.Pattern;

import static java.lang.Math.abs;

public class Validation {
    public static boolean CheckEmail(String email){
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,4}$");
        if(p.matcher(email).find()){
            return true;
        }
        return false;
    }
    public static int checkTime(LocalTime time){
        int timeResult = LocalTime.now().getMinute() - time.getMinute() ;
        timeResult = abs(timeResult);
        return timeResult;
    }
}
