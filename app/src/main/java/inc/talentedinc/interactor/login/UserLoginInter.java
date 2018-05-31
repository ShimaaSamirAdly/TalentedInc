package inc.talentedinc.interactor.login;

import inc.talentedinc.listener.OnLoginResult;

/**
 * Created by Alaa on 5/23/2018.
 */

public interface UserLoginInter {

     void sendLoginRequest(String email, String password, OnLoginResult onresult);

}
