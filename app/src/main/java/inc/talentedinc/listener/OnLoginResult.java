package inc.talentedinc.listener;

import inc.talentedinc.model.response.MainResponse;

/**
 * Created by Alaa on 5/23/2018.
 */

public interface OnLoginResult {
    void onSucess(MainResponse response);
    void onFailure();
}
