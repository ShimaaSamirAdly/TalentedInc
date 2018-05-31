package inc.talentedinc.presenter;

import java.util.ArrayList;

import inc.talentedinc.interactor.offeredcourse.OfferedCoursesFetcher;
import inc.talentedinc.model.Course;
import inc.talentedinc.view.callbackinterfaces.EndlessScrollHandler;

public class OfferedCoursesPresenter implements OfferedCoursesPresenterInt {

    String BASE_URL = "https://c1b268ef-f1b9-44c3-8bd1-38ae8a2bd213.mock.pstmn.io/";
    EndlessScrollHandler endlessScrollHandler;

    public OfferedCoursesPresenter(EndlessScrollHandler endlessScrollHandler) {
        this.endlessScrollHandler = endlessScrollHandler;
    }

    @Override
    public void fetchCourses() {
        endlessScrollHandler.showProgressBar();
        OfferedCoursesFetcher offeredCoursesFetcher = new OfferedCoursesFetcher(this);
        offeredCoursesFetcher.fetchCourses(BASE_URL);
    }

    @Override
    public void notifyFragmentWithOfferedCourses(ArrayList<Course> offeredCourses) {
        endlessScrollHandler.hideProgressBar();
        endlessScrollHandler.showData(offeredCourses);
    }

    @Override
    public void notifyFragmentWithError() {
        endlessScrollHandler.hideProgressBar();
        endlessScrollHandler.makeErrorToast();
    }
}
