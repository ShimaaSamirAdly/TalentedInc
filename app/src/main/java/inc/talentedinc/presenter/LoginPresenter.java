package inc.talentedinc.presenter;

import android.util.Log;

import inc.talentedinc.interactor.login.UserLoginImpl;
import inc.talentedinc.interactor.login.UserLoginInter;
import inc.talentedinc.listener.OnLoginResult;
import inc.talentedinc.model.User;
import inc.talentedinc.model.UserLogin;
import inc.talentedinc.model.response.MainResponse;

/**
 * Created by Alaa on 5/23/2018.
 */

public class LoginPresenter {

    LoginView  view ;
   private UserLoginInter userLoginInter ;
   private MainResponse mainResponse = new MainResponse() ;
   private User userLogged ;

     public  LoginPresenter(){

        userLoginInter = new UserLoginImpl() ;
    }




    public void setView(UserLogin userLogin, LoginView  view) {
        this.view =view;
        if (userLogged == null){
            view.showProgress();
            Log.i("d5l","ra7 el presenter");
            getUserData(userLogin);
        }
        else {
            view.hideProgress();
            view.sendUserData(userLogged);
        }
    }



    private void getUserData (UserLogin userLogin ) {
        view.showProgress();
        Log.i("gaeluser",userLogin.getEmail());
        userLoginInter.sendLoginRequest(userLogin, new OnLoginResult() {
            @Override
            public void onSucess(User user) {
                Log.i("tmam",user.getFirstName());
                userLogged = user ;
                view.hideProgress();
                view.sendUserData(userLogged);
            }

            @Override
            public void onFailure() {
                Log.i("zft","fail");
                view.hideProgress();
                view.loginInvalid();
            }
        });


    }


    public  interface  LoginView{
        void showProgress();
        void hideProgress();
        void sendUserData(User user);
        void loginInvalid();
    }


}
