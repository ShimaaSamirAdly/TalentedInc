package inc.talentedinc.listener;

import inc.talentedinc.model.Result;

/**
 * Created by asmaa on 05/21/2018.
 */

public interface HomeListener {
    void onCourseClicked(Result result);
    void onRateClick(int courseId , String courseDate);
    void onLikeClick(int courseId , String courseDate, int position);
    void onDisLikeClick(int courseId , String courseDate , int position);
    void onCommentClick(int courseId , String courseDate ,int position);
    void onInstructorClick(int instracturId);

}
