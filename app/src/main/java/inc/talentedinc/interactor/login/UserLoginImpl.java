package inc.talentedinc.interactor.login;

import android.util.Log;

import inc.talentedinc.API.ApiLogin;
import inc.talentedinc.listener.OnLoginResult;
import inc.talentedinc.model.User;
import inc.talentedinc.model.UserLogin;
import inc.talentedinc.model.response.MainResponse;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Alaa on 5/23/2018.
 */

public class UserLoginImpl implements UserLoginInter {
    ApiLogin apiLogin = AppRetrofit.getInstance().getApiLogin();


    @Override
    public void sendLoginRequest(UserLogin userLogin, final OnLoginResult onresult) {

        apiLogin.checkUserLogin(SharedPrefrencesSingleton.getDeviceToken(getApplicationContext()),userLogin).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response != null) {
                    //response.headers().get("Token");
                   // Log.i("TOKEN",response.headers().get("Token"));
                    if(response.code() == 200) {
                        Log.i("TOKEN",SharedPrefrencesSingleton.getDeviceToken(getApplicationContext()));
                        SharedPrefrencesSingleton.setSharedPrefToken(getApplicationContext(),response.headers().get("Token"));
                        Log.i("nela ", response.raw().toString());
                        onresult.onSucess(response.body());
                    }
                    else{
                        onresult.onFailure();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Log.i("baz", call.toString());
                onresult.onFailure();
            }
        });
    }

}
