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
    private TextView txtRate;
    private TextView txtDate;
    private ImageView img;
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
        txtRate = itemView.findViewById(R.id.textView11);
        txtDate = itemView.findViewById(R.id.textView12);
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
        txtName.setText(courseModel.getTitle());
        txtRate.setText(String.valueOf(courseModel.getVote_count()));
        txtDate.setText(courseModel.getRelease_date());
        if(courseModel.getPoster_path() != null ) {
            Glide.with(context).load("http://image.tmdb.org/t/p/w185/"+courseModel.getPoster_path()).centerCrop().placeholder(R.drawable.ic_launcher_background).into(img);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//             starts rate
            case R.id.imageView2:
                listener.onRateClick();
                break;

//                like
            case R.id.thumb_button:
//                if (likeButton.isLiked()){
//                    likeButton.setLiked(false);
//                }else {
//                    likeButton.setLiked(true);
//                }

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
