package com.example.emersongarcia86.lr_samurais_android_v2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mikabrytu on 23/11/16.
 */

public class Application {

    private static SharedPreferences preferences;

    public static boolean isLogged(Activity activity) {
        preferences = activity.getPreferences(Context.MODE_PRIVATE);
        return preferences.getBoolean("LOGIN", false);
    }

    public static void login(Activity activity) {
        preferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("LOGIN", true);
        editor.apply();
    }
}
