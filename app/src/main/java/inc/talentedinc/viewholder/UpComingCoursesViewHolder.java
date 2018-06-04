package inc.talentedinc.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by asmaa on 05/21/2018.
 */

public class UpComingCoursesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, OnAnimationEndListener, OnLikeListener {

    private Context context;
    private HomeListener listener;
    private View itemView;
    private TextView txtName;
    private TextView txtonInstructorName;
    private TextView txtDate , tvLikes , tvComments;
    private CircleImageView img;
    private Result courseModel;
    private ImageView imgRte;
    private LikeButton likeButton;
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
        if (s.equals(HomeAdapter.HISTORY)){
            imgRte.setVisibility(View.VISIBLE);
        }
        imgRte.setOnClickListener(this);
        likeButton = itemView.findViewById(R.id.thumb_button);
        likeButton.setOnLikeListener(this);
        likeButton.setOnAnimationEndListener(this);
    }

    public void setData(Result course){
        this.courseModel=course;
        txtName.setText(courseModel.getName());
        txtonInstructorName.setText("Asmaa");
        txtDate.setText(courseModel.getStartDate());
        tvLikes.setText(String.valueOf(courseModel.getNumberOfLikes()));
        tvComments.setText(String.valueOf(courseModel.getNumberOfComments()));
        if(courseModel.getImageUrl() != null ) {
          //  Glide.with(context).load("http://image.tmdb.org/t/p/w185/"+courseModel.getImageUrl()).centerCrop().placeholder(R.drawable.ic_launcher_background).into(img);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//             starts rate
            case R.id.imageView2:
                listener.onRateClick();
                break;
//                instractur Name
            case R.id.textView15:
                listener.onInstructorClick(courseModel.getInstructorId().getUserId());
                break;
//                comment
            case R.id.editText:
                listener.onCommentClick();

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
        listener.onLikeClick();

        //API

    }

    @Override
    public void unLiked(LikeButton likeButton) {
        listener.onLikeClick();

        //API

    }
}
