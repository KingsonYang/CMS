package com.cms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static Date date = new Date();

    public static String getCurrTime(){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    public static String getCurrTime(Date date){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    public static String getDateEight(){
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        return sd.format(date);
    }

    public static String getDateTen(){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        return sd.format(date);
    }

    public static String getTimeEight(){
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        return sd.format(date);
    }


}
