package com.madrzak.mygenericlistingapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Łukasz on 19/11/2017.
 */

public class DatesUtil {

    public static final String LONG_FORMAT = "HH:mm:ss, dd/MM/yy";

    public static String format(Date date) {

        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(LONG_FORMAT);
        return sdf.format(date);

    }

}
