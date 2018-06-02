package inc.talentedinc.presenter.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.google.gson.Gson;

import inc.talentedinc.interactor.profile.ProfileInteractor;
import inc.talentedinc.interactor.profile.ProfileInteractorImpl;
import inc.talentedinc.interactor.uploadimage.UploadImageInteractor;
import inc.talentedinc.interactor.uploadimage.UploadImageInteractorImpl;
import inc.talentedinc.listener.UploadImageListener;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.fragmnts.ProfileFragment;

/**
 * Created by MMM on 6/2/2018.
 */

public class ProfilePresenterImpl implements ProfilePresenter, UserProfileListener, UploadImageListener {

    ProfileFragment profileFragment;
    ProfileInteractor profileInteractor;
    UploadImageInteractor uploadImageInteractor;
    User user;
    Context context;

    public ProfilePresenterImpl(ProfileFragment view, Context context){

        this.profileFragment = view;
        profileInteractor = new ProfileInteractorImpl();
        uploadImageInteractor = new UploadImageInteractorImpl();
        user = new User();
        this.context = context;
    }

    @Override
    public void updateUser(User user) {

        profileInteractor.updateUser(user, this);
    }

    @Override
    public void uploadImage(Uri filePath, User user) {

        this.user = user;
        uploadImageInteractor.uploadImage(filePath, this);
    }

    @Override
    public void onSuccess() {

        SharedPreferences preferences = SharedPrefrencesSingleton.getInstance(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.remove("user");
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccessUploadImage(String imageUrl) {

        user.setImgUrl(imageUrl);
        updateUser(user);
    }
}
