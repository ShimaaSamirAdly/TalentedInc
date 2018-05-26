package inc.talentedinc.presenter;

import java.util.ArrayList;

import inc.talentedinc.model.Course;

public interface OfferedCoursesPresenterInt {

    void fetchCourses();
    void notifyFragmentWithOfferedCourses(ArrayList<Course> offeredCourses);
    void notifyFragmentWithError();
}
