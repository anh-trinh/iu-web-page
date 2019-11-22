package com.tnta.iuwebpage;

/**
 * Created by theanh on 10/31/15.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private static final String TABNAME = "DEFAULT";
    private static final String STUDENT_ID = "StudentID";
    private static final String EDU_PASSWORD = "EduPassword";
    private static final String BB_PASSWORD = "BBPassword";
    private static final String M_PASSWORD = "MPassword";

    private final SharedPreferences preferences;

    public Preferences(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setTab(String tabName) {
        if (tabName != null) {
            preferences.edit().putString(TABNAME, tabName).apply();
        } else {
            preferences.edit().remove(TABNAME).apply();
        }
    }

    public String getTab() {
        return preferences.getString(TABNAME, null);
    }

    public void setID(String Id) {
        if (Id != null) {
            preferences.edit().putString(STUDENT_ID, Id).apply();
        } else {
            preferences.edit().remove(STUDENT_ID).apply();
        }
    }

    public String getID() {
        return preferences.getString(STUDENT_ID, null);
    }

    public void setEduUserPassword(String eduPassword) {
        if (eduPassword != null) {
            preferences.edit().putString(EDU_PASSWORD, eduPassword).apply();
        } else {
            preferences.edit().remove(EDU_PASSWORD).apply();
        }
    }

    public String getEduUserPassword() {
        return  preferences.getString(EDU_PASSWORD, null);
    }

    public void setBBUserPassword(String BbPassword) {
        if (BbPassword != null) {
            preferences.edit().putString(BB_PASSWORD, BbPassword).apply();
        } else {
            preferences.edit().remove(BB_PASSWORD).apply();
        }
    }

    public String getBBUserPassword() {
        return  preferences.getString(BB_PASSWORD, null);
    }

    public void setMUserPassword(String mPassword) {
        if (mPassword != null) {
            preferences.edit().putString(M_PASSWORD, mPassword).apply();
        } else {
            preferences.edit().remove(M_PASSWORD).apply();
        }
    }

    public String getMUserPassword() {
        return  preferences.getString(M_PASSWORD, null);
    }
}
