package com.healthstore.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd+hh:mm:ss");

    public static String formatNow(){
        return sdfDateTime.format(new Date());
    }

}
