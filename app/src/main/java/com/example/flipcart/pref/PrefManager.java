package com.example.flipcart.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.flipcart.model.LoginData;

@SuppressWarnings("unused")
public class PrefManager extends BasePref {

    private final SharedPreferences mPref;
    private static PrefManager sInstance;


    public static synchronized PrefManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PrefManager(context);
        }
        return sInstance;
    }

    public PrefManager(@NonNull Context context) {
        String prefsFile = context.getPackageName();
        mPref = context.getSharedPreferences(prefsFile, Context.MODE_PRIVATE);
    }


    public void clearSharePrefUserData() {
        deleteAllPreference();
    }

    @Override
    public SharedPreferences getPrefs() {
        return mPref;
    }

    private Editor getEditor() {
        return mPref.edit();
    }

    public void savePrefrenceData(String key, Object value) {
        savePreference(key, value);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T getPrefrenceData(String key) {
        return getPreference(key);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T getPrefrenceData(String key, T type) {
        return getPreference(key,type);
    }

    public Boolean isLogin() {
        return getPreference(Prefkeys.IS_LOGIN, false);
    }

    public void setLogin(boolean isLogged) {
        savePreference(Prefkeys.IS_LOGIN, isLogged);
    }


    public void saveUserData(LoginData data) {
        setObject(Prefkeys.USERDATA, data);
    }
    public LoginData getUserData() {
        return getObject(Prefkeys.USERDATA, LoginData.class);
    }


}

