package inc.talentedinc.view.fragmnts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.rey.material.widget.ProgressView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.adapter.HomeAdapter;
import inc.talentedinc.factory.Factory;
import inc.talentedinc.interactor.history.NetworkHistoryInteractor;
import inc.talentedinc.interactor.upcoming.NetworkUpComingCoursesInteractor;
import inc.talentedinc.listener.HomeListener;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.Result;
import inc.talentedinc.presenter.HistoryPresenter;
import inc.talentedinc.presenter.UpComingCoursesPresenter;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.utilitis.ActionUtils;
import inc.talentedinc.utilitis.EndlessRecyclerOnScrollListener;
import inc.talentedinc.utilitis.ValidationUtility;
import inc.talentedinc.view.activities.HomeActivity;
import inc.talentedinc.view.activities.OthersProfileActivity;
import inc.talentedinc.view.activities.UpComingDetailsActivity;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class RegisterFragment extends Fragment implements UpComingCoursesPresenter.ViewListener, HomeListener, MaterialRatingBar.OnRatingChangeListener {

    /****************************** asmaa *************************/
    private RecyclerView recyclerView;
    private int page=0;
    private boolean isLoading = false;
    private boolean moreDataAvailable = true;

    private HistoryPresenter presenter;
    private HomeAdapter upcomingCoursesAdapter;
    private ArrayList<Result> dataResult = new ArrayList<>();
    private ProgressView progressView;
    private LinearLayout linearLayoutSearch;
    private AlertDialog commentDialog ,rateDialog;
    private MaterialRatingBar ratingCourse ;
    private MaterialRatingBar ratingInstructor ;
    private MaterialRatingBar ratingWorkspace ;
    public static final String History = "register";

    public RegisterFragment() {
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
        ((HomeActivity)getActivity()).whichFragment(HomeActivity.NOTIFICATION);
        HomeActivity.KEY=HomeActivity.NOTIFICATION;

        linearLayoutSearch =v.findViewById(R.id.ll2);
        linearLayoutSearch.setVisibility(View.GONE);
        recyclerView= v.findViewById(R.id.my_recycler_view);
        progressView=v.findViewById(R.id.pv_load);
        presenter = new HistoryPresenter(Factory.provideHistory(),Factory.provideCommentLike(),Factory.provideRate());
        presenter.setView(History,SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        upcomingCoursesAdapter = new HomeAdapter(HomeAdapter.HISTORY,gridLayoutManager,this);
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
        page++;
        presenter.getHomeData(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page);
    }

    private void commentDialog(final int courseId, final String courseDate){
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
                        if (ActionUtils.isInternetConnected(getActivity()))
                            presenter.setComment(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),courseId,courseDate,etComment.getText().toString());
                        else
                            ActionUtils.showToast(getActivity(),"Connection Error");
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

    private void rateDialog(final int courseId, final String courseDate){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final View dialogView = this.getLayoutInflater().inflate(R.layout.custom_rate_dialog, null);
        builder.setView(dialogView);

        rateDialog = builder.create();
        if (dialogView != null) {
            Button btnRaiting =dialogView.findViewById(R.id.btnRate);
            Button btnCancel = dialogView.findViewById(R.id.btnCancel);

            ratingCourse =dialogView.findViewById(R.id.mRatingCourse);
            ratingInstructor =dialogView.findViewById(R.id.mRatingInstructor);
            ratingWorkspace =dialogView.findViewById(R.id.mRatingWorkspace);
            ratingCourse.setOnRatingChangeListener(this);
            ratingInstructor.setOnRatingChangeListener(this);
            ratingWorkspace.setOnRatingChangeListener(this);

            btnRaiting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Raiting", ratingCourse.getRating()+"");
                    presenter.setRate(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),courseId,courseDate,ratingCourse.getRating(), ratingInstructor.getRating(),ratingWorkspace.getRating());
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rateDialog.dismiss();
                }
            });
            ratingCourse.setOnRatingChangeListener(this);
            ratingInstructor.setOnRatingChangeListener(this);
            ratingWorkspace.setOnRatingChangeListener(this);
        }
        rateDialog.show();
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
        upcomingCoursesAdapter.setLoading(false);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingMoreData() {
        upcomingCoursesAdapter.setLoading(false);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataAvailable() {
        upcomingCoursesAdapter.setLoading(false);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void setData(ArrayList<Result> listData) {
        isLoading=false;
        if (listData.size() > 0){
            dataResult.addAll(listData);
            if (page == NetworkHistoryInteractor.totalPage-1){
                moreDataAvailable =false;
            }
        }
        upcomingCoursesAdapter.setData(dataResult);
    }

    @Override
    public void setDataSearchName(ArrayList<Result> listData) {

    }

    @Override
    public void setDataSearchFilter(ArrayList<Result> listData) {

    }

    @Override
    public void setCategories(List<Categories> listData) {

    }

    @Override
    public void errorMsg() {
        ActionUtils.showToast(getActivity(), "API error");
    }

    @Override
    public void showCategoriesError(String msg) {
        ActionUtils.showToast(getActivity(),msg);
    }

    @Override
    public void showToast(String s) {
        ActionUtils.showToast(getActivity(),s);

    }

    @Override
    public void setRateResult() {
        rateDialog.dismiss();
        dataResult.clear();
        upcomingCoursesAdapter.clearData();
        page =0;
        presenter.getRegister(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page);
    }

    @Override
    public void setLikeResult() {
        presenter.getRegister(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page);
    }

    @Override
    public void setDisLikeResult() {
        dataResult.clear();
        upcomingCoursesAdapter.clearData();
        page =0;
        presenter.getRegister(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page);


    }

    @Override
    public void setCommentResult() {
        commentDialog.dismiss();
        dataResult.clear();
        upcomingCoursesAdapter.clearData();
        page =0;
        presenter.getHomeData(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page);

    }

    @Override
    public void onCourseClicked(Result result) {
        Intent switchToDetails = new Intent(getActivity(),UpComingDetailsActivity.class);
        switchToDetails.putExtra(UpComingDetailsActivity.COURSE, (Serializable)  result);
        startActivity(switchToDetails );
    }

    @Override
    public void onRateClick(int courseId, String courseDate) {
        rateDialog(courseId,courseDate);
    }

    @Override
    public void onLikeClick(int courseId, String courseDate) {
        presenter.setLike(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),courseId,courseDate);

    }

    @Override
    public void onDisLikeClick(int courseId, String courseDate) {
        presenter.setDisLike(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),courseId,courseDate);
    }

    @Override
    public void onCommentClick(int courseId, String courseDate) {
        commentDialog(courseId,courseDate);
    }


    @Override
    public void onInstructorClick(int instracturId) {
        Intent intentUser = new Intent(getActivity(), OthersProfileActivity.class);
        intentUser.putExtra("userId", instracturId);
        startActivity(intentUser);
    }

    @Override
    public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
        ActionUtils.showToast(getActivity(),rating+"");
    }

    /****************************** *************************/
}
