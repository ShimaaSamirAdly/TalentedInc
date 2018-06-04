package inc.talentedinc.interactor.signup;

import inc.talentedinc.listener.SignUpListener;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;

/**
 * Created by MMM on 6/1/2018.
 */

public interface SignUpInteractor {

    public void insertUser(User user, SignUpListener listener);
}
