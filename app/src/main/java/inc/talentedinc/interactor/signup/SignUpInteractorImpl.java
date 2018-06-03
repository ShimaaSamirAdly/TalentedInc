package inc.talentedinc.interactor.signup;

import android.util.Log;

import inc.talentedinc.API.UserEndpoint;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MMM on 6/1/2018.
 */

public class SignUpInteractorImpl implements SignUpInteractor {

//    UserEndpoint userEndpoint = RetrofitConnection.getInstance().create(UserEndpoint.class);

    @Override
    public void insertUser(final User user, final UserProfileListener listener) {

//        Call<User> call = userEndpoint.insertUser(user);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.i("conn", String.valueOf(response.code()));
//                listener.onSuccess();
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.i("conn", "failed");
//            }
//        });
    }
}
