package inc.talentedinc.interactor.profile;

import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.API.ProfileEndpoint;
import inc.talentedinc.listener.OthersProfileListener;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.Followers;
import inc.talentedinc.model.OtherUsers;
import inc.talentedinc.model.User;
import inc.talentedinc.model.request.FollowRequest;
import inc.talentedinc.presenter.profile.OthersProfilePresenter;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by MMM on 6/3/2018.
 */

public class OthersProfileInteractorImpl implements OthersProfileInteractor {

    ProfileEndpoint profileEndpoint = AppRetrofit.getInstance().getRetrofitInstance().create(ProfileEndpoint.class);
    OtherUsers user;

    @Override
    public void getUserProfile(int userProfileId, int currentUserId, final OthersProfileListener listener) {
        Call<OtherUsers> call = profileEndpoint.getUserProfile(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),userProfileId, currentUserId, "no-cache");
        call.enqueue(new Callback<OtherUsers>() {
            @Override
            public void onResponse(Call<OtherUsers> call, Response<OtherUsers> response) {
                Log.i("resp", Integer.toString(response.code()));
                if(response.code() == 200) {
                    user = (OtherUsers) response.body();
                    Log.i("resp", "userData"+user.getFirstName());
                    Log.i("resp", "" + user);

                    listener.onGetProfile(user);
                }else{
                    listener.onFailedConnection();
                }
            }

            @Override
            public void onFailure(Call<OtherUsers> call, Throwable t) {

            }
        });
    }

    @Override
    public void followUser(int currentUserId, int followingUserId, final OthersProfileListener listener) {

        FollowRequest followRequest = new FollowRequest();
        followRequest.setId(currentUserId);
        followRequest.setUserToFollowId(followingUserId);

        Call<Void> call = profileEndpoint.followUser(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()), followRequest);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("followinteractor", "success"+ response.code());
                listener.onSuccessFollowing();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    public void unfollowUser(int currentUserId, int unfollowingUserId, final OthersProfileListener listener) {

        Call<Void> call = profileEndpoint.unfollowUser(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),currentUserId, unfollowingUserId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("unfollowinteractor", "success"+response.code());
                listener.onSuccessUnfollowing();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    public void getFollowers(int userId, final OthersProfileListener listener) {

        Call<ArrayList<Followers>> call = profileEndpoint.getFollowers(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),userId);
        call.enqueue(new Callback<ArrayList<Followers>>() {
            @Override
            public void onResponse(Call<ArrayList<Followers>> call, Response<ArrayList<Followers>> response) {
                Log.i("follow", "success");
                if(response.code() == 200) {
                    listener.onGetFollowers(response.body());
                }else{
                    listener.onFailedConnection();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Followers>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getFollowings(int userId, final OthersProfileListener listener) {

        Call<ArrayList<Followers>> call = profileEndpoint.getFollowing(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),userId);
        call.enqueue(new Callback<ArrayList<Followers>>() {
            @Override
            public void onResponse(Call<ArrayList<Followers>> call, Response<ArrayList<Followers>> response) {
                Log.i("follow", "success"+response.code());
                if(response.code() == 200) {
                    listener.onGetFollowers(response.body());
                }else{
                    listener.onFailedConnection();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Followers>> call, Throwable t) {

            }
        });

    }
}
