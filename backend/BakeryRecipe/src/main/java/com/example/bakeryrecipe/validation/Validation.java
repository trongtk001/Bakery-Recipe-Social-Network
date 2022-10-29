package com.example.bakeryrecipe.validation;

import java.util.regex.Pattern;

public class Validation {
    public static boolean CheckEmail(String email){
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,4}$");
        if(p.matcher(email).find()){
            return true;
        }
        return false;
    }
}
