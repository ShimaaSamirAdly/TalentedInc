package inc.talentedinc.interactor.profile;

import android.util.Log;

import inc.talentedinc.API.ProfileEndpoint;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MMM on 6/2/2018.
 */

public class ProfileInteractorImpl implements ProfileInteractor {

    ProfileEndpoint profileEndpoint = AppRetrofit.getInstance().getRetrofitInstance().create(ProfileEndpoint.class);

    @Override
    public void updateUser(final User user, final UserProfileListener listener) {

        Call<User> call = profileEndpoint.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("resp", Integer.toString(response.code()));
                if(response.code() == 200) {
                    listener.onSuccess(user);
                }else{
                    listener.onFailedConnection();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    @Override
    public void getCurrentUser(int currentUserId, final UserProfileListener listener) {

        Call<User> call = profileEndpoint.getCurrentUserProfile(currentUserId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    Log.i("curentUser", ""+response.code());
                    listener.onGetCurrentUser(response.body());
                }else{
                    Log.i("curentUser", ""+response.code());
                    listener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
