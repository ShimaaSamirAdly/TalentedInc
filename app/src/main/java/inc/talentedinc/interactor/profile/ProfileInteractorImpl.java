package inc.talentedinc.interactor.profile;

import android.util.Log;

import inc.talentedinc.API.ProfileEndpoint;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by MMM on 6/2/2018.
 */

public class ProfileInteractorImpl implements ProfileInteractor {

    ProfileEndpoint profileEndpoint = AppRetrofit.getInstance().getRetrofitInstance().create(ProfileEndpoint.class);

    @Override
    public void updateUser(final User user, final UserProfileListener listener) {

        Call<Void> call = profileEndpoint.updateUser(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("resp", Integer.toString(response.code()));
                if(response.code() == 200) {
                    listener.onSuccess(user);
                }else{
                    listener.onFailedConnection();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("onFailure", t.getMessage());
                listener.onFailure();
            }
        });
    }

    @Override
    public void getCurrentUser(int currentUserId, final UserProfileListener listener) {

        Call<User> call = profileEndpoint.getCurrentUserProfile(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),currentUserId, "no-cache");
        Log.i("userId", ""+currentUserId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    Log.i("curentUser", ""+response.code());
                    User user = response.body();
                    Log.i("userInt", user.getCity());
                    listener.onGetCurrentUser(user);
                }else{
                    Log.i("curentUser", ""+response.code());
                    listener.onFailedConnection();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("curentUser", "faileeed");
                    listener.onFailure();
            }
        });
    }
}
