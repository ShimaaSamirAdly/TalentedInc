package inc.talentedinc.interactor.upcoming;

import inc.talentedinc.listener.OnCoursesResult;

/**
 * Created by asmaa on 05/21/2018.
 */

public interface UpComingCoursesInteractor {
    void getUpComingCourses(int userId,int page , OnCoursesResult onCoursesResult);
    void getSearchByName(int userId,String keyword,int page , OnCoursesResult onCoursesResult);
    void getSearchByFilter(int userId,String category,String city,int page , OnCoursesResult onCoursesResult);


}
