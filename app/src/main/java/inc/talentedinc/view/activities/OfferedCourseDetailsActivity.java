package inc.talentedinc.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import inc.talentedinc.R;
import inc.talentedinc.adapter.OfferedCoursesViewAdapter;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;

public class OfferedCourseDetailsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_course_details);
        Intent intent = getIntent();
        offeredCourseDetailed = (OfferedCourseDetailed) intent.getSerializableExtra(OfferedCoursesViewAdapter.OFFERED_COURSE_OBJECT);
        initializeViews();

    }

    @Override
    protected void onStart() {
        super.onStart();
        setData();
    }

    private void initializeViews(){
        offeredCourseImage = (ImageView)findViewById(R.id.offered_course_img);
        offeredCourseName = (TextView)findViewById(R.id.course_name_txt);
        offeredCourseCreator = (TextView)findViewById(R.id.course_creator_txt);
        offeredCoursedescription = (TextView)findViewById(R.id.offered_course_desc_txt);
        offeredCourseStartDate = (TextView)findViewById(R.id.start_date_txt);
        offeredCourseEndDate = (TextView)findViewById(R.id.end_date_txt);
        offeredCourseDuration = (TextView)findViewById(R.id.duration_txt);
        offeredCourseApplicants = (TextView)findViewById(R.id.no_applicants_txt);
        requestButton = (Button)findViewById(R.id.request_offered_course_btn);
    }

    private void setData(){
        offeredCourseName.setText(offeredCourseDetailed.getName());
        if(offeredCourseDetailed.getCourseCreator() != null){
            offeredCourseCreator.setText(offeredCourseDetailed.getCourseCreator().getFirstName()+""+offeredCourseDetailed.getCourseCreator().getLastName());
        }else{
            offeredCourseCreator.setText(offeredCourseDetailed.getHostingWorkSpaceId().getName());
        }
        offeredCoursedescription.setText(offeredCourseDetailed.getDescription());
        offeredCourseStartDate.setText(offeredCourseDetailed.getStartDate());
        offeredCourseEndDate.setText(offeredCourseDetailed.getEndDate());
        offeredCourseDuration.setText("30 Hours");
        offeredCourseApplicants.setText(String.valueOf(offeredCourseDetailed.getNoOfApplicant()));
    }
}
