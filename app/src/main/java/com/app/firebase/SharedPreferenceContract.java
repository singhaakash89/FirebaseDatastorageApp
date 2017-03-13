package com.app.firebase;

/**
 * Created by Aakash Singh on 28-10-2016.
 */
public interface SharedPreferenceContract {

    public static final String USER_DATA = "WeightalysisSharedPreferenceFile";
    public static final String STRING_DEFAULT = "N/A";
    public static final boolean BOOLEAN_DEFAULT = false;

    public void putString(String key, String value);

    public void putBoolean(String key, boolean value);

    public boolean getBoolean(String key);

    public String getString(String key);

}
