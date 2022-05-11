package com.example.flipcart.pref;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public abstract class BasePref
{
    private static final String TAG = BasePref.class.getSimpleName();
    private SharedPreferences.Editor getEditor() {
        return getPrefs().edit();
    }

    protected abstract SharedPreferences getPrefs();

    public void deleteAllPreference() {
        try {
            getPrefs().edit().clear().apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeKey(String key) {
        getPrefs().edit().remove(key).apply();
    }

    //Function to check the key exists or not in the preferences
    @SuppressWarnings("unchecked")
    public boolean containsKey(String key) {
        return getPrefs().contains(key);
    }

    //Function to get the preference based on key
    @SuppressWarnings("unchecked")
    public <T> T getPreference(String key) {
        return (T) getPrefs().getAll().get(key);
    }

    //Function to get the preference based on key
    @SuppressWarnings("unchecked")
    public <T> T getPreference(String key, T defValue) {
        T returnValue = (T) getPrefs().getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    //Function to check the value exists or not in the preferences based on key
    @SuppressWarnings("unchecked")
    public boolean isPreferenceExists(String key) {
        return getPrefs().contains(key);
    }

    //Function to save the preferences based on key
    public void savePreference(String key, Object value) {
        deletePreferenceIfPresence(key);
        if (value instanceof Boolean) {
            getPrefs().edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Integer) {
            getPrefs().edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Float) {
            getPrefs().edit().putFloat(key, (Float) value).apply();
        } else if (value instanceof Long) {
            getPrefs().edit().putLong(key, (Long) value).apply();
        } else if (value instanceof String) {
            getPrefs().edit().putString(key, (String) value).apply();
        } else if (value instanceof Enum) {
            getPrefs().edit().putString(key, value.toString()).apply();
        } else if (value != null) {
            throw new RuntimeException("Attempting to save non-primitive preference");
        }
    }

    //Function to remove the key and value if exusts
    public void deletePreferenceIfPresence(String key) {
        if (getPrefs().contains(key)) {
            getPrefs().edit().remove(key).apply();
        }
    }

    /**
     * Persists an Object in prefs at the specified key, class of given Object must implement Model
     * interface
     *
     * @param key         String
     * @param modelObject Object to persist
     * @param <M>         Generic for Object
     */
    protected <M> void setObject(String key, M modelObject) {
        String value = createJSONStringFromObject(modelObject);
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString(key, value);
        editor.apply();
    }

    //Function to create the Json from an object using GSON
    public static String createJSONStringFromObject(Object object) {
        return new Gson().toJson(object);
    }

    /**
     * Fetches the previously stored Object of given Class from prefs
     *
     * @param key                String
     * @param classOfModelObject Class of persisted Object
     * @param <M>                Generic for Object
     * @return Object of given class
     */
    @Nullable
    protected <M> M getObject(String key, Type classOfModelObject) {
        String jsonData = getPrefs().getString(key, null);

        if (null != jsonData) {
            try {
                Gson gson = new Gson();
                return gson.fromJson(jsonData, classOfModelObject);
            } catch (ClassCastException cce) {
                Log.e(TAG, "Cannot convert string obtained from prefs into collection of type " +
                        classOfModelObject.toString(), cce);
            }
        }
        return null;
    }

}
