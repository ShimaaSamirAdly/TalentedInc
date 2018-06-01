package inc.talentedinc.view.callbackinterfaces;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;

public interface EndlessScrollHandler {

    void showData(ArrayList<OfferedCourse> courses);
    void hideProgressBar();
    void showProgressBar();
    void makeErrorToast();

}
