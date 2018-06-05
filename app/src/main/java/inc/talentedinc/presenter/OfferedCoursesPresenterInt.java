package inc.talentedinc.presenter;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;

public interface OfferedCoursesPresenterInt {

    void fetchCourses();
    void notifyFragmentWithOfferedCourses(ArrayList<OfferedCourseDetailed> offeredCourses);
    void notifyFragmentWithError();
}
