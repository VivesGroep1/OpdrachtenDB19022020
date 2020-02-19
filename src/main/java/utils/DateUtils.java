package main.java.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date dateFromYMD(int jaar, int maand, int dag) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, jaar);
        cal.set(Calendar.MONTH, maand-1);
        cal.set(Calendar.DAY_OF_MONTH, dag);
        return cal.getTime();
    }

}
