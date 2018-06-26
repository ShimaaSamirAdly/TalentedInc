package inc.talentedinc.presenter;

import java.util.ArrayList;

import inc.talentedinc.interactor.commentLike.CommentLikeInteractor;
import inc.talentedinc.interactor.rate.RateInteractor;
import inc.talentedinc.interactor.register.RegisterInteractor;
import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.model.CourseComment;
import inc.talentedinc.model.Result;
import inc.talentedinc.model.request.CommentRequest;
import inc.talentedinc.model.request.MainRequest;
import inc.talentedinc.model.request.RateRequest;
import inc.talentedinc.model.response.BaseResponse;

/**
 * Created by asmaa on 05/21/2018.
 */

public class UpComingDetailsPresenter {

    private Result data = new Result();

    private ViewUpComingDetails view;
    private CommentLikeInteractor commentLikeInteractor;
    private RegisterInteractor registerInteractor;
    private RateInteractor rateInteractor;

    public UpComingDetailsPresenter(CommentLikeInteractor commentLikeInteractor,RegisterInteractor registerInteractor,RateInteractor rateInteractor){
        this.commentLikeInteractor=commentLikeInteractor;
        this.registerInteractor=registerInteractor;
        this.rateInteractor=rateInteractor;
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
            view.setWorkspaceName(data.getWorkSpaceName());
            view.setLocation(data.getWorkSpaceAddress());
            view.setIsLike(data.getLiked());
            view.setIsRegister(data.getRegistered());
            view.setIsRate(data.getRated());
            view.setCourseImage(data.getImageUrl());
        }
    }


    public void setComment(int userIid,int courseId,String courseDate , String comment){
        view.showProgress();
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setUserId(userIid);
        commentRequest.setCourseId(courseId);
        commentRequest.setCourseDate(courseDate);
        commentRequest.setComment(comment);
        commentLikeInteractor.setComment(commentRequest, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.hideProgress();
                view.setCommentResult();

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
        MainRequest mainRequest= new MainRequest();
        mainRequest.setUserId(userIid);
        mainRequest.setCourseId(courseId);
        mainRequest.setCourseDate(courseDate);
        commentLikeInteractor.setLike(mainRequest, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.hideProgress();
                view.setLikeResult();

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
        commentLikeInteractor.setDisLike(userIid,courseId,courseDate, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.hideProgress();
                view.setDisLikeResult();
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
        MainRequest mainRequest= new MainRequest();
        mainRequest.setUserId(userIid);
        mainRequest.setCourseId(courseId);
        mainRequest.setCourseDate(courseDate);
        registerInteractor.setRegister(mainRequest, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.hideProgress();
                view.setRegisterResult();

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
        registerInteractor.unRegister(userIid,courseId,courseDate,new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.hideProgress();
                view.setUnRegisterResult();
            }

            @Override
            public void onFailure() {
                view.showToast("Failure");
                view.hideProgress();
            }
        });

    }

    public void setRate(int userIid,int courseId,String courseDate , float courseRate,float instructorRate ,float workSpaceRate ){
        RateRequest rateRequest = new RateRequest();
        rateRequest.setUserId(userIid);
        rateRequest.setCourseId(courseId);
        rateRequest.setCourseDate(courseDate);
        rateRequest.setCourseRate((int) courseRate);
        rateRequest.setWorkSpaceRate((int) workSpaceRate);
        rateRequest.setInstructorRate((int) instructorRate);
        rateInteractor.setRate(rateRequest, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.setRateResult();
            }
            @Override
            public void onFailure() {
                view.showToast("Failure");

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
        void setRegisterResult();
        void setUnRegisterResult();
        void setIsRate(boolean isRate);
        void setRateResult();
        void setLikeResult();
        void setDisLikeResult();
        void setCommentResult();
        void setCourseImage(String image);

    }

}
