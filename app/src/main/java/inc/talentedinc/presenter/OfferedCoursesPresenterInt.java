package inc.talentedinc.presenter;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;

public interface OfferedCoursesPresenterInt {

    void fetchCourses();
    void notifyFragmentWithOfferedCourses(ArrayList<OfferedCourse> offeredCourses);
    void notifyFragmentWithError();
}
