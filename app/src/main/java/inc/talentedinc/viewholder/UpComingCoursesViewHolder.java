package inc.talentedinc.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnAnimationEndListener;
import com.like.OnLikeListener;

import de.hdodenhof.circleimageview.CircleImageView;
import inc.talentedinc.R;
import inc.talentedinc.adapter.HomeAdapter;
import inc.talentedinc.listener.HomeListener;
import inc.talentedinc.model.Result;
import inc.talentedinc.utilitis.ActionUtils;

/**
 * Created by asmaa on 05/21/2018.
 */

public class UpComingCoursesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, OnAnimationEndListener, OnLikeListener {

    private Context context;
    private HomeListener listener;
    private View itemView;
    private TextView txtName;
    private TextView txtonInstructorName;
    private TextView txtDate ;
    public static TextView tvLikes , tvComments;
    private CircleImageView img;
    private Result courseModel;
    private ImageView imgRte;
    public static LikeButton likeButton;
    private TextView etComment;
    private String s;
    public UpComingCoursesViewHolder(String s,View itemView , HomeListener listener , Context context) {
        super(itemView);
        this.s=s;
        this.context=context;
        this.itemView= itemView;
        this.listener=listener;
        itemView.setOnClickListener(this);
        initializeViews();
    }
    private void initializeViews() {
        txtName = itemView.findViewById(R.id.textView10);
        txtonInstructorName = itemView.findViewById(R.id.textView15);
        txtonInstructorName.setOnClickListener(this);
        txtDate = itemView.findViewById(R.id.textView20);
        tvLikes =itemView.findViewById(R.id.textView13);
        tvComments =itemView.findViewById(R.id.textView14);

        img = itemView.findViewById(R.id.imageView);
        img.setOnClickListener(this);
        etComment=itemView.findViewById(R.id.editText);
        etComment.setOnClickListener(this);
        imgRte = itemView.findViewById(R.id.imageView2);

        imgRte.setOnClickListener(this);
        likeButton = itemView.findViewById(R.id.thumb_button);
        likeButton.setOnLikeListener(this);
        likeButton.setOnAnimationEndListener(this);

    }

    public void setData(Result course){
        this.courseModel=course;
        txtName.setText(courseModel.getName());
        txtonInstructorName.setText(courseModel.getNameOfInstructor());
        txtDate.setText(courseModel.getStartDate());
        tvLikes.setText(String.valueOf(courseModel.getNumberOfLikes()));
        tvComments.setText(String.valueOf(courseModel.getNumberOfComments()));
//        if(courseModel.getImageUrl()!=null ) {
            Glide.with(context).load(courseModel.getImageUrl()).centerCrop().placeholder(R.drawable.default_course).into(img);
//        }
        if (courseModel.getLiked()){
            likeButton.setLiked(true);
        }else {
            likeButton.setLiked(false);
        }

        Log.i("TESTTEST", course.getOfferedCourseId()+"");
        Log.i("TESTTEST", course.getPublishedDate()+"");
        if (s.equals(HomeAdapter.HISTORY)){
            imgRte.setVisibility(View.VISIBLE);
            if (course.isRated())
                imgRte.setVisibility(View.GONE);
            else
                imgRte.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//             starts rate
            case R.id.imageView2:
                listener.onRateClick(courseModel.getOfferedCourseId(),courseModel.getPublishedDate());
                break;
//                instractur Name
            case R.id.textView15:
                listener.onInstructorClick(courseModel.getInstructorId().getUserId());
                break;
//                comment
            case R.id.editText:
                listener.onCommentClick(courseModel.getOfferedCourseId(),courseModel.getPublishedDate());
                break;
                // to details
            case R.id.imageView:
                listener.onCourseClicked(courseModel);
                break;
        }
    }

    @Override
    public void onAnimationEnd(LikeButton likeButton) {
    }

    @Override
    public void liked(LikeButton likeButton) {
        if (ActionUtils.isInternetConnected(context))
        listener.onLikeClick(courseModel.getOfferedCourseId(),courseModel.getPublishedDate());
        else {
            ActionUtils.showToast(context,"Connection Error");
            likeButton.setLiked(true);
        }
        //API
    }

    @Override
    public void unLiked(LikeButton likeButton) {
        if (ActionUtils.isInternetConnected(context))
            listener.onDisLikeClick(courseModel.getOfferedCourseId(),courseModel.getPublishedDate());
        else {
            ActionUtils.showToast(context,"Connection Error");
            likeButton.setLiked(false);
        }
        //API
    }
}
