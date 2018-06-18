package inc.talentedinc.presenter;

/**
 * Created by MMM on 6/9/2018.
 */

public interface FollowersPresenter {

    public void getFollowers(int userId);
    public void getFollowing(int userId);
    public void unfollow(int userId);
}
