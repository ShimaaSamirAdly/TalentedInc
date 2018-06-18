package inc.talentedinc.singleton;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import inc.talentedinc.model.User;

/**
 * Created by MMM on 5/27/2018.
 */

public class SharedPrefrencesSingleton {

    private static volatile SharedPreferences sharedPreferences;
    private static final String prefName = "UserPref";


    public static SharedPreferences getInstance(Context context){

        if (sharedPreferences == null){

            synchronized (SharedPrefrencesSingleton.class){

                if(sharedPreferences == null){

                    sharedPreferences = context.getSharedPreferences(prefName, 0);
                }
            }
        }

        return sharedPreferences;
    }


    public static User getSharedPrefUser(Context context){

        sharedPreferences = getInstance(context);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString("user", "");

        User user = gson.fromJson(userJson, User.class);

        return user;

    }


    public static void setSharedPrefUser(Context context, User user){

        SharedPreferences preferences = SharedPrefrencesSingleton.getInstance(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.remove("user");
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
    }

    public static void setDeviceToken(Context context , String deviceToken){

        SharedPreferences preferences = SharedPrefrencesSingleton.getInstance(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.remove("deviceToken");
        prefsEditor.putString("deviceToken", deviceToken);
        prefsEditor.commit();
    }
    public static String getDeviceToken(Context context){
        sharedPreferences = getInstance(context);
        String deviceToken = sharedPreferences.getString("deviceToken", "");

        return   deviceToken ;

    }


}
