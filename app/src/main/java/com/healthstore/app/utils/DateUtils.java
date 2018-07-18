package com.healthstore.app.utils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd+hh:mm:ss");
    public static final SimpleDateFormat sdfDateCn = new SimpleDateFormat("yyyy年MM月dd日");

    public enum DayOfWeek {
        Sunday("星期日", Calendar.SUNDAY),
        Monday("星期一", Calendar.MONDAY),
        Tuesday("星期二", Calendar.TUESDAY),
        Wednesday("星期三", Calendar.WEDNESDAY),
        Thursday("星期四", Calendar.THURSDAY),
        Friday("星期五", Calendar.FRIDAY),
        Saturday("星期六", Calendar.SATURDAY);

        String descCn;
        int val;

        DayOfWeek(String descCn, int val) {
            this.descCn = descCn;
            this.val = val;
        }

        public String getDescCn(){
            return descCn;
        }

        public static DayOfWeek of(int val) {
            return Arrays.stream(DayOfWeek.values()).filter(dayOfWeek -> dayOfWeek.val==val).findFirst().orElse(null);
        }

    }

    public static String formatNow() {
        return sdfDateTime.format(new Date());
    }

    public static String formatDate(Date date, SimpleDateFormat sdf) {
        return sdf.format(date);
    }

    public static DayOfWeek getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return DayOfWeek.of(dayOfWeek);
    }

}
