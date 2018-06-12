package inc.talentedinc.presenter;

import java.io.Serializable;
import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;

public interface OfferedCoursesPresenterInt extends Serializable {

    void fetchCourses(int instructorId);
    void notifyFragmentWithOfferedCourses(ArrayList<OfferedCourseDetailed> offeredCourses);
    void notifyFragmentWithError();
    void requestOfferedCourse(Integer offeredCourseId, Integer instructorId, int position);
    void makeToastRequestResult(int result,int position);
    void loadMoreOfferedCourses(int instructorId);
    void gotoDetailedCourseView(OfferedCourseDetailed offeredCourseDetailed);

    void notifyDataFinished();

    void requestCanceled(int position);

    void errorCancelingRequest(int position);

    void cancelOfferedCourse(Integer offeredCourseId, Integer instructorId, int position);
}
