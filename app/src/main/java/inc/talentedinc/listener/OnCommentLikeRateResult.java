package inc.talentedinc.listener;

import java.util.ArrayList;

import inc.talentedinc.model.Result;
import inc.talentedinc.model.response.BaseResponse;

/**
 * Created by asmaa on 06/04/2018.
 */

public interface OnCommentLikeRateResult {
    void onSuccess(BaseResponse response);
    void onFailure();
}
