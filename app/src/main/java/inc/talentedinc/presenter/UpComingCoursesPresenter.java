package inc.talentedinc.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import inc.talentedinc.interactor.categories.CategoriesInteractor;
import inc.talentedinc.interactor.commentLike.CommentLikeInteractor;
import inc.talentedinc.interactor.upcoming.NetworkUpComingCoursesInteractor;
import inc.talentedinc.interactor.upcoming.UpComingCoursesInteractor;
import inc.talentedinc.listener.CategoriesListener;
import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.listener.OnCoursesResult;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.Result;
import inc.talentedinc.model.response.BaseResponse;

/**
 * Created by asmaa on 05/21/2018.
 */

public class UpComingCoursesPresenter {

    private ArrayList<Result> data = new ArrayList<>();

    private UpComingCoursesInteractor upComingCoursesInteractor;
    private CommentLikeInteractor commentLikeInteractor;
    ViewListener view;
    private CategoriesInteractor categoriesInteractor;

    public UpComingCoursesPresenter(UpComingCoursesInteractor upComingCoursesInteractor , CommentLikeInteractor commentLikeInteractor,CategoriesInteractor categoriesInteractor){
        this.upComingCoursesInteractor=upComingCoursesInteractor;
        this.commentLikeInteractor=commentLikeInteractor;
        this.categoriesInteractor= categoriesInteractor;
    }

    public void setView(int userId,int page,ViewListener view) {
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
        upComingCoursesInteractor.getUpComingCourses(userId,page, new OnCoursesResult() {
            @Override
            public void onSuccess(ArrayList<Result> listData) {
                data.addAll(listData);
                view.hideProgress();
                if (data.size() ==0){
                    view.showNoDataAvailable();
                }else{
                    if (page < NetworkUpComingCoursesInteractor.totalPage - 1){
                        view.showLoadingMoreData();
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

    public void getSearchByName(int userId,String keyword,final int page) {
        data.clear();
        view.showProgress();
        upComingCoursesInteractor.getSearchByName(userId,keyword, page, new OnCoursesResult() {
            @Override
            public void onSuccess(ArrayList<Result> listData) {
                data.addAll(listData);
                view.hideProgress();
                if (data.size() == 0) {
                    view.showNoDataAvailable();
                    view.hideProgress();
                } else {
                    if (page < NetworkUpComingCoursesInteractor.totalPage - 1) {
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


    public void getSearchByFilter(int userId,String category,String city ,final int page) {
        data.clear();
        view.showProgress();
        upComingCoursesInteractor.getSearchByFilter(userId,category,city, page, new OnCoursesResult() {
            @Override
            public void onSuccess(ArrayList<Result> listData) {
                data.addAll(listData);
                view.hideProgress();
                if (data.size() == 0) {
                    view.showNoDataAvailable();
                    view.hideProgress();
                } else {
                    if (page < NetworkUpComingCoursesInteractor.totalPage - 1) {
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

    public void getCategoties(){
        view.showProgress();
        categoriesInteractor.getAllCategories(new CategoriesListener() {
            @Override
            public void onSuccess(List<Categories> categoriesList) {
                view.hideProgress();
                view.setCategories(categoriesList);
            }

            @Override
            public void onFailed() {
                view.showCategoriesError("categories >> Failure");

            }

            @Override
            public void onFailedConnection() {

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

    public interface ViewListener {
        void showProgress();
        void hideProgress();
        void showLoadingMoreData();
        void hideLoadingMoreData();
        void showNoDataAvailable();
        void setData(ArrayList<Result> listData);
        void setDataSearchName(ArrayList<Result> listData);
        void setDataSearchFilter(ArrayList<Result> listData);
        void setCategories(List<Categories> listData);
        void errorMsg();
        void showCategoriesError(String msg);
        void showToast(String s);
    }

}
