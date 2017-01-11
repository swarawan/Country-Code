package com.swarawan.countrycode.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.swarawan.countrycode.R;

/**
 * Created by rioswarawan on 1/6/17.
 */

public class PrefUtils {

    /**
     * Store Token from Google Sign In
     */
    public static void storeGoogleToken(Context context, String time) {
        String key = context.getString(R.string.pref_google_token);
        store(context, key, time);
    }

    /**
     * Get stored token
     */
    public static String getGoogleToken(Context context) {
        String key = context.getString(R.string.pref_google_token);
        return getString(context, key);
    }

    /**
     * Store Token from Google UID
     */
    public static void storeGoogleUID(Context context, String time) {
        String key = context.getString(R.string.pref_google_uid);
        store(context, key, time);
    }

    /**
     * Get stored Google UID
     */
    public static String getGoogleUID(Context context) {
        String key = context.getString(R.string.pref_google_uid);
        return getString(context, key);
    }

    /**
     * Call if user has signed in
     */
    public static void keepSignIn(Context context) {
        String key = context.getString(R.string.pref_keep_sign_in);
        store(context, key, true);
    }

    /**
     * Check if user signed in or not
     */
    public static boolean isSignedIn(Context context) {
        String key = context.getString(R.string.pref_keep_sign_in);
        return getBoolean(context, key);
    }

    /**
     * Clear SharedPreference
     */
    private static void clear(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * Store String SharedPreference
     */
    private static void store(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Store Boolean SharedPreference
     */
    private static void store(Context context, String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Store Integer SharedPreference
     */
    private static void store(Context context, String key, int value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Return String of SharedPreference by stored key
     */
    private static String getString(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }

    /**
     * Return Boolean of SharedPreference by stored key
     */
    private static boolean getBoolean(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);
    }

    /**
     * Return Integer of SharedPreference by stored key
     */
    private static int getInteger(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 0);
    }
}
