package inc.talentedinc.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import inc.talentedinc.R;
import inc.talentedinc.factory.Factory;
import inc.talentedinc.model.CourseComment;
import inc.talentedinc.model.Result;
import inc.talentedinc.presenter.UpComingDetailsPresenter;
import inc.talentedinc.utilitis.ActionUtils;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class UpComingDetailsActivity extends AppCompatActivity implements UpComingDetailsPresenter.ViewUpComingDetails, MaterialRatingBar.OnRatingChangeListener, View.OnClickListener {

    /****************************** asmaa *************************/

    public static final String COURSE ="course";
    private UpComingDetailsPresenter presenter;
    private MaterialRatingBar ratingBar;

    private CircleImageView imgCourse;
    private TextView tvCourseName, tvInstructorName, tvWorkspaceName, tvLocation,tvDescription ,tvStartD,tvEndD,tvDuration;
    private Button btnRegister;
    private TextView tvCommentsNum , tvLikesNum;
    private ProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming_details);
        initView();
    }

    /****************************** *************************/

    /****************************** asmaa *************************/


    private void initView(){
//        ratingBar = findViewById(R.id.mRating);
//        ratingBar.setOnRatingChangeListener(this);
        imgCourse=findViewById(R.id.imgCourse);
        progressView = findViewById(R.id.pv_load);
        tvCourseName=findViewById(R.id.tvCourseUser);
        tvInstructorName=findViewById(R.id.tvCourseUser);
        tvInstructorName.setOnClickListener(this);
        tvWorkspaceName=findViewById(R.id.tvWorkSpace);
        tvWorkspaceName.setOnClickListener(this);
        tvLocation=findViewById(R.id.tvLocation);
        tvDescription=findViewById(R.id.tvDisc);
        tvStartD =findViewById(R.id.tvStart);
        tvEndD=findViewById(R.id.tvEnd);
        tvDescription=findViewById(R.id.tvDuration);
        btnRegister =findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        presenter = new UpComingDetailsPresenter(Factory.provideCommentLike());
        presenter.setView((Result) getIntent().getExtras().getSerializable(COURSE),this);

    }
    /******************************  *************************/

    /****************************** asmaa *************************/

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressView.setVisibility(View.GONE);

    }

    @Override
    public void setCourseName(String name) {
        tvCourseName.setText(name);

    }

    @Override
    public void setWorkspaceName(String name) {
        tvWorkspaceName.setText(name);

    }

    @Override
    public void setInstructorName(String name) {
        tvInstructorName.setText(name);

    }

    @Override
    public void setLocation(String address) {
        tvLocation.setText(address);

    }

    @Override
    public void setLikes(int likes) {


    }

    @Override
    public void setCommentsNum(int comments) {

    }

    @Override
    public void setDuration(String duration) {
        tvDescription.setText(duration);

    }

    @Override
    public void setStartDate(String startD) {
        tvStartD.setText(startD);

    }

    @Override
    public void setEndDate(String endD) {
        tvEndD.setText(endD);

    }

    @Override
    public void setDescription(String description) {
        tvDescription.setText(description);

    }

    @Override
    public void setComments(ArrayList<CourseComment> comments) {

    }

    @Override
    public void showToast(String msg) {
        ActionUtils.showToast(this, msg);


    }

    @Override
    public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
        ActionUtils.showToast(this, rating+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                //Register
                break;
            case R.id.tvCourseName:
                //switch to Instructor profile
//                (Result) getIntent().getExtras().getSerializable(COURSE)
                break;
            case R.id.tvWorkSpace:
                //switch to workSpace profile
//                (Result) getIntent().getExtras().getSerializable(COURSE)
                break;

        }

    }

    /******************************  *************************/

}
