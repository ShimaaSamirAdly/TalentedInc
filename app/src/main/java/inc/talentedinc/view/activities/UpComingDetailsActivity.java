package inc.talentedinc.view.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.like.LikeButton;
import com.like.OnAnimationEndListener;
import com.like.OnLikeListener;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import inc.talentedinc.R;
import inc.talentedinc.factory.Factory;
import inc.talentedinc.model.CourseComment;
import inc.talentedinc.model.Result;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.presenter.UpComingDetailsPresenter;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.utilitis.ActionUtils;
import inc.talentedinc.utilitis.ValidationUtility;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class UpComingDetailsActivity extends AppCompatActivity implements UpComingDetailsPresenter.ViewUpComingDetails, MaterialRatingBar.OnRatingChangeListener, View.OnClickListener, OnLikeListener, OnAnimationEndListener {

    /****************************** asmaa *************************/

    public static final String COURSE ="course";
    private UpComingDetailsPresenter presenter;
    ///private MaterialRatingBar ratingBar;

    private CircleImageView imgCourse;
    private TextView tvCourseName, tvInstructorName, tvWorkspaceName, tvLocation,tvDescription ,tvStartD,tvEndD,tvDuration;
    private Button btnRegister;
    private TextView tvCommentsNum , tvLikesNum;
    private ProgressView progressView;
    private LinearLayout myRoot ;
    private LinearLayout a;
    private CircleImageView commentUserImg;
    private TextView commentUserName;
    private TextView userComment, commentTvTime;
    private LikeButton likeButton;
    private Result result;
    private boolean isRegister;

    private  TextView tvSetComment;
    private AlertDialog commentDialog ,filterDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming_details);
        initView();
    }

    /****************************** *************************/

    /****************************** asmaa *************************/


    private void initView(){
         myRoot = (LinearLayout) findViewById(R.id.ll);
         a = new LinearLayout(this);
        a.setOrientation(LinearLayout.VERTICAL);
        tvSetComment = findViewById(R.id.tvSetComment);
        tvSetComment.setOnClickListener(this);

        imgCourse=findViewById(R.id.imgCourse);
        progressView = findViewById(R.id.pv_load);
        tvCourseName=findViewById(R.id.course_name_txt);
        tvInstructorName=findViewById(R.id.tvCourseUser);
        tvInstructorName.setOnClickListener(this);
        tvWorkspaceName=findViewById(R.id.course_creator_txt);
        tvWorkspaceName.setOnClickListener(this);
        tvLocation=findViewById(R.id.tvLocation);
        tvDescription=findViewById(R.id.offered_course_desc_txt);
        tvStartD =findViewById(R.id.start_date_txt);
        tvEndD=findViewById(R.id.tvEnd);
        tvDuration=findViewById(R.id.duration_txt);
        tvLikesNum= findViewById(R.id.tvLikes);
        tvCommentsNum=findViewById(R.id.tvComment);
        likeButton =findViewById(R.id.thumb_button);
        likeButton.setOnLikeListener(this);
        likeButton.setOnAnimationEndListener(this);
        btnRegister =findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        presenter = new UpComingDetailsPresenter(Factory.provideCommentLike(),Factory.provideRegister(),Factory.provideRate());
        result = new Result();
        result=(Result) getIntent().getExtras().getSerializable(COURSE);
        Log.i("IDID",SharedPrefrencesSingleton.getSharedPrefUser(UpComingDetailsActivity.this).getUserId()+"");
        Log.i("IDID2",result.getInstructorId().getUserId()+"");
       if (SharedPrefrencesSingleton.getSharedPrefUser(UpComingDetailsActivity.this).getUserId().equals( result.getInstructorId().getUserId()) ||
               result.getCourseStatus()==1){
            btnRegister.setVisibility(View.GONE);
        }
        presenter.setView(result,this);
    }

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
        tvLikesNum.setText(String.valueOf(likes));
    }

    @Override
    public void setCommentsNum(int comments) {
        tvCommentsNum.setText(String.valueOf(comments));
    }

    @Override
    public void setDuration(String duration) {
        tvDuration.setText(duration);
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
        if (comments.size() >0){
            for (int i =0; i<comments.size();i++){
                View child = getLayoutInflater().inflate(R.layout.comments_row_design, null);
                commentUserImg = child.findViewById(R.id.circleImageView);
                commentUserName = child.findViewById(R.id.tvUserName);
                userComment=child.findViewById(R.id.tvComment);
                commentTvTime=child.findViewById(R.id.tvTime);
                if (!comments.get(i).getComment().equals(null)){
                    userComment.setText(comments.get(i).getComment());
                }
                if (!comments.get(i).getUserNameOfcomment().equals(null))
                commentUserName.setText(comments.get(i).getUserNameOfcomment());

                if (comments.get(i).getTime()!=null){
                    commentTvTime.setText(comments.get(i).getTime());
                }
              //  if (!comments.get(i).getUserImageOfComment().equals(null)){
                    Glide.with(this).load(comments.get(i).getUserImageOfComment()).centerCrop()
                            .into(commentUserImg);
               // }
                a.addView(child);
            }
            myRoot.addView(a);
        }
    }

    @Override
    public void showToast(String msg) {
        ActionUtils.showToast(this, msg);
    }

    @Override
    public void setIsLike(boolean isLike) {
        if (isLike)
        likeButton.setLiked(true);
        else
            likeButton.setLiked(false);
    }

    @Override
    public void setIsRegister(boolean isRegister) {
        this.isRegister=isRegister;
        if (isRegister)
            btnRegister.setText("UnRegister");
        else
            btnRegister.setText("Register");
    }

    @Override
    public void setRegisterResult() {
        btnRegister.setText("UnRegister");
    }

    @Override
    public void setUnRegisterResult() {
        btnRegister.setText("Register");
    }

    @Override
    public void setIsRate(boolean isRate) {

    }

    @Override
    public void setRateResult() {

    }

    @Override
    public void setLikeResult() {
        likeButton.setLiked(true);
        int resultN = Integer.parseInt(tvLikesNum.getText().toString());
        tvLikesNum.setText(String.valueOf(resultN+1));

    }

    @Override
    public void setDisLikeResult() {
        likeButton.setLiked(false);
        int resultN = Integer.parseInt(tvLikesNum.getText().toString());
        tvLikesNum.setText(String.valueOf(resultN-1));
    }

    @Override
    public void setCommentResult() {
        commentDialog.dismiss();
        int resultN = Integer.parseInt(tvCommentsNum.getText().toString());
        tvCommentsNum.setText(String.valueOf(resultN+1));
    }

    @Override
    public void setCourseImage(String image) {
        Glide.with(this).load(image).centerCrop().into(imgCourse);
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
                if (isRegister)
                    presenter.unRegister(SharedPrefrencesSingleton.getSharedPrefUser(this).getUserId(),result.getOfferedCourseId(),result.getPublishedDate());
                else
                    presenter.setRegister(SharedPrefrencesSingleton.getSharedPrefUser(this).getUserId(),result.getOfferedCourseId(),result.getPublishedDate());
                break;
            case R.id.tvCourseUser:
                //switch to Instructor profile
                Log.i("profileUser", result.getInstructorId().getUserId()+"");

                Intent intentUser = new Intent(this, OthersProfileActivity.class);
                intentUser.putExtra("userId", result.getInstructorId().getUserId());
                startActivity(intentUser);
//                result
                break;
            case R.id.course_creator_txt:
                //switch to workSpace profile
                Log.i("workspace", result.getHostingWorkSpaceId().getWorkSpaceId()+"");
                Intent intentWork = new Intent(this, WorkSpaceProfile.class);
                intentWork.putExtra(WorkSpaceProfile.workSpaceID, result.getHostingWorkSpaceId().getWorkSpaceId().toString());
                startActivity(intentWork);
//                result
                break;
            case R.id.tvSetComment:
                commentDialog(result.getOfferedCourseId(), result.getPublishedDate());
                break;


        }
    }

    private void commentDialog(final int courseId , final String courseDate){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

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
                        if (ActionUtils.isInternetConnected(UpComingDetailsActivity.this))
                            presenter.setComment(SharedPrefrencesSingleton.getSharedPrefUser(UpComingDetailsActivity.this).getUserId(),courseId,courseDate,etComment.getText().toString());
                        else
                            ActionUtils.showToast(UpComingDetailsActivity.this,"Connection Error");
                    }
                }
            });

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Hide keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    // dismiss dialog
                    commentDialog.dismiss();
                }
            });
        }
        commentDialog.show();
    }

    @Override
    public void liked(LikeButton likeButton) {
        if (ActionUtils.isInternetConnected(this))
            presenter.setLike(SharedPrefrencesSingleton.getSharedPrefUser(this).getUserId(),result.getOfferedCourseId(),result.getPublishedDate());
           // listener.onLikeClick(courseModel.getOfferedCourseId(),courseModel.getPublishedDate());
        else {
            ActionUtils.showToast(this,"Connection Error");
            likeButton.setLiked(true);
        }

    }

    @Override
    public void unLiked(LikeButton likeButton) {
        if (ActionUtils.isInternetConnected(this))
            presenter.disLike(SharedPrefrencesSingleton.getSharedPrefUser(this).getUserId(),result.getOfferedCourseId(),result.getPublishedDate());
        else {
            ActionUtils.showToast(this,"Connection Error");
            likeButton.setLiked(false);
        }

    }

    @Override
    public void onAnimationEnd(LikeButton likeButton) {

    }


    /******************************  *************************/

}
