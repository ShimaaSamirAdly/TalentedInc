package inc.talentedinc.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import inc.talentedinc.R;
import inc.talentedinc.adapter.RequstsAdapter;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.model.offeredcourse.OfferedCourseWorkspace;
import inc.talentedinc.presenter.RequestsPresenter;
import inc.talentedinc.view.callbackinterfaces.RequestsHandler;

public class MyOfferedCoursesRequestsActivity extends AppCompatActivity implements RequestsHandler{

    private RecyclerView requestRecyclerView;
    private ArrayList<OfferedCourseWorkspace> requestingWorkspaces;
    private LinearLayoutManager requestsLayoutManager;
    private RequstsAdapter requestsViewAdapter;
    private RequestsPresenter requestsPresenter;
    private OfferedCourseDetailed myOfferedCourse;
    public static String ACCEPTED_OFFERED_COURSE = "accepted_offered_course";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offered_courses_requests);

        Intent intent = getIntent();
        myOfferedCourse = (OfferedCourseDetailed) intent.getSerializableExtra(MyOfferedCourses.MY_OFFERED_COURSE_OBJECT);

        requestRecyclerView = (RecyclerView) findViewById(R.id.my_offered_course_workspace_requests_rec);
        requestRecyclerView.setHasFixedSize(true);
        requestsLayoutManager = new LinearLayoutManager(this);
        requestRecyclerView.setLayoutManager(requestsLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestingWorkspaces = new ArrayList<>();
        requestsViewAdapter = new RequstsAdapter(requestingWorkspaces);
        requestsViewAdapter.setCourseId(myOfferedCourse.getOfferedCourseId());
        requestsPresenter = new RequestsPresenter(this);
        requestsViewAdapter.setRequestsPresenter(requestsPresenter);
        requestRecyclerView.setAdapter(requestsViewAdapter);
        requestsPresenter.fetchCourseRequests(myOfferedCourse.getOfferedCourseId());
    }

    @Override
    public void viewRequests(ArrayList<OfferedCourseWorkspace> offeredCourseWorkspaces) {
        requestingWorkspaces.addAll(offeredCourseWorkspaces);
        requestsViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void workspaceAcceptedSuccessfully() {
        Intent intent = new Intent(this,MyOfferedCourses.class);
        startActivity(intent);
        finish();
    }
}
