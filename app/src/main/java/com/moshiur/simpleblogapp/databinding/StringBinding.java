package com.moshiur.simpleblogapp.databinding;

import java.util.List;

public class StringBinding {
    public static String categories(List<String> categories){
        String cat = "Categories: ";
        for(int i =0; i<categories.size(); i++){
            cat += categories.get(i);
            if(i != categories.size()-1){
                cat += ", ";
            }
        }
        return cat;
    }
}
