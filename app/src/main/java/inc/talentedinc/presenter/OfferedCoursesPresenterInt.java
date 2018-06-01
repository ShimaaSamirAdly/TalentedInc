package inc.talentedinc.presenter;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;

public interface OfferedCoursesPresenterInt {

    void fetchCourses();
    void notifyFragmentWithOfferedCourses(ArrayList<MinaCourse> offeredCourses);
    void notifyFragmentWithError();
}
