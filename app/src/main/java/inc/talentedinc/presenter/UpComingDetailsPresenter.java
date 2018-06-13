package inc.talentedinc.presenter;

import java.util.ArrayList;
import java.util.Date;

import inc.talentedinc.interactor.commentLike.CommentLikeInteractor;
import inc.talentedinc.interactor.register.RegisterInteractor;
import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.model.CourseComment;
import inc.talentedinc.model.Result;
import inc.talentedinc.model.response.BaseResponse;

/**
 * Created by asmaa on 05/21/2018.
 */

public class UpComingDetailsPresenter {

    private Result data = new Result();

    private ViewUpComingDetails view;
    private CommentLikeInteractor commentLikeInteractor;
    private RegisterInteractor registerInteractor;

    public UpComingDetailsPresenter(CommentLikeInteractor commentLikeInteractor,RegisterInteractor registerInteractor){
        this.commentLikeInteractor=commentLikeInteractor;
        this.registerInteractor=registerInteractor;
    }

    public void setView(Result data, ViewUpComingDetails view) {
        this.view=view;
        setDetailsData(data);
    }


    public void setDetailsData(Result data) {
        this.data = data;
        if (data!=null){
            view.setComments(data.getCourseComments());
            view.setCommentsNum(data.getNumberOfComments());
            view.setLikes(data.getNumberOfLikes());
            view.setCourseName(data.getName());
            view.setDescription(data.getDescription());
            view.setDuration(data.getDurationHours()+" Hours");
            view.setEndDate(data.getEndDate());
            view.setStartDate(data.getStartDate());
            view.setInstructorName(data.getNameOfInstructor());
            view.setWorkspaceName(data.getHostingWorkSpaceId().getName());
            view.setLocation(data.getHostingWorkSpaceId().getAddress());
            view.setIsLike(data.getLiked());
            view.setIsRegister(data.isRegistered());

        }
    }


    public void setComment(int userIid,int courseId,String courseDate , String comment){
        view.showProgress();
        commentLikeInteractor.setComment(userIid, courseId, courseDate,comment, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.hideProgress();
            }
            @Override
            public void onFailure() {
                view.showToast("Failure");
                view.hideProgress();
            }
        });

    }

    public void setLike(int userIid,int courseId,String courseDate){
        view.showProgress();
        commentLikeInteractor.setLike(userIid, courseId, courseDate, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.hideProgress();
            }

            @Override
            public void onFailure() {
                view.showToast("Failure");
                view.hideProgress();
            }
        });
    }

    public void disLike(int userIid,int courseId,String courseDate){
        view.showProgress();
        commentLikeInteractor.setDisLike(userIid, courseId, courseDate, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.hideProgress();
            }

            @Override
            public void onFailure() {
                view.showToast("Failure");
                view.hideProgress();


            }
        });

    }
    public void setRegister(int userIid,int courseId,String courseDate){
        view.showProgress();
        registerInteractor.setRegister(userIid, courseId, courseDate, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.hideProgress();
            }

            @Override
            public void onFailure() {
                view.showToast("Failure");
                view.hideProgress();


            }
        });

    }
    public void unRegister(int userIid,int courseId,String courseDate){
        view.showProgress();
        registerInteractor.unRegister(userIid, courseId, courseDate, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.hideProgress();
            }

            @Override
            public void onFailure() {
                view.showToast("Failure");
                view.hideProgress();


            }
        });

    }
    public interface ViewUpComingDetails {
        void showProgress();
        void hideProgress();
        void setCourseName(String name);
        void setWorkspaceName(String name);
        void setInstructorName(String name);
        void setLocation(String address);
        void setLikes(int likes);
        void setCommentsNum(int comments);
        void setDuration(String duration);
        void setStartDate(String startD);
        void setEndDate(String endD);
        void setDescription(String description);
        void setComments(ArrayList<CourseComment>comments);
        void showToast(String msg);
        void setIsLike(boolean isLike);
        void setIsRegister(boolean isRegister);
    }



}
