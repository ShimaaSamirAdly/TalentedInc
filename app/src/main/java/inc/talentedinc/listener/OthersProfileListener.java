package inc.talentedinc.listener;

import java.util.ArrayList;

import inc.talentedinc.model.Followers;
import inc.talentedinc.model.OtherUsers;
import inc.talentedinc.model.User;

/**
 * Created by MMM on 6/3/2018.
 */

public interface OthersProfileListener {

    public void onGetProfile(OtherUsers user);

    public void onSuccessFollowing();

    public void onSuccessUnfollowing();

    public void onGetFollowers(ArrayList<Followers> followers);

    public void onFailedConnection();
}
