package inc.talentedinc.interactor.profile;

import android.util.Log;

import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MMM on 6/2/2018.
 */

public class ProfileInteractorImpl implements ProfileInteractor {

//    ProfileEndpoint profileEndpoint = RetrofitConnection.getInstance().create(ProfileEndpoint.class);

    @Override
    public void updateUser(User user, final UserProfileListener listener) {

//        Call<User> call = profileEndpoint.updateUser(user);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.i("resp", Integer.toString(response.code()));
//                listener.onSuccess();
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                listener.onFailure();
//            }
//        });
    }
}
