package inc.talentedinc.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import inc.talentedinc.interactor.profile.OthersProfileInteractor;
import inc.talentedinc.interactor.profile.OthersProfileInteractorImpl;
import inc.talentedinc.listener.OthersProfileListener;
import inc.talentedinc.model.Followers;
import inc.talentedinc.model.OtherUsers;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.activities.FollowersActivity;
import inc.talentedinc.view.activities.OthersProfileActivity;

/**
 * Created by MMM on 6/9/2018.
 */

public class FollowersPresenterImpl implements FollowersPresenter, OthersProfileListener {

    private FollowersActivity followersActivity;
    private OthersProfileInteractor othersProfileInteractor;
    private Context context;
    private User user;


    public FollowersPresenterImpl(Context context, FollowersActivity followersActivity){

        this.followersActivity = followersActivity;
        othersProfileInteractor = new OthersProfileInteractorImpl();
        this.context = context;
        user = SharedPrefrencesSingleton.getSharedPrefUser(context);
    }

    @Override
    public void getFollowers(int userId) {

        othersProfileInteractor.getFollowers(userId, this);
    }

    @Override
    public void getFollowing(int userId) {

        othersProfileInteractor.getFollowings(userId, this);
    }

    @Override
    public void unfollow(int userId) {

        othersProfileInteractor.unfollowUser(user.getUserId(), userId, this);

    }


    @Override
    public void onGetProfile(OtherUsers user) {

    }

    @Override
    public void onSuccessFollowing() {

    }

    @Override
    public void onSuccessUnfollowing() {

//        followersActivity.setUnfollow();
    }

    @Override
    public void onGetFollowers(ArrayList<Followers> followers) {

        followersActivity.setData(followers);
    }

    @Override
    public void onFailedConnection() {
        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
    }
}
