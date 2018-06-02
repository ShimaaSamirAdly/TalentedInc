package inc.talentedinc.presenter;

import java.util.ArrayList;

import inc.talentedinc.interactor.offeredcourse.OfferedCoursesFetcher;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.view.callbackinterfaces.EndlessScrollHandler;

public class OfferedCoursesPresenter implements OfferedCoursesPresenterInt {

    String BASE_URL = "https://f940191e-5b7a-4c0d-8e45-05b482b2e6e8.mock.pstmn.io/";
    EndlessScrollHandler endlessScrollHandler;

    public OfferedCoursesPresenter(EndlessScrollHandler endlessScrollHandler) {
        this.endlessScrollHandler = endlessScrollHandler;
    }

    @Override
    public void fetchCourses() {
        //endlessScrollHandler.showProgressBar();
        OfferedCoursesFetcher offeredCoursesFetcher = new OfferedCoursesFetcher(this);
        offeredCoursesFetcher.fetchCourses(BASE_URL);
    }

    @Override
    public void notifyFragmentWithOfferedCourses(ArrayList<OfferedCourse> offeredCourses) {
        //endlessScrollHandler.hideProgressBar();
        endlessScrollHandler.showData(offeredCourses);
    }

    @Override
    public void notifyFragmentWithError() {
        //endlessScrollHandler.hideProgressBar();
        endlessScrollHandler.makeErrorToast();
    }
}
