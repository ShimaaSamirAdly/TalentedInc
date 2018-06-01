package inc.talentedinc.view.fragmnts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.adapter.CoursesViewAdapter;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.presenter.OfferedCoursesPresenter;
import inc.talentedinc.presenter.OfferedCoursesPresenterInt;
import inc.talentedinc.view.callbackinterfaces.EndlessScrollHandler;


public class OfferedCoursesFragment extends Fragment implements EndlessScrollHandler {

    RecyclerView coursesRecyclerView;
    ArrayList<MinaCourse> offeredCourses;
    LinearLayoutManager coursesLayoutManager;
    CoursesViewAdapter coursesViewAdapter;
    ProgressBar myProgressBar;
    Boolean itShouldLoadMore;
    OfferedCoursesPresenterInt offeredCoursesPresenterInt;

    public OfferedCoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offeredCoursesPresenterInt = new OfferedCoursesPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offered_courses, container, false);

        itShouldLoadMore = true;
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
                            offeredCoursesPresenterInt.fetchCourses();
                            itShouldLoadMore = false;
                        }
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        offeredCourses = new ArrayList<>();
        coursesViewAdapter = new CoursesViewAdapter(offeredCourses);
        coursesRecyclerView.setAdapter(coursesViewAdapter);
        offeredCoursesPresenterInt.fetchCourses();
        itShouldLoadMore = false;
    }

    @Override
    public void showData(ArrayList<MinaCourse> courses) {
        Log.i("showData",courses.toString());
        itShouldLoadMore = true;
        offeredCourses.addAll(courses);
        coursesViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgressBar() {
        myProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        //
        myProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void makeErrorToast() {
        itShouldLoadMore = true;
        Toast.makeText(getContext(), "Error fetching course", Toast.LENGTH_SHORT).show();
    }
}
