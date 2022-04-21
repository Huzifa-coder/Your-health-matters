package com.barmej.wecare.utiles;

import android.text.format.DateFormat;

import com.barmej.wecare.viewModels.MainViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utiles {

    public static String toString(long date){
        return (String) DateFormat.format("MM/dd/yyyy", new Date(date));
    }
}
