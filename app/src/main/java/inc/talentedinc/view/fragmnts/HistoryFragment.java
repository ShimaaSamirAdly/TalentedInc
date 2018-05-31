package inc.talentedinc.view.fragmnts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rey.material.widget.ProgressView;

import java.io.Serializable;
import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.adapter.UpcomingCoursesAdapter;
import inc.talentedinc.factory.Factory;
import inc.talentedinc.interactor.upcoming.NetworkUpComingCoursesInteractor;
import inc.talentedinc.listener.HomeListener;
import inc.talentedinc.model.Result;
import inc.talentedinc.presenter.HistoryPresenter;
import inc.talentedinc.presenter.UpComingCoursesPresenter;
import inc.talentedinc.utilitis.ActionUtils;
import inc.talentedinc.utilitis.EndlessRecyclerOnScrollListener;
import inc.talentedinc.view.activities.UpComingDetailsActivity;


public class HistoryFragment extends Fragment implements UpComingCoursesPresenter.ViewListener, HomeListener {

    /****************************** asmaa *************************/
    private RecyclerView recyclerView;
    private int page=1;
    private boolean isLoading = false;
    private boolean moreDataAvailable = true;

    private HistoryPresenter presenter;
    private UpcomingCoursesAdapter upcomingCoursesAdapter;
    private ArrayList<Result> dataResult = new ArrayList<>();
    private ProgressView progressView;


    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming_courses, container, false);
        initView(view);
        return view;
    }
    /****************************** *************************/

    /****************************** asmaa *************************/

    private void initView(View v){

        recyclerView= v.findViewById(R.id.my_recycler_view);
        progressView=v.findViewById(R.id.pv_load);
        presenter = new HistoryPresenter(Factory.provideHistory());
//        if (ActionUtils.isInternetConnected(getActivity())) {
            presenter.setView(page, this);
//        }else{
//            ActionUtils.showToast(getActivity(), "Connection Error");
//        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        upcomingCoursesAdapter = new UpcomingCoursesAdapter(gridLayoutManager,this);
        upcomingCoursesAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(upcomingCoursesAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager/*recyclerView.getLayoutManager()*/) {
            @Override
            public void onLoadMore() {
                if (!isLoading && moreDataAvailable ) {
                    isLoading = true;
                    loadMoreData();
                }
            }
        });

    }

    private void loadMoreData() {
        upcomingCoursesAdapter.setLoading(true);
        presenter.getHomeData(page);
    }


    @Override
    public void showProgress() {
        isLoading = true;
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        isLoading = false;
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingMoreData() {
        upcomingCoursesAdapter.setLoading(true);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingMoreData() {
        upcomingCoursesAdapter.setLoading(false);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataAvailable() {

    }

    @Override
    public void setData(ArrayList<Result> listData) {
        isLoading=false;
        if (listData.size() > 0){
            dataResult.addAll(listData);
            if (page == NetworkUpComingCoursesInteractor.totalPage){
                moreDataAvailable =false;
            }else {
                page++;
            }
        }
        upcomingCoursesAdapter.setData(dataResult);
    }

    @Override
    public void errorMsg() {
        ActionUtils.showToast(getActivity(), "API error");
    }

    @Override
    public void onCourseClicked(Result result) {
        Intent switchToDetails = new Intent(getActivity(),UpComingDetailsActivity.class);
        switchToDetails.putExtra(UpComingDetailsActivity.COURSE, (Serializable)  result);
        startActivity(switchToDetails );
    }

    /****************************** *************************/


}
