package inc.talentedinc.presenter;

import java.io.Serializable;
import java.util.ArrayList;

import inc.talentedinc.interactor.offeredcourse.OfferedCoursesFetcher;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.view.callbackinterfaces.EndlessScrollHandler;

public class OfferedCoursesPresenter implements OfferedCoursesPresenterInt, Serializable {

    String BASE_URL = "https://f940191e-5b7a-4c0d-8e45-05b482b2e6e8.mock.pstmn.io/";
    EndlessScrollHandler endlessScrollHandler;
    OfferedCoursesFetcher offeredCoursesFetcher;

    public OfferedCoursesPresenter(EndlessScrollHandler endlessScrollHandler) {
        this.endlessScrollHandler = endlessScrollHandler;
    }

    @Override
    public void fetchCourses() {
        //endlessScrollHandler.showProgressBar();
        offeredCoursesFetcher = new OfferedCoursesFetcher(this);
        offeredCoursesFetcher.fetchCourses(0);
    }

    @Override
    public void notifyFragmentWithOfferedCourses(ArrayList<OfferedCourseDetailed> offeredCourses) {
        //endlessScrollHandler.hideProgressBar();
        endlessScrollHandler.showData(offeredCourses);
    }

    @Override
    public void notifyFragmentWithError() {
        //endlessScrollHandler.hideProgressBar();
        endlessScrollHandler.makeErrorToast();
    }

    @Override
    public void requestOfferedCourse(Integer offeredCourseId, Integer instructorId) {
        offeredCoursesFetcher.requestCourse(offeredCourseId,instructorId);
    }

    @Override
    public void makeToastRequestResult(int result) {
        endlessScrollHandler.makeToastRequestResult(result);
    }

    @Override
    public void loadMoreOfferedCourses() {
        offeredCoursesFetcher.fetchMoreCourses();
    }

    @Override
    public void gotoDetailedCourseView(OfferedCourseDetailed offeredCourseDetailed) {
        endlessScrollHandler.gotoDetailedCourseView(offeredCourseDetailed);
    }
}
