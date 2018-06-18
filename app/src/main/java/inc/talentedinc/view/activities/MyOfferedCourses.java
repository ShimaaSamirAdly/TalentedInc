package inc.talentedinc.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private OfferedCourseDetailed acceptedOfferedCourse;
    public static String MY_OFFERED_COURSE_OBJECT = "my_offered_course_object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offered_courses);

        coursesRecyclerView = (RecyclerView) findViewById(R.id.my_offered_course_rec);
        coursesRecyclerView.setHasFixedSize(true);
        coursesLayoutManager = new LinearLayoutManager(this);
        coursesRecyclerView.setLayoutManager(coursesLayoutManager);
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
        myOfferedCoursePresenter.fetchCourses(SharedPrefrencesSingleton.getSharedPrefUser(this).getUserId());
    }

    @Override
    public void viewMyOfferedCourses(ArrayList<OfferedCourseDetailed> myOfferedCourses) {
        offeredCourses.addAll(myOfferedCourses);
        offeredCoursesViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void gotoCoursesRequestsActivity(OfferedCourseDetailed offeredCourseDetailed) {
        //go to requests activity
        Intent intent = new Intent(this,MyOfferedCoursesRequestsActivity.class);
        intent.putExtra(MyOfferedCourses.MY_OFFERED_COURSE_OBJECT,offeredCourseDetailed);
        startActivity(intent);
    }
}
