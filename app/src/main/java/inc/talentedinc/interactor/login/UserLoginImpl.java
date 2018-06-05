package inc.talentedinc.interactor.login;

import android.util.Log;

import inc.talentedinc.API.ApiLogin;
import inc.talentedinc.listener.OnLoginResult;
import inc.talentedinc.model.User;
import inc.talentedinc.model.UserLogin;
import inc.talentedinc.model.response.MainResponse;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alaa on 5/23/2018.
 */

public class UserLoginImpl implements UserLoginInter {
    ApiLogin apiLogin = AppRetrofit.getInstance().getApiLogin();


    @Override
    public void sendLoginRequest(UserLogin userLogin, final OnLoginResult onresult ) {

        apiLogin.checkUserLogin(userLogin).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("nela ",response.body().getEmail());


//                Log.i("check",response.body().getStatus());

                onresult.onSucess(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Log.i("baz",call.toString());
                onresult.onFailure();
            }
        });
    }

}
