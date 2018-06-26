package inc.talentedinc.presenter;

import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.interactor.commentLike.CommentLikeInteractor;
import inc.talentedinc.interactor.history.HistoryInteractor;
import inc.talentedinc.interactor.history.NetworkHistoryInteractor;
import inc.talentedinc.interactor.rate.RateInteractor;
import inc.talentedinc.interactor.upcoming.NetworkUpComingCoursesInteractor;
import inc.talentedinc.interactor.upcoming.UpComingCoursesInteractor;
import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.listener.OnCoursesResult;
import inc.talentedinc.model.Result;
import inc.talentedinc.model.request.CommentRequest;
import inc.talentedinc.model.request.MainRequest;
import inc.talentedinc.model.request.RateRequest;
import inc.talentedinc.model.response.BaseResponse;

/**
 * Created by asmaa on 05/23/2018.
 */

public class HistoryPresenter {
    private ArrayList<Result> data = new ArrayList<>();

    private HistoryInteractor historyInteractor;
    UpComingCoursesPresenter.ViewListener view;
    private CommentLikeInteractor commentLikeInteractor;
    private RateInteractor rateInteractor;

    public HistoryPresenter(HistoryInteractor historyInteractor,CommentLikeInteractor commentLikeInteractor,RateInteractor rateInteractor){
        this.historyInteractor=historyInteractor;
        this.commentLikeInteractor=commentLikeInteractor;
        this.rateInteractor=rateInteractor;
    }

    public void setView(String key,int userId,int page,UpComingCoursesPresenter.ViewListener view) {
        this.view =view;

        Log.i("here ","here");
        if (data.size()== 0 ) {
            view.showProgress();
            if (key.equals("history"))
            getHomeData(userId,page);
            else
                getRegister(userId,page);
            Log.i("here ","here2");
        } else {
            view.hideProgress();
            view.setData(data);
            Log.i("here ","here3");

        }
    }

    public void getHomeData(int userId,final int page){
        data.clear();
      //  view.showProgress();
       historyInteractor.getHistory(userId,page, new OnCoursesResult() {
            @Override
            public void onSuccess(ArrayList<Result> listData) {
                data.addAll(listData);
                view.hideProgress();
                if (data.size() == 0) {
                    view.showNoDataAvailable();
                    view.hideProgress();
                } else {
                    if (page < NetworkHistoryInteractor.totalPage - 1) {
                        view.showLoadingMoreData();
                        view.hideProgress();
                        view.setData(listData);
                    } else {
                        view.hideLoadingMoreData();
                        view.hideProgress();
                        view.setData(listData);
                    }
                }
            }
            @Override
            public void onFailure() {
                view.hideProgress();
                view.errorMsg();
            }
        });
    }


    public void getRegister(int userId,final int page){
        data.clear();
       // view.showProgress();
        historyInteractor.getRegister(userId,page, new OnCoursesResult() {
            @Override
            public void onSuccess(ArrayList<Result> listData) {
                data.addAll(listData);
                view.hideProgress();
                if (data.size() == 0) {
                    view.showNoDataAvailable();
                    view.hideProgress();
                } else {
                    if (page < NetworkHistoryInteractor.totalPage - 1) {
                         view.showLoadingMoreData();
                        view.hideProgress();
                        view.setData(listData);
                    } else {
                        view.hideLoadingMoreData();
                        view.hideProgress();
                        view.setData(listData);
                    }
                }
            }
            @Override
            public void onFailure() {
                view.hideProgress();
                view.errorMsg();
            }
        });
    }

    public void setComment(int userIid,int courseId,String courseDate , String comment){
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setUserId(userIid);
        commentRequest.setCourseId(courseId);
        commentRequest.setCourseDate(courseDate);
        commentRequest.setComment(comment);
        commentLikeInteractor.setComment(commentRequest, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.setCommentResult();
            }
            @Override
            public void onFailure() {
                view.showToast("Failure");
            }
        });
    }

    public void setLike(int userIid,int courseId,String courseDate){
        MainRequest mainRequest= new MainRequest();
        mainRequest.setUserId(userIid);
        mainRequest.setCourseId(courseId);
        mainRequest.setCourseDate(courseDate);
        commentLikeInteractor.setLike(mainRequest, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.setLikeResult();
            }

            @Override
            public void onFailure() {
                view.showToast("Failure");
            }
        });
    }

    public void setRate(int userIid,int courseId,String courseDate , float courseRate,float instructorRate ,float workSpaceRate ){
        RateRequest rateRequest = new RateRequest();
        rateRequest.setUserId(userIid);
        rateRequest.setCourseId(courseId);
        rateRequest.setCourseDate(courseDate);

        rateRequest.setCourseRate((int)courseRate);
        rateRequest.setWorkSpaceRate((int)workSpaceRate);
        rateRequest.setInstructorRate((int)instructorRate);
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

    public void setDisLike(int userIid,int courseId,String courseDate){
        commentLikeInteractor.setDisLike(userIid,courseId,courseDate, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
                view.setDisLikeResult();
            }

            @Override
            public void onFailure() {
                view.showToast("Failure");
            }
        });

    }
}
