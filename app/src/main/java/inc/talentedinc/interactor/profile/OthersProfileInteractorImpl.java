package inc.talentedinc.interactor.profile;

import android.util.Log;

import inc.talentedinc.API.ProfileEndpoint;
import inc.talentedinc.listener.OthersProfileListener;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MMM on 6/3/2018.
 */

public class OthersProfileInteractorImpl implements OthersProfileInteractor {

    ProfileEndpoint profileEndpoint = AppRetrofit.getInstance().getRetrofitInstance().create(ProfileEndpoint.class);
    User user;
    @Override
    public void getUserProfile(int userProfileId, int currentUserId, final OthersProfileListener listener) {

        Call<User> call = profileEndpoint.getUserProfile(userProfileId, currentUserId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("resp", Integer.toString(response.code()));
                user = new User();
                user = (User) response.body();
                Log.i("resp", ""+user);

                listener.onGetProfile(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
