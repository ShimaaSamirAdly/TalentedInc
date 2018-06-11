package inc.talentedinc.listener;

import inc.talentedinc.model.User;

/**
 * Created by MMM on 5/23/2018.
 */

public interface UserProfileListener {

    public void onSuccess(User user);

    public void onFailedConnection();

    public void onFailure();

    public void onGetCurrentUser(User user);
}
