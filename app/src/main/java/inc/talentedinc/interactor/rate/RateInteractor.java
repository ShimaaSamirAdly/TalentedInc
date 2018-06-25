package inc.talentedinc.interactor.rate;

import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.model.request.RateRequest;

/**
 * Created by asmaa on 05/23/2018.
 */

public interface RateInteractor {
    void setRate(RateRequest rateRequest , OnCommentLikeRateResult onCommentLikeRateResult);

}
