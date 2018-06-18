package inc.talentedinc.presenter.profile;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import inc.talentedinc.interactor.profile.OthersProfileInteractor;
import inc.talentedinc.interactor.profile.OthersProfileInteractorImpl;
import inc.talentedinc.listener.OthersProfileListener;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.Followers;
import inc.talentedinc.model.OtherUsers;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.activities.OthersProfileActivity;

/**
 * Created by MMM on 6/3/2018.
 */

public class OthersProfilePresenterImpl implements OthersProfilePresenter, OthersProfileListener {

    private OthersProfileActivity view;
    private OthersProfileInteractor othersProfileInteractor;
    private Context context;
    private User userData;

    public OthersProfilePresenterImpl(OthersProfileActivity view, Context context){

        this.view = view;
        othersProfileInteractor = new OthersProfileInteractorImpl();
        this.context = context;
    }

    @Override
    public void getProfileData(int userId) {

        User user = SharedPrefrencesSingleton.getSharedPrefUser(context);
        Log.i("useruser", ""+user.getUserId());
        Log.i("useruser", ""+userId);
        othersProfileInteractor.getUserProfile(userId, user.getUserId(), this);

    }

    @Override
    public void unfollowUser(User user) {

        User currentUser = SharedPrefrencesSingleton.getSharedPrefUser(context);
        othersProfileInteractor.unfollowUser(currentUser.getUserId(), user.getUserId(), this);
    }

    @Override
    public void followUser(User user) {

        User currentUser = SharedPrefrencesSingleton.getSharedPrefUser(context);
        Log.i("userrrr", ""+currentUser.getFirstName());
        othersProfileInteractor.followUser(currentUser.getUserId(), user.getUserId(), this);

    }

    @Override
    public void onGetProfile(OtherUsers user){
        Log.i("othersprofiledataaaaaa", "onGetProfile");

        userData = new User();
        userData.setUserId(user.getUserId());
        userData.setPhone(user.getPhone());
        userData.setUserType(user.getUserType());
        userData.setInstructor(user.getInstructor());
        userData.setCity(user.getCity());
        userData.setImgUrl(user.getImgUrl());
        userData.setEmail(user.getEmail());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setUserDob(user.getUserDob());
        userData.setFollowersNumber(user.getFollowersNumber());
        userData.setFollowingNumber(user.getFollowingNumber());
        userData.setCategoryCollection(user.getCategoryCollection());
        userData.setFollowing(user.isFollowing());

        view.setUserData(userData);
    }

    @Override
    public void onSuccessFollowing() {

//        userData.setFollowing(true);
//        userData.setFollowersNumber(userData.getFollowersNumber()+1);
//        view.setUserData(userData);
        Toast.makeText(context, "Successfully Following", Toast.LENGTH_LONG).show();
        view.refreshActivity();
    }

    @Override
    public void onSuccessUnfollowing() {
//        userData.setFollowing(false);
//        userData.setFollowersNumber(userData.getFollowersNumber()-1);
//        view.setUserData(userData);
        Log.i("unfollow", "onSuccessUnfollowing");
        Toast.makeText(context, "Successfully UnFollowing", Toast.LENGTH_LONG).show();
        view.refreshActivity();
    }

    @Override
    public void onGetFollowers(ArrayList<Followers> followers) {

    }

    @Override
    public void onFailedConnection() {
        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
    }
}
