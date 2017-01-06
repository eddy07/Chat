package com.dev.app.chat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil {

    public static String getHour(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.FRENCH);
        return sdf.format(date);
    }

}