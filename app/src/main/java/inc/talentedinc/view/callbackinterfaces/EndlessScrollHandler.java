package inc.talentedinc.view.callbackinterfaces;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;

public interface EndlessScrollHandler {

    void showData(ArrayList<OfferedCourseDetailed> courses);
    void hideProgressBar();
    void showProgressBar();
    void makeErrorToast();

    void makeToastRequestResult(int result);
}
