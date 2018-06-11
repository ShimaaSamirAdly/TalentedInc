package inc.talentedinc.interactor.profile;

import inc.talentedinc.listener.OthersProfileListener;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.presenter.profile.OthersProfilePresenter;

/**
 * Created by MMM on 6/3/2018.
 */

public interface OthersProfileInteractor {

    public void getUserProfile(int currentUserId, int userProfileId, final OthersProfileListener listener);

    public void followUser(int currentUserId, int followingUserId, final OthersProfileListener listener);

    public void unfollowUser(int currentUserId, int unfollowingUserId, final OthersProfileListener listener);

    public void getFollowers(int userId, final OthersProfileListener listener);

    public void getFollowings(int userId, final OthersProfileListener listener);
}
