package inc.talentedinc.presenter;

import java.io.Serializable;
import java.util.ArrayList;

import inc.talentedinc.interactor.offeredcourse.OfferedCoursesFetcher;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.callbackinterfaces.EndlessScrollHandler;
import inc.talentedinc.view.callbackinterfaces.RequestListener;

public class OfferedCoursesPresenter implements OfferedCoursesPresenterInt, Serializable {

    private EndlessScrollHandler endlessScrollHandler;
    private OfferedCoursesFetcher offeredCoursesFetcher;
    private RequestListener requestListener;

    public OfferedCoursesPresenter() {
        initializeInteractor();
    }

    public OfferedCoursesPresenter(EndlessScrollHandler endlessScrollHandler) {
        this.endlessScrollHandler = endlessScrollHandler;
        initializeInteractor();
    }

    public OfferedCoursesPresenter(RequestListener requestListener) {
        this.requestListener = requestListener;
        initializeInteractor();
    }

    @Override
    public void fetchCourses(int instructorId) {
        offeredCoursesFetcher.resetFetcher();
        offeredCoursesFetcher.fetchCourses(0, instructorId);
    }

    @Override
    public void notifyFragmentWithOfferedCourses(ArrayList<OfferedCourseDetailed> offeredCourses) {
        endlessScrollHandler.showData(offeredCourses);
    }

    @Override
    public void notifyFragmentWithError() {
        endlessScrollHandler.makeErrorToast();
    }

    @Override
    public void requestOfferedCourse(Integer offeredCourseId, Integer instructorId ,int position) {
        offeredCoursesFetcher.requestCourse(offeredCourseId, instructorId , position);
    }

    @Override
    public void makeToastRequestResult(int result,int position) {
        if(endlessScrollHandler != null) {
            endlessScrollHandler.makeToastRequestResult(result, position);
        }else if (requestListener != null){
            requestListener.makeToastRequestResult(result,position);
        }
    }

    @Override
    public void loadMoreOfferedCourses(int instructorId) {
        offeredCoursesFetcher.fetchMoreCourses(instructorId);
    }

    @Override
    public void gotoDetailedCourseView(OfferedCourseDetailed offeredCourseDetailed) {
        endlessScrollHandler.gotoDetailedCourseView(offeredCourseDetailed);
    }

    @Override
    public void notifyDataFinished() {
        endlessScrollHandler.dataFinished();
    }

    @Override
    public void requestCanceled(int position) {
        if(endlessScrollHandler != null) {
            endlessScrollHandler.courseRequestCanceled(position);
        }else if(requestListener != null){
            requestListener.courseRequestCanceled(position);
        }
    }

    @Override
    public void errorCancelingRequest(int position) {
        if(endlessScrollHandler != null) {
            endlessScrollHandler.errorCancelingCopurse(position);
        }else if(requestListener != null){
            requestListener.errorCancelingCopurse(position);
        }
    }

    @Override
    public void cancelOfferedCourse(Integer offeredCourseId, Integer instructorId, int position) {
        offeredCoursesFetcher.cancelCourse(offeredCourseId,instructorId,position);
    }

    private void initializeInteractor() {
        offeredCoursesFetcher = OfferedCoursesFetcher.sharedInstance();
        offeredCoursesFetcher.setOfferedCoursesPresenterInt(this);
    }
}
