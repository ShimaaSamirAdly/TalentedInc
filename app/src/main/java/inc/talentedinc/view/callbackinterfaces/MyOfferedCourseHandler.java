package inc.talentedinc.view.callbackinterfaces;

import java.util.ArrayList;

import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;

public interface MyOfferedCourseHandler {
    void viewMyOfferedCourses(ArrayList<OfferedCourseDetailed> myOfferedCourses);

    void gotoCoursesRequestsActivity(OfferedCourseDetailed offeredCourseDetailed);
}
