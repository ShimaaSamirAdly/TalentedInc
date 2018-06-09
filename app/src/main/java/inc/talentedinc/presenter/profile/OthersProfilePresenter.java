package inc.talentedinc.presenter.profile;

import inc.talentedinc.model.User;

/**
 * Created by MMM on 6/3/2018.
 */

public interface OthersProfilePresenter {

    public void getProfileData(int userId);

    public void unfollowUser(User user);

    public void followUser(User user);
}
