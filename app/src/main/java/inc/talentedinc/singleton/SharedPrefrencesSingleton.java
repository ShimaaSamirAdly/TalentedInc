package inc.talentedinc.singleton;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MMM on 5/27/2018.
 */

public class SharedPrefrencesSingleton {

    private static SharedPreferences sharedPreferences;
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


}
