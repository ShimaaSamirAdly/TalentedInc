package inc.talentedinc.view.fragmnts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.rey.material.widget.ProgressView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import inc.talentedinc.R;
import inc.talentedinc.adapter.CategorySpinAdapter;
import inc.talentedinc.adapter.HomeAdapter;
import inc.talentedinc.adapter.SignUpInterestsAdapter;
import inc.talentedinc.factory.Factory;
import inc.talentedinc.interactor.upcoming.NetworkUpComingCoursesInteractor;
import inc.talentedinc.listener.HomeListener;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.Result;
import inc.talentedinc.presenter.UpComingCoursesPresenter;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.utilitis.ValidationUtility;
import inc.talentedinc.view.activities.HomeActivity;
import inc.talentedinc.view.activities.OthersProfileActivity;
import inc.talentedinc.view.activities.UpComingDetailsActivity;
import inc.talentedinc.utilitis.ActionUtils;
import inc.talentedinc.utilitis.EndlessRecyclerOnScrollListener;
import inc.talentedinc.viewholder.UpComingCoursesViewHolder;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.facebook.FacebookSdk.getApplicationContext;

public class UpComingCoursesFragment extends Fragment implements UpComingCoursesPresenter.ViewListener ,HomeListener, View.OnClickListener {


    private List<String> categories = new ArrayList<>();
    /****************************** asmaa *************************/

    private RecyclerView recyclerView;
    private int page=0;
    private boolean isLoading = false;
    private boolean moreDataAvailable = true;
    private CategorySpinAdapter adapter;

    private UpComingCoursesPresenter presenter;
    private HomeAdapter upcomingCoursesAdapter;
    private ArrayList<Result> dataResult = new ArrayList<>();
    private ProgressView progressView;
    private EditText edSearh;
    private ImageView imgBg;
    private AlertDialog commentDialog ,filterDialog;
    private ImageView imgFilter;
    private String mLastSearchQuery = "";



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

        ((HomeActivity)getActivity()).whichFragment(HomeActivity.UPCOMING);
        HomeActivity.KEY=HomeActivity.UPCOMING;

        // hide keyboard when launch screen
        imgFilter= v.findViewById(R.id.imgFilter);
        imgFilter.setOnClickListener(this);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        imgBg = v.findViewById(R.id.imgBg);
        edSearh = v.findViewById(R.id.txtSearch);
        edSearh.setOnClickListener(this);

