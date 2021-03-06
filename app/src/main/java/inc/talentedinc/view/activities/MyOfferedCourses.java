package inc.talentedinc.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import inc.talentedinc.R;
import inc.talentedinc.adapter.MyOfferedCourseViewAdapter;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.presenter.MyOfferedCoursePresenter;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.callbackinterfaces.MyOfferedCourseHandler;

public class MyOfferedCourses extends AppCompatActivity implements MyOfferedCourseHandler{

    private RecyclerView coursesRecyclerView;
    private ArrayList<OfferedCourseDetailed> offeredCourses;
    private LinearLayoutManager coursesLayoutManager;
    private MyOfferedCourseViewAdapter offeredCoursesViewAdapter;
    private MyOfferedCoursePresenter myOfferedCoursePresenter;
    public static String MY_OFFERED_COURSE_OBJECT = "my_offered_course_object";
    private ProgressBar myProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offered_courses);

        coursesRecyclerView = (RecyclerView) findViewById(R.id.my_offered_course_rec);
        coursesRecyclerView.setHasFixedSize(true);
        coursesLayoutManager = new LinearLayoutManager(this);
        coursesRecyclerView.setLayoutManager(coursesLayoutManager);
        myProgressBar = (ProgressBar)findViewById(R.id.your_offered_prog_bar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        offeredCourses = new ArrayList<>();
        offeredCoursesViewAdapter = new MyOfferedCourseViewAdapter(offeredCourses);
        offeredCoursesViewAdapter.setMyContext(this);
        myOfferedCoursePresenter = new MyOfferedCoursePresenter(this);
        offeredCoursesViewAdapter.setOfferedCoursesPresenter(myOfferedCoursePresenter);
        coursesRecyclerView.setAdapter(offeredCoursesViewAdapter);
        //get Offered courses from api
        myOfferedCoursePresenter.fetchCourses(SharedPrefrencesSingleton.getSharedPrefUser(this).getUserId());
    }

    /*
     * this method view the fetched courses in recycler view
     * @Param  ArrayList<OfferedCourseDetailed> myOfferedCourses
     */
    @Override
    public void viewMyOfferedCourses(ArrayList<OfferedCourseDetailed> myOfferedCourses) {
        myProgressBar.setVisibility(View.GONE);
        offeredCourses.addAll(myOfferedCourses);
        offeredCoursesViewAdapter.notifyDataSetChanged();
    }

    /*
    * this method goes to the selected workspace profile activity
    * @Param  OfferedCourseDetailed offeredCourseDetailed
     */
    @Override
    public void gotoCoursesRequestsActivity(OfferedCourseDetailed offeredCourseDetailed) {
        Intent intent = new Intent(this,MyOfferedCoursesRequestsActivity.class);
        intent.putExtra(MyOfferedCourses.MY_OFFERED_COURSE_OBJECT,offeredCourseDetailed);
        startActivity(intent);
    }
}
