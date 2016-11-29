package com.pi.emersongarcia86.lr_samurais_android_v2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

import models.Participante;

/**
 * Created by hendy on 23/11/16.
 */

public class Application {

    private static SharedPreferences preferences;
    private static String identificadorEvento;

    public static boolean isLogged(Activity activity) {
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return preferences.getBoolean("LOGIN", false);
    }

    public static void login(Activity activity, List<Participante> lpg) {
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("LOGIN", true);
        editor.putInt("userid", lpg.get(0).getCodParticipante());
        editor.apply();
    }

    public static void dadosEvento(String identificador){
        identificadorEvento = preferences.getString("identificador", identificador);
    }
    public static String getEvento(){
        if(identificadorEvento != null){
            return identificadorEvento;
        }
        return "";
    }
}
