package inc.talentedinc.presenter;

import java.util.ArrayList;

import inc.talentedinc.interactor.offeredcourse.OfferedCoursesFetcher;
import inc.talentedinc.model.offeredcourse.OfferedCourseWorkspace;
import inc.talentedinc.view.activities.MyOfferedCoursesRequestsActivity;
import inc.talentedinc.view.callbackinterfaces.RequestsHandler;

public class RequestsPresenter {

    private RequestsHandler myRequestsHAndler;
    private OfferedCoursesFetcher offeredCoursesFetcher;

    public RequestsPresenter(RequestsHandler myRequestsHAndler) {
        this.myRequestsHAndler = myRequestsHAndler;
        offeredCoursesFetcher = OfferedCoursesFetcher.sharedInstance();
        offeredCoursesFetcher.setRequestsPresenter(this);
    }


    public void fetchCourseRequests(Integer offeredCourseId) {
        offeredCoursesFetcher.getCourseRequests(offeredCourseId);
    }

    public void notifyWithRequestsWorkspaces(ArrayList<OfferedCourseWorkspace> offeredCourseWorkspaces) {
        myRequestsHAndler.viewRequests(offeredCourseWorkspaces);
    }

    public void acceptWorkspace(int courseId, Integer workSpaceId) {
        offeredCoursesFetcher.acceptCourse(courseId,workSpaceId);
    }

    public void worSpaceAccepted() {
        myRequestsHAndler.workspaceAcceptedSuccessfully();
    }

    public void gotoWorkspace(Integer workSpaceId) {
        myRequestsHAndler.gotoWorkspaceProfile(workSpaceId);
    }
}
