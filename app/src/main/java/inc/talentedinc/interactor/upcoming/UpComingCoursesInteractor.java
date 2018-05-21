package inc.talentedinc.interactor.upcoming;

import inc.talentedinc.listener.OnCoursesResult;

/**
 * Created by asmaa on 05/21/2018.
 */

public interface UpComingCoursesInteractor {
    void getUpComingCourses(int page , OnCoursesResult onCoursesResult);

}
