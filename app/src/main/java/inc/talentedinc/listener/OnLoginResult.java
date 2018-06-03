package inc.talentedinc.listener;

import inc.talentedinc.model.User;
import inc.talentedinc.model.response.MainResponse;

/**
 * Created by Alaa on 5/23/2018.
 */

public interface OnLoginResult {
    void onSucess(User user);
    void onFailure();
}
