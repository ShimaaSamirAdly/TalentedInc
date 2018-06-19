package inc.talentedinc.view.fragmnts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.adapter.OfferedCoursesViewAdapter;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.presenter.OfferedCoursesPresenter;
import inc.talentedinc.presenter.OfferedCoursesPresenterInt;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.activities.HomeActivity;
import inc.talentedinc.view.activities.MyOfferedCourses;
import inc.talentedinc.view.activities.OfferedCourseDetailsActivity;
import inc.talentedinc.view.callbackinterfaces.EndlessScrollHandler;


public class OfferedCoursesFragment extends Fragment implements EndlessScrollHandler {

    private RecyclerView coursesRecyclerView;
    private ArrayList<OfferedCourseDetailed> offeredCourses;
    private LinearLayoutManager coursesLayoutManager;
    private OfferedCoursesViewAdapter offeredCoursesViewAdapter;
    private ProgressBar myProgressBar;
    private Boolean itShouldLoadMore;
    private OfferedCoursesPresenterInt offeredCoursesPresenterInt;
    private Button myOfferedCoursesButton;
    public static String OFFERED_COURSE_PRESENTER = "offered_course_presenter";

    public OfferedCoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offered_courses, container, false);
        //***************************** Asmaa ***************************************

        ((HomeActivity) getActivity()).whichFragment(HomeActivity.OFFERD);
        HomeActivity.KEY=HomeActivity.OFFERD;
        // *****************************  ***************************************

        myProgressBar = (ProgressBar) view.findViewById(R.id.offered_course_pBar);
        coursesRecyclerView = (RecyclerView) view.findViewById(R.id.courses_rec_view);
        coursesRecyclerView.setHasFixedSize(true);
        coursesLayoutManager = new LinearLayoutManager(getContext());
        coursesRecyclerView.setLayoutManager(coursesLayoutManager);
        coursesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        if (itShouldLoadMore) {
                            //loadMore();
                            showProgressBar();
                            offeredCoursesPresenterInt.loadMoreOfferedCourses(SharedPrefrencesSingleton.getSharedPrefUser(getContext()).getUserId());
                            itShouldLoadMore = false;
                        }
                    }
                }
            }
        });

        myOfferedCoursesButton = (Button) view.findViewById(R.id.my_offered_course_btn);
        myOfferedCoursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyOfferedCourses.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        offeredCoursesPresenterInt = new OfferedCoursesPresenter(this);
        itShouldLoadMore = true;
        offeredCourses = new ArrayList<>();
        offeredCoursesViewAdapter = new OfferedCoursesViewAdapter(offeredCourses);
        offeredCoursesViewAdapter.setMyContext(getContext());
        offeredCoursesViewAdapter.setOfferedCoursesPresenter(offeredCoursesPresenterInt);
        coursesRecyclerView.setAdapter(offeredCoursesViewAdapter);
        showProgressBar();
        offeredCoursesPresenterInt.fetchCourses(SharedPrefrencesSingleton.getSharedPrefUser(getContext()).getUserId());
        itShouldLoadMore = false;
    }

    @Override
    public void showData(ArrayList<OfferedCourseDetailed> courses) {
        hideProgressBar();
        itShouldLoadMore = true;
        offeredCourses.addAll(courses);
        offeredCoursesViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgressBar() {
        myProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        myProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void makeErrorToast() {
        hideProgressBar();
        itShouldLoadMore = true;
        Toast.makeText(getContext(), "Error fetching course", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeToastRequestResult(int result,int position) {
        switch (result) {
            case 0:
                Toast.makeText(getContext(), "Error requesting course Try again later!", Toast.LENGTH_SHORT).show();
                offeredCourses.get(position).setRequested(false);
                offeredCoursesViewAdapter.notifyItemChanged(position);
                break;
            case 1:
                Toast.makeText(getContext(), "Your request has been sent successfully!", Toast.LENGTH_SHORT).show();
                offeredCourses.get(position).setRequested(true);
                offeredCoursesViewAdapter.notifyItemChanged(position);
                break;
        }
    }

    @Override
    public void gotoDetailedCourseView(OfferedCourseDetailed offeredCourseDetailed) {
        Intent intent = new Intent(getContext(), OfferedCourseDetailsActivity.class);
        intent.putExtra(OfferedCoursesViewAdapter.OFFERED_COURSE_OBJECT, offeredCourseDetailed);
        startActivity(intent);
    }

    @Override
    public void dataFinished() {
        hideProgressBar();
        itShouldLoadMore = false;
    }

    @Override
    public void courseRequestCanceled(int position) {
        Toast.makeText(getContext(), "Request Canceled successfully!", Toast.LENGTH_SHORT).show();
        offeredCourses.get(position).setRequested(false);
        offeredCoursesViewAdapter.notifyItemChanged(position);
    }

    @Override
    public void errorCancelingCopurse(int position) {
        Toast.makeText(getContext(), "Error canceling request!", Toast.LENGTH_SHORT).show();
        offeredCourses.get(position).setRequested(true);
        offeredCoursesViewAdapter.notifyItemChanged(position);
    }
}
