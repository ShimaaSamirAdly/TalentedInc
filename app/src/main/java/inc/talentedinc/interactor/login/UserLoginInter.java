package inc.talentedinc.interactor.login;

import inc.talentedinc.listener.OnLoginResult;
import inc.talentedinc.model.UserLogin;

/**
 * Created by Alaa on 5/23/2018.
 */

public interface UserLoginInter {

     void sendLoginRequest(UserLogin userLogin, OnLoginResult onresult);

}
