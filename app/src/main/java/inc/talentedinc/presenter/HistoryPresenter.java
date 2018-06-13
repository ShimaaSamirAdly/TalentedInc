package inc.talentedinc.presenter;

import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.interactor.commentLike.CommentLikeInteractor;
import inc.talentedinc.interactor.history.HistoryInteractor;
import inc.talentedinc.interactor.rate.RateInteractor;
import inc.talentedinc.interactor.upcoming.NetworkUpComingCoursesInteractor;
import inc.talentedinc.interactor.upcoming.UpComingCoursesInteractor;
import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.listener.OnCoursesResult;
import inc.talentedinc.model.Result;
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

    public void setView(int userId,int page,UpComingCoursesPresenter.ViewListener view) {
        this.view =view;

        Log.i("here ","here");
        if (data.size()== 0 ) {
            view.showProgress();
            getHomeData(userId,page);
            Log.i("here ","here2");
        } else {
            view.hideProgress();
            view.setData(data);
            Log.i("here ","here3");

        }
    }

    public void getHomeData(int userId,final int page){
        data.clear();
        view.showProgress();
       historyInteractor.getHistory(userId,page, new OnCoursesResult() {
            @Override
            public void onSuccess(ArrayList<Result> listData) {
                data.addAll(listData);
                view.hideProgress();
                if (data.size() ==0){
                    view.showNoDataAvailable();
                    view.hideProgress();
                }else{
                    if (page < NetworkUpComingCoursesInteractor.totalPage){
                        view.hideLoadingMoreData();
                        view.setData(listData);
                    }else {
                        view.hideLoadingMoreData();
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
        commentLikeInteractor.setComment(userIid, courseId, courseDate,comment, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
            }
            @Override
            public void onFailure() {
                view.showToast("Failure");
            }
        });
    }

    public void setLike(int userIid,int courseId,String courseDate){
        commentLikeInteractor.setLike(userIid, courseId, courseDate, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
            }

            @Override
            public void onFailure() {
                view.showToast("Failure");
            }
        });
    }

    public void setRate(int userIid,int courseId,String courseDate , float courseRate,float instructorRate ,float workSpaceRate ){
        rateInteractor.setRate(userIid, courseId, courseDate,courseRate,instructorRate,workSpaceRate, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
            }
            @Override
            public void onFailure() {
                view.showToast("Failure");

            }
        });
    }

    public void setDisLike(int userIid,int courseId,String courseDate){
        commentLikeInteractor.setDisLike(userIid, courseId, courseDate, new OnCommentLikeRateResult() {
            @Override
            public void onSuccess(BaseResponse response) {
                view.showToast(response.getStatus());
            }

            @Override
            public void onFailure() {
                view.showToast("Failure");
            }
        });

    }
}