        recyclerView= v.findViewById(R.id.my_recycler_view);
        progressView=v.findViewById(R.id.pv_load);
        presenter = new UpComingCoursesPresenter(Factory.provideUpComing(),Factory.provideCommentLike(),Factory.provideCategories());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        upcomingCoursesAdapter = new HomeAdapter(HomeAdapter.UPCOMING,gridLayoutManager,this);
        upcomingCoursesAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(upcomingCoursesAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && ((HomeActivity)getActivity()).fab.getVisibility() == View.VISIBLE) {
                    ((HomeActivity)getActivity()).fab.hide();
                } else if (dy < 0 && ((HomeActivity)getActivity()).fab.getVisibility() != View.VISIBLE) {
                    ((HomeActivity)getActivity()).fab.show();
                }
            }
        });

        presenter.setView(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page, this);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager/*recyclerView.getLayoutManager()*/) {
            @Override
            public void onLoadMore() {
                if (!isLoading && moreDataAvailable ) {
                    isLoading = true;
                    loadMoreData();
                }
            }
        });

        RxTextView.textChangeEvents(edSearh)
                .onBackpressureLatest()
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                      //  dataResult.clear();
                        //upcomingCoursesAdapter.clearData();
                        String newSearchQuery = textViewTextChangeEvent.text() + "";
                        if( !mLastSearchQuery.equals(newSearchQuery) &&  !newSearchQuery.equals(null)
                                && !newSearchQuery.equals("search") && !newSearchQuery.equals("")) {
                            // isSearch=true;
                            mLastSearchQuery = newSearchQuery;
                            if (ActionUtils.isInternetConnected(getActivity())) {
                                  dataResult.clear();
                                upcomingCoursesAdapter.clearData();
                                page =0;
                                presenter.getSearchByName(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),mLastSearchQuery, page);
                            }else {
                                ActionUtils.showToast(getActivity(), "connection error");
                            }
                        }else {
                           dataResult.clear();
                            upcomingCoursesAdapter.clearData();
                            page=0;
                            presenter.getHomeData(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page);

                        }
                    }
                });

        presenter.getCategoties();
    }

    private void loadMoreData() {
        upcomingCoursesAdapter.setLoading(true);
        page++;
        presenter.getHomeData(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page);
    }

    private void commentDialog(final int courseId , final String courseDate){
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
    private void filterDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View dialogView = this.getLayoutInflater().inflate(R.layout.custom_filter_dialog, null);
        builder.setView(dialogView);

        filterDialog = builder.create();
        if (dialogView != null) {

            final Spinner spinnerCategories = dialogView.findViewById(R.id.spinnerCategories);
            final Spinner spinnerCities = dialogView.findViewById(R.id.spinnerCities);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerCategories.setAdapter(dataAdapter);
            Button okBtn = dialogView.findViewById(R.id.btnFilter);
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataResult.clear();
                    upcomingCoursesAdapter.clearData();
                  page=0;
                  presenter.getSearchByFilter(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),spinnerCategories.getSelectedItem().toString(), spinnerCities.getSelectedItem().toString(),page);
                }
            });

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
        Log.i("PAGE",page+"");
        if (listData.size() > 0){
            dataResult.addAll(listData);
            if (page == NetworkUpComingCoursesInteractor.totalPage-1){
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
        categories = new ArrayList<>();
        if (listData.size()>0) {
            for (int i = 0; i < listData.size(); i++) {
                categories.add(listData.get(i).getName());
            }
        }
    }

    @Override
    public void errorMsg() {
        ActionUtils.showToast(getActivity(), "API error");
    }

    @Override
    public void showCategoriesError(String msg) {
    }

    @Override
    public void showToast(String s) {
        ActionUtils.showToast(getActivity(),s);
    }

    @Override
    public void setRateResult() {

    }

    @Override
    public void setLikeResult() {
       /* UpComingCoursesViewHolder.likeButton.setLiked(false);
        int resultN = Integer.parseInt(UpComingCoursesViewHolder.tvLikes.getText().toString());
        UpComingCoursesViewHolder.tvLikes.setText(String.valueOf(resultN+1));*/
      //  presenter.getHomeData(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page);
    }

    @Override
    public void setDisLikeResult() {

       // presenter.getHomeData(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page);
    }

    @Override
    public void setCommentResult() {
        commentDialog.dismiss();

//        presenter.getHomeData(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),page);


    }

    @Override
    public void setFilterResult() {
        filterDialog.dismiss();
    }

    @Override
    public void onCourseClicked(Result result) {
        Intent switchToDetails = new Intent(getActivity(),UpComingDetailsActivity.class);
        switchToDetails.putExtra(UpComingDetailsActivity.COURSE, (Serializable)  result);
        startActivity(switchToDetails );
    }

    @Override
    public void onRateClick(int courseId , String courseDate) {
        //rateDialog();
    }

    @Override
    public void onLikeClick(int courseId , String courseDate, int position) {
        // presenter
            presenter.setLike(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),courseId,courseDate);
    }

    @Override
    public void onDisLikeClick(int courseId, String courseDate , int position) {
        presenter.setDisLike(SharedPrefrencesSingleton.getSharedPrefUser(getActivity()).getUserId(),courseId,courseDate);
    }

    @Override
    public void onCommentClick(int courseId , String courseDate, int position) {
        commentDialog(courseId,courseDate);
    }

    @Override
    public void onInstructorClick(int instructorId) {
        //// switch to instructor Profile
        Intent intentUser = new Intent(getActivity(), OthersProfileActivity.class);
        intentUser.putExtra("userId", instructorId);
        startActivity(intentUser);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtSearch:
                page=0;
                break;
            case R.id.imgFilter:
                filterDialog();
                break;
        }
    }
    /*************************************************************/

}
