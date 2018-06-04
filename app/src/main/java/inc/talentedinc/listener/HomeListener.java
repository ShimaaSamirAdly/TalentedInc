package inc.talentedinc.listener;

import inc.talentedinc.model.Result;

/**
 * Created by asmaa on 05/21/2018.
 */

public interface HomeListener {
    void onCourseClicked(Result result);
    void onRateClick();
    void onLikeClick();
    void onCommentClick();
    void onInstructorClick(int instracturId);

}
