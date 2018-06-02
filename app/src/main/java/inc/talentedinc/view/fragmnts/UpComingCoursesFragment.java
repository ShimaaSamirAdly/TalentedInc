package inc.talentedinc.view.fragmnts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.rey.material.widget.ProgressView;
import java.io.Serializable;
import java.util.ArrayList;
import inc.talentedinc.R;
import inc.talentedinc.adapter.HomeAdapter;
import inc.talentedinc.factory.Factory;
import inc.talentedinc.interactor.upcoming.NetworkUpComingCoursesInteractor;
import inc.talentedinc.listener.HomeListener;
import inc.talentedinc.model.Result;
import inc.talentedinc.presenter.UpComingCoursesPresenter;
import inc.talentedinc.utilitis.ValidationUtility;
import inc.talentedinc.view.activities.UpComingDetailsActivity;
import inc.talentedinc.utilitis.ActionUtils;
import inc.talentedinc.utilitis.EndlessRecyclerOnScrollListener;

public class UpComingCoursesFragment extends Fragment implements UpComingCoursesPresenter.ViewListener ,HomeListener, View.OnClickListener {

    /****************************** asmaa *************************/

    private RecyclerView recyclerView;
    private int page=1;
    private boolean isLoading = false;
    private boolean moreDataAvailable = true;

    private UpComingCoursesPresenter presenter;
    private HomeAdapter upcomingCoursesAdapter;
    private ArrayList<Result> dataResult = new ArrayList<>();
    private ProgressView progressView;
    private EditText edSearh;
    private ImageView imgBg;
    private AlertDialog commentDialog ,filterDialog;
    private ImageView imgFilter;


    public UpComingCoursesFragment() {
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

        // hide keyboard when launch screen
        imgFilter= v.findViewById(R.id.imgFilter);
        imgFilter.setOnClickListener(this);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        imgBg = v.findViewById(R.id.imgBg);
        edSearh = v.findViewById(R.id.txtSearch);
        edSearh.setOnClickListener(this);

        recyclerView= v.findViewById(R.id.my_recycler_view);
        progressView=v.findViewById(R.id.pv_load);
        presenter = new UpComingCoursesPresenter(Factory.provideUpComing());

        presenter.setView(page, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        upcomingCoursesAdapter = new HomeAdapter(HomeAdapter.UPCOMING,gridLayoutManager,this);
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


    private void commentDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View dialogView = this.getLayoutInflater().inflate(R.layout.custom_comment_dialog, null);
        builder.setView(dialogView);

        commentDialog = builder.create();
        if (dialogView != null) {
            final EditText etComment= dialogView.findViewById(R.id.etComment);
            Button commentBtn = dialogView.findViewById(R.id.btnComment);
            Button cancelBtn = dialogView.findViewById(R.id.btnCancel);
            commentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ValidationUtility.validateEmptyString(etComment.getText().toString())){
                        ///// presenter
                    }

                }
            });

           cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Hide keyboard
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    // dismiss dialog
                    commentDialog.dismiss();
                }
            });
        }
        commentDialog.show();
    }

    private void filterDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View dialogView = this.getLayoutInflater().inflate(R.layout.custom_filter_dialog, null);
        builder.setView(dialogView);

        filterDialog = builder.create();
        if (dialogView != null) {

            Button cancelBtn = dialogView.findViewById(R.id.btnCancel);

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Hide keyboard
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    // dismiss dialog
                    filterDialog.dismiss();
                }
            });
        }
        filterDialog.show();
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

    @Override
    public void onRateClick() {
        //rateDialog();

    }

    @Override
    public void onLikeClick() {
        // presenter

    }

    @Override
    public void onCommentClick() {
        commentDialog();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtSearch:
                imgBg.setVisibility(View.GONE);
                break;
            case R.id.imgFilter:
                filterDialog();
                break;
        }
    }

    /*************************************************************/

}
