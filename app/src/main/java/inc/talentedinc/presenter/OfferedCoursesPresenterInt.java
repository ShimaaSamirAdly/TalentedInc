package inc.talentedinc.presenter;

import java.io.Serializable;
import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;

public interface OfferedCoursesPresenterInt extends Serializable {

    void fetchCourses();
    void notifyFragmentWithOfferedCourses(ArrayList<OfferedCourseDetailed> offeredCourses);
    void notifyFragmentWithError();
    void requestOfferedCourse(Integer offeredCourseId, Integer instructorId);
    void makeToastRequestResult(int result);
    void loadMoreOfferedCourses();
    void gotoDetailedCourseView(OfferedCourseDetailed offeredCourseDetailed);
}
