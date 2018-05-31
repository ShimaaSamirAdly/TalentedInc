package inc.talentedinc.presenter;

import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.interactor.history.HistoryInteractor;
import inc.talentedinc.interactor.upcoming.NetworkUpComingCoursesInteractor;
import inc.talentedinc.interactor.upcoming.UpComingCoursesInteractor;
import inc.talentedinc.listener.OnCoursesResult;
import inc.talentedinc.model.Result;

/**
 * Created by asmaa on 05/23/2018.
 */

public class HistoryPresenter {
    private ArrayList<Result> data = new ArrayList<>();

    private HistoryInteractor historyInteractor;
    UpComingCoursesPresenter.ViewListener view;

    public HistoryPresenter(HistoryInteractor historyInteractor){
        this.historyInteractor=historyInteractor;
    }

    public void setView(int page,UpComingCoursesPresenter.ViewListener view) {
        this.view =view;

        Log.i("here ","here");
        if (data.size()== 0 ) {
            view.showProgress();
            getHomeData(page);
            Log.i("here ","here2");
        } else {
            view.hideProgress();
            view.setData(data);
            Log.i("here ","here3");

        }
    }

    public void getHomeData(final int page){
        data.clear();
        view.showProgress();
       historyInteractor.getHistory(page, new OnCoursesResult() {
            @Override
            public void onSuccess(ArrayList<Result> listData) {
                data.addAll(listData);
                view.hideProgress();
                if (data.size() ==0){
                    view.showNoDataAvailable();
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
}
