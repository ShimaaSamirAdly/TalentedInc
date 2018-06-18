package inc.talentedinc.presenter.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
    public void getCurrentUser() {

        user = SharedPrefrencesSingleton.getSharedPrefUser(context);
        profileInteractor.getCurrentUser(user.getUserId(), this);
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
    public void onSuccess(User user) {
        SharedPrefrencesSingleton.setSharedPrefUser(context, user);
    }

    @Override
    public void onFailedConnection() {

    }

    @Override
    public void onFailure() {

        Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_LONG).show();
        user = SharedPrefrencesSingleton.getSharedPrefUser(context);
        profileFragment.setUserData(user);
    }

    @Override
    public void onGetCurrentUser(User user) {

        profileFragment.setUserData(user);
    }

    @Override
    public void onSuccessUploadImage(String imageUrl) {

        user.setImgUrl(imageUrl);
        SharedPrefrencesSingleton.setSharedPrefUser(context, user);
        profileFragment.setProfileImage(imageUrl);
        updateUser(user);
    }
}
