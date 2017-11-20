package com.madrzak.mygenericlistingapp.util;

/**
 * Created by Łukasz on 19/11/2017.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
    private static volatile Gson gson;

    public final static String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static Gson get() {
        if (gson == null) {

            synchronized (GsonFactory.class) {
                if (gson == null) {
                    gson = new GsonBuilder()
                            .setDateFormat(ISO_DATE_FORMAT)
                            .create();
                }
            }
        }
        return gson;
    }
}
