package inc.talentedinc.view.activities;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.adapter.OfferedCoursesViewAdapter;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.presenter.OfferedCoursesPresenter;
import inc.talentedinc.presenter.OfferedCoursesPresenterInt;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.callbackinterfaces.EndlessScrollHandler;
import inc.talentedinc.view.fragmnts.OfferedCoursesFragment;

public class OfferedCourseDetailsActivity extends AppCompatActivity implements EndlessScrollHandler{

    private OfferedCourseDetailed offeredCourseDetailed;
    private ImageView offeredCourseImage;
    private TextView offeredCourseName;
    private TextView offeredCourseCreator;
    private TextView offeredCoursedescription;
    private TextView offeredCourseStartDate;
    private TextView offeredCourseEndDate;
    private TextView offeredCourseDuration;
    private TextView offeredCourseApplicants;
    private Button requestButton;
    private LinearLayout requestsLayout;
    private OfferedCoursesPresenterInt offeredCoursesPresenterInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_course_details);
        Intent intent = getIntent();
        offeredCourseDetailed = (OfferedCourseDetailed) intent.getSerializableExtra(OfferedCoursesViewAdapter.OFFERED_COURSE_OBJECT);
        //offeredCoursesPresenterInt = (OfferedCoursesPresenterInt) intent.getSerializableExtra(OfferedCoursesFragment.OFFERED_COURSE_PRESENTER);
        initializeViews();
        offeredCoursesPresenterInt = new OfferedCoursesPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setData();
    }

    private void initializeViews() {
        offeredCourseImage = (ImageView) findViewById(R.id.imgCourse);
        offeredCourseName = (TextView) findViewById(R.id.course_name_txt);
        offeredCourseCreator = (TextView) findViewById(R.id.course_creator_txt);
        offeredCoursedescription = (TextView) findViewById(R.id.offered_course_desc_txt);
        offeredCourseStartDate = (TextView) findViewById(R.id.start_date_txt);
        offeredCourseEndDate = (TextView) findViewById(R.id.end_date_txt);
        offeredCourseDuration = (TextView) findViewById(R.id.duration_txt);
        offeredCourseApplicants = (TextView) findViewById(R.id.no_applicants_txt);
        requestButton = (Button) findViewById(R.id.btnRegister);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(requestButton.getText().equals("Request")) {
                    requestCourse();
                }else {
                    cancelCourse();
                }
            }
        });
    }

    private void cancelCourse() {
        offeredCoursesPresenterInt.cancelOfferedCourse(offeredCourseDetailed.getOfferedCourseId(),
                SharedPrefrencesSingleton.getSharedPrefUser(this).getUserId(),0);
    }

    private void requestCourse() {
        offeredCoursesPresenterInt.requestOfferedCourse(offeredCourseDetailed.getOfferedCourseId(),
                SharedPrefrencesSingleton.getSharedPrefUser(this).getUserId(),0);
    }

    private void setData() {

        offeredCourseName.setText(offeredCourseDetailed.getName());

        if (offeredCourseDetailed.getHostingWorkSpaceId() != null) {
            offeredCourseCreator.setText(offeredCourseDetailed.getHostingWorkSpaceId().getName());
            offeredCourseCreator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),WorkSpaceProfile.class);
                    Log.i("workspaceId",String.valueOf(offeredCourseDetailed.getHostingWorkSpaceId().getWorkSpaceId()));
                    intent.putExtra(WorkSpaceProfile.workSpaceID,String.valueOf(offeredCourseDetailed.getHostingWorkSpaceId().getWorkSpaceId()));
                    startActivity(intent);
                }
            });
        }
        if (offeredCourseDetailed.getDescription() != null) {
            offeredCoursedescription.setText(offeredCourseDetailed.getDescription());
        }
        if (offeredCourseDetailed.getStartDate() != null) {
            offeredCourseStartDate.setText(offeredCourseDetailed.getStartDate());
        }
        if (offeredCourseDetailed.getEndDate() != null) {
            offeredCourseEndDate.setText(offeredCourseDetailed.getEndDate());
        }
        if(offeredCourseDetailed.getDurationHours() != null) {
            offeredCourseDuration.setText(String.valueOf(offeredCourseDetailed.getDurationHours()));
        }
        if (offeredCourseDetailed.getNoOfApplicant() != null) {
            offeredCourseApplicants.setText(String.valueOf(offeredCourseDetailed.getNoOfApplicant()));
        }

        if(offeredCourseDetailed.getImageUrl() != null) {
            Log.i("courseImg",offeredCourseDetailed.getImageUrl());
            Glide.
                    with(this).
                    load(offeredCourseDetailed.getImageUrl()).
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    skipMemoryCache(true).
                    into(offeredCourseImage);
        }else {
            Glide.
                    with(this).
                    load(R.drawable.default_course).
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    skipMemoryCache(true).
                    into(offeredCourseImage);
        }

    }

    @Override
    public void showData(ArrayList<OfferedCourseDetailed> courses) {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void makeErrorToast() {

    }

    @Override
    public void makeToastRequestResult(int result,int position) {
        switch (result) {
            case 0:
                Toast.makeText(this, "Error requesting course Try again later!", Toast.LENGTH_SHORT).show();
                requestButton.setText("Request");
                break;
            case 1:
                Toast.makeText(this,"Your request has been sent successfully!",Toast.LENGTH_SHORT).show();
                requestButton.setText("Cancel");
                break;
        }
    }

    @Override
    public void gotoDetailedCourseView(OfferedCourseDetailed offeredCourseDetailed) {
    }

    @Override
    public void dataFinished() {
    }

    @Override
    public void courseRequestCanceled(int position) {
        Toast.makeText(this,"Request has been canceled!",Toast.LENGTH_SHORT).show();
        requestButton.setText("Request");
    }

    @Override
    public void errorCancelingCopurse(int position) {
        Toast.makeText(this,"Error canceling request!",Toast.LENGTH_SHORT).show();
        requestButton.setText("Cancel");
    }
}
