package inc.talentedinc.presenter;

import java.util.ArrayList;

import inc.talentedinc.interactor.offeredcourse.OfferedCoursesFetcher;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.view.callbackinterfaces.MyOfferedCourseHandler;

public class MyOfferedCoursePresenter {

    private MyOfferedCourseHandler myOfferedCourseHandler;
    private OfferedCoursesFetcher offeredCoursesFetcher;

    public MyOfferedCoursePresenter(MyOfferedCourseHandler myOfferedCourseHandler) {
        this.myOfferedCourseHandler = myOfferedCourseHandler;
        offeredCoursesFetcher = OfferedCoursesFetcher.sharedInstance();
        offeredCoursesFetcher.setMyOfferedCoursePresenter(this);
    }

    public void fetchCourses(int instructorId) {
        offeredCoursesFetcher.fetchMyOfferedCourses(instructorId);
    }

    public void notifyMyOfferedCoursesFetched(ArrayList<OfferedCourseDetailed> body) {
        myOfferedCourseHandler.viewMyOfferedCourses(body);
    }

    public void gotoDetailedCourseView(OfferedCourseDetailed offeredCourseDetailed) {
        myOfferedCourseHandler.gotoCoursesRequestsActivity(offeredCourseDetailed);
    }
}
