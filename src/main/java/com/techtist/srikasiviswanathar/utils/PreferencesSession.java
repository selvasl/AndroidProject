package com.techtist.srikasiviswanathar.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesSession {

    public static final String PREF_FILE_NAME = "Med4US";


    public static void saveStringSession(Context activity, String key, String value) {

        SharedPreferences sharedPref = activity.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();

      /*  SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();*/

    }

    public static String getStringSession(Context activity, String key, String defaultValue) {

        SharedPreferences sharedPref = activity.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        String result = sharedPref.getString(key, defaultValue);
        return result;
    }

    public static void saveBooleanSession(Context activity, String key, boolean result) {


        SharedPreferences sharedPref = activity.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, result);
        editor.apply();
    }

    public static boolean getBooleanSession(Context context, String key, boolean defaultValue) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        boolean result = sharedPref.getBoolean(key, defaultValue);
        return result;
    }
    public static void clearAll(Context context){

        clearData(context,Config.PREF_USERNAME);
        clearData(context,Config.PREF_USER_MOBILE_NUMBER);


    }
    public static void clearData(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE);
        sharedPreferences.edit().clear();
        sharedPreferences.edit().commit();
        sharedPreferences.edit().apply();

    }
}

