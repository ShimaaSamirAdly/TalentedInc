package inc.talentedinc.interactor.rate;

import inc.talentedinc.listener.OnCommentLikeRateResult;

/**
 * Created by asmaa on 05/23/2018.
 */

public interface RateInteractor {
    void setRate(int userIid,int courseId,String courseDate, float courseRate,float instructorRate ,float workSpaceRate , OnCommentLikeRateResult onCommentLikeRateResult);

}
