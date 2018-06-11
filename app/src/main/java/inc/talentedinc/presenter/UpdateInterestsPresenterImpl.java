package inc.talentedinc.presenter;

import android.content.Context;
import android.widget.Toast;

import inc.talentedinc.interactor.profile.ProfileInteractor;
import inc.talentedinc.interactor.profile.ProfileInteractorImpl;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.activities.UpdateInterestsActivity;

/**
 * Created by MMM on 6/9/2018.
 */

public class UpdateInterestsPresenterImpl implements UpdateInterestsPresenter, UserProfileListener{

    private UpdateInterestsActivity view;
    private ProfileInteractor profileInteractor;
    private Context context;

    public UpdateInterestsPresenterImpl(UpdateInterestsActivity view, Context context){
        this.view = view;
        this.context = context;
        profileInteractor = new ProfileInteractorImpl();
    }


    @Override
    public void updateUser(User user) {

        profileInteractor.updateUser(user, this);
    }

    @Override
    public void onSuccess(User user) {

        SharedPrefrencesSingleton.setSharedPrefUser(context, user);
        view.switchToHome();
    }

    @Override
    public void onFailedConnection() {

        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure() {

        Toast.makeText(context, "Error Occured", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetCurrentUser(User user) {

    }
}
