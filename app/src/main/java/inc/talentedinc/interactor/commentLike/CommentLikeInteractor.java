package inc.talentedinc.interactor.commentLike;

import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.model.request.CommentRequest;
import inc.talentedinc.model.request.MainRequest;

/**
 * Created by asmaa on 05/23/2018.
 */

public interface CommentLikeInteractor {
    void setLike(MainRequest mainRequest, OnCommentLikeRateResult onCommentLikeRateResult);
    void setDisLike(int userIid,int courseId,String courseDate,OnCommentLikeRateResult onCommentLikeRateResult);

    void setComment(CommentRequest commentRequest, OnCommentLikeRateResult onCommentLikeRateResult);

}
