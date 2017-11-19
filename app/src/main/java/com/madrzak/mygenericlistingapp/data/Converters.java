package com.madrzak.mygenericlistingapp.data;

import android.arch.persistence.room.TypeConverter;

import com.madrzak.mygenericlistingapp.util.GsonFactory;

import java.util.Date;

/**
 * Created by Łukasz on 19/11/2017.
 */

public class Converters {

    @TypeConverter
    public static Date fromDate(String dateObject) {
        return dateObject == null ? null : GsonFactory.get().fromJson(dateObject, Date.class);
    }

    @TypeConverter
    public static String toJsonDate(Date date) {
        return date == null ? null : GsonFactory.get().toJson(date);
    }

}
