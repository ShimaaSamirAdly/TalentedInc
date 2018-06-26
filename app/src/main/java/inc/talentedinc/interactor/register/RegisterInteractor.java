package inc.talentedinc.interactor.register;

import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.model.request.MainRequest;

/**
 * Created by asmaa on 06/07/2018.
 */

public interface RegisterInteractor {
    void setRegister(MainRequest mainRequest, OnCommentLikeRateResult onCommentLikeRateResult);
    void unRegister(int userIid,int courseId,String courseDate, OnCommentLikeRateResult onCommentLikeRateResult);


}
