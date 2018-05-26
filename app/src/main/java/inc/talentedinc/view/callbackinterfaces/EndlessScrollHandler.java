package inc.talentedinc.view.callbackinterfaces;

import java.util.ArrayList;

import inc.talentedinc.model.Course;

public interface EndlessScrollHandler {

    void showData(ArrayList<Course> courses);
    void hideProgressBar();
    void showProgressBar();
    void makeErrorToast();

}
