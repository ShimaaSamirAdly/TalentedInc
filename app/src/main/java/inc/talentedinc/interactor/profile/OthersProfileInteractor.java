package inc.talentedinc.interactor.profile;

import inc.talentedinc.listener.OthersProfileListener;
import inc.talentedinc.listener.UserProfileListener;

/**
 * Created by MMM on 6/3/2018.
 */

public interface OthersProfileInteractor {

    public void getUserProfile(int currentUserId, int userProfileId, final OthersProfileListener listener);
}
