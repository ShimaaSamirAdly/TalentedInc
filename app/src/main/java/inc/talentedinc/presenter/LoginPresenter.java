package inc.talentedinc.presenter;

import inc.talentedinc.interactor.login.UserLoginImpl;
import inc.talentedinc.interactor.login.UserLoginInter;
import inc.talentedinc.listener.OnLoginResult;
import inc.talentedinc.model.response.MainResponse;

/**
 * Created by Alaa on 5/23/2018.
 */

public class LoginPresenter {

    LoginView  view ;
   private UserLoginInter userLoginInter ;
   private MainResponse mainResponse = new MainResponse() ;

     public  LoginPresenter(){

        userLoginInter = new UserLoginImpl() ;
    }




    public void setView(String mail , String password , LoginView  view) {
        this.view =view;
        if (mainResponse == null){
            view.showProgress();
            getUserData(mail , password);
        }
        else {
            view.hideProgress();
            view.sendUserData(mainResponse);
        }
    }



    private void getUserData (final String mail , String  password ) {
        view.showProgress();
        userLoginInter.sendLoginRequest(mail, password, new OnLoginResult() {
            @Override
            public void onSucess(MainResponse response) {

                mainResponse = response ;
                view.hideProgress();
                view.sendUserData(mainResponse);
            }

            @Override
            public void onFailure() {
                view.hideProgress();
                view.loginInvalid();
            }
        });


    }


    public  interface  LoginView{
        void showProgress();
        void hideProgress();
        void sendUserData(MainResponse response);
        void loginInvalid();
    }


}
