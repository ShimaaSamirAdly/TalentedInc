package inc.talentedinc.listener;

import inc.talentedinc.model.response.CreateCourseResponse;

/**
 * Created by Alaa on 6/1/2018.
 */

public interface OnCreateCourse {
     void onSuccess(CreateCourseResponse object);
     void onFail();

}
