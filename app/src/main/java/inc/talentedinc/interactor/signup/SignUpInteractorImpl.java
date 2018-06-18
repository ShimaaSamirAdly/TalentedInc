package inc.talentedinc.interactor.signup;

import android.util.Log;

import inc.talentedinc.API.UserEndpoint;
import inc.talentedinc.listener.SignUpListener;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;
import inc.talentedinc.model.response.MainResponse;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MMM on 6/1/2018.
 */

public class SignUpInteractorImpl implements SignUpInteractor {

    UserEndpoint userEndpoint = AppRetrofit.getInstance().getRetrofitInstance().create(UserEndpoint.class);

    @Override
    public void insertUser(final User user, final SignUpListener listener) {

        Log.i("errorData", "respo");
        Call<MainResponse> call = userEndpoint.insertUser(user);
        call.enqueue(new Callback<MainResponse>() {

            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {

                Log.i("errorData", String.valueOf(response.code()));
                MainResponse res = (MainResponse) response.body();

                if(response.code() == 200) {
                    Log.i("errorData", "responseCode "+response.code());
                    listener.onSuccess(Integer.parseInt(res.getUserId()));
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
