package inc.talentedinc.interactor.signup;

import android.util.Log;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.UserEndpoint;
import inc.talentedinc.listener.SignUpListener;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;
import inc.talentedinc.model.response.MainResponse;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by MMM on 6/1/2018.
 */

public class SignUpInteractorImpl implements SignUpInteractor {

    UserEndpoint userEndpoint = AppRetrofit.getInstance().getRetrofitInstance().create(UserEndpoint.class);

    @Override
    public void insertUser(final User user, final SignUpListener listener) {

        Log.i("errorData", "respo");
        Call<MainResponse> call = userEndpoint.insertUser(SharedPrefrencesSingleton.getDeviceToken(getApplicationContext()),user);
        call.enqueue(new Callback<MainResponse>() {

            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                Log.i("deviceTok", SharedPrefrencesSingleton.getDeviceToken(getApplicationContext()));
                Log.i("errorData", String.valueOf(response.code()));
                if (response.code()== 202) {
                    SharedPrefrencesSingleton.setSharedPrefToken(getApplicationContext(),response.headers().get("Token"));

                    MainResponse res = (MainResponse) response.body();
                    listener.onSuccess(Integer.parseInt(res.getUserId()));

                }else if(response.code()== 400){

                    listener.onFailedSignUp("Email is Already Used");

                }else{
                    listener.onFailedConnection();
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.i("conn", "failed");
            }
        });
    }
}
