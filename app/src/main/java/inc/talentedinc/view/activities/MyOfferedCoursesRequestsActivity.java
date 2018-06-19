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
    private ProgressBar requestsProgBar;

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
        requestsProgBar = (ProgressBar)findViewById(R.id.requests_prog);


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

    /*
    this methods view the requests on an offered course
    @Param ArrayList<OfferedCourseWorkspace> offeredCourseWorkspaces
     */
    @Override
    public void viewRequests(ArrayList<OfferedCourseWorkspace> offeredCourseWorkspaces) {
        requestsProgBar.setVisibility(View.GONE);
        requestingWorkspaces.addAll(offeredCourseWorkspaces);
        requestsViewAdapter.notifyDataSetChanged();
    }

    /*
    this methods listens to the press on accept button
     */
    @Override
    public void workspaceAcceptedSuccessfully() {
        Intent intent = new Intent(this,MyOfferedCourses.class);
        startActivity(intent);
        finish();
    }

    /*
     * this method goes to the selected workspace profile activity
     * @Param  OfferedCourseDetailed offeredCourseDetailed
     */
    @Override
    public void gotoWorkspaceProfile(int workspaceId) {
        Intent intent = new Intent(this,WorkSpaceProfile.class);
        intent.putExtra(WorkSpaceProfile.workSpaceID,String.valueOf(workspaceId));
        startActivity(intent);
    }
}
