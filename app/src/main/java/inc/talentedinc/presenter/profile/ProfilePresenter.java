package inc.talentedinc.presenter.profile;

import android.net.Uri;

import inc.talentedinc.model.User;

/**
 * Created by MMM on 6/2/2018.
 */

public interface ProfilePresenter {

    public void updateUser(User user);

    public void uploadImage(Uri filePath, User user);
}
