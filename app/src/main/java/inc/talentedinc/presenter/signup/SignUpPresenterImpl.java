package inc.talentedinc.presenter.signup;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import inc.talentedinc.adapter.SignUpInterestsAdapter;
import inc.talentedinc.interactor.categories.CategoriesInteractor;
import inc.talentedinc.interactor.categories.CategoriesInteractorImpl;
import inc.talentedinc.interactor.signup.SignUpInteractor;
import inc.talentedinc.interactor.signup.SignUpInteractorImpl;
import inc.talentedinc.listener.CategoriesListener;
import inc.talentedinc.listener.SignUpListener;
import inc.talentedinc.listener.UserProfileListener;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.activities.SignUpActivity;
import inc.talentedinc.view.fragmnts.ThirdSignUpFragment;

/**
 * Created by MMM on 6/1/2018.
 */

public class SignUpPresenterImpl implements SignUpPresenter, CategoriesListener, SignUpListener {

    ThirdSignUpFragment thirdSignUpFragment;
    SignUpActivity signUpActivity;
    CategoriesInteractor categoriesInteractor;
    SignUpInteractor signUpInteractor;
    Context context;
    User user;

    public SignUpPresenterImpl(ThirdSignUpFragment view, Context context){

        this.thirdSignUpFragment = view;
        categoriesInteractor = new CategoriesInteractorImpl();
        this.context = context;
    }

    public SignUpPresenterImpl(SignUpActivity view, Context context){

        this.signUpActivity = view;
        signUpInteractor = new SignUpInteractorImpl();
        this.context = context;
        user =new User();
    }

    @Override
    public void getAllCategories() {

        categoriesInteractor.getAllCategories(this);
    }

    @Override
    public void insertUser(User user) {
        this.user = user;
//        Log.i("gender", user.getGender().toString());
        signUpInteractor.insertUser(user, this);
    }


    @Override
    public void onSuccess(List<Categories> categoriesList) {
        Log.i("listener", "Success");
        thirdSignUpFragment.setData(categoriesList);
    }

    @Override
    public void onFailed(){


    }

    @Override
    public void onFailedConnection() {
        Toast.makeText(context, "No InternetConnection", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onSuccess(int userId) {

        user.setUserId(userId);
        SharedPrefrencesSingleton.setSharedPrefUser(context, user);

        signUpActivity.switchToProfile();
    }

    @Override
    public void onFailure(String error) {

        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
    }
}
