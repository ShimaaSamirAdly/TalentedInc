package inc.talentedinc.interactor.commentLike;

import inc.talentedinc.listener.OnCommentLikeRateResult;

/**
 * Created by asmaa on 05/23/2018.
 */

public interface CommentLikeInteractor {
    void setLike(int userIid,int courseId,String courseDate,OnCommentLikeRateResult onCommentLikeRateResult);
    void setDisLike(int userIid,int courseId,String courseDate,OnCommentLikeRateResult onCommentLikeRateResult);

    void setComment(int userIid,int courseId,String courseDate, String comment , OnCommentLikeRateResult onCommentLikeRateResult);

}
