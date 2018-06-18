package inc.talentedinc.interactor.profile;

import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;

/**
 * Created by MMM on 6/2/2018.
 */

public interface ProfileInteractor {

    public void updateUser(User user, final UserProfileListener listener);

    public void getCurrentUser(int currentUserId, final UserProfileListener listener);
}
