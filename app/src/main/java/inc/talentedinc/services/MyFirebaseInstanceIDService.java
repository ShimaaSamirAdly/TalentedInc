package inc.talentedinc.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import inc.talentedinc.singleton.SharedPrefrencesSingleton;

/**
 * Created by Alaa on 6/13/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.i("d5l ","nela");
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("myTag", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);



    }


    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        SharedPrefrencesSingleton.setDeviceToken(getApplicationContext(),token);

    }
}

