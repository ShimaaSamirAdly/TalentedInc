package inc.talentedinc.interactor.login;

import android.util.Log;

import inc.talentedinc.API.ApiLogin;
import inc.talentedinc.listener.OnLoginResult;
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
    public void sendLoginRequest(String email, String password , final OnLoginResult onresult ) {

        apiLogin.checkUserLogin(email , password ).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                onresult.onSucess(response.body());

//                Log.i("check",response.body().getStatus());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                call.cancel();
                onresult.onFailure();
            }
        });
    }

}
