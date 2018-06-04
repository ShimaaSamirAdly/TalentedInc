package inc.talentedinc.presenter.profile;

import android.content.Context;
import android.util.Log;

import inc.talentedinc.interactor.profile.OthersProfileInteractor;
import inc.talentedinc.interactor.profile.OthersProfileInteractorImpl;
import inc.talentedinc.listener.OthersProfileListener;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.activities.OthersProfileActivity;

/**
 * Created by MMM on 6/3/2018.
 */

public class OthersProfilePresenterImpl implements OthersProfilePresenter, OthersProfileListener {

    private OthersProfileActivity view;
    private OthersProfileInteractor othersProfileInteractor;
    private Context context;

    public OthersProfilePresenterImpl(OthersProfileActivity view, Context context){

        this.view = view;
        othersProfileInteractor = new OthersProfileInteractorImpl();
        this.context = context;
    }

    @Override
    public void getProfileData(int userId) {

        User user = SharedPrefrencesSingleton.getSharedPrefUser(context);
        Log.i("useruser", ""+user.getUserId());
        Log.i("useruser", ""+userId);
        othersProfileInteractor.getUserProfile(userId, user.getUserId(), this);

    }

    @Override
    public void onGetProfile(User user){
        Log.i("othersprofiledataaaaaa", "onGetProfile");
        view.setUserData(user);
    }
}
