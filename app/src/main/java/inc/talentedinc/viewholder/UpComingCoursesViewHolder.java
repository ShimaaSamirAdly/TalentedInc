package inc.talentedinc.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import inc.talentedinc.R;
import inc.talentedinc.listener.HomeListener;
import inc.talentedinc.model.Result;

/**
 * Created by asmaa on 05/21/2018.
 */

public class UpComingCoursesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private Context context;
    private HomeListener listener;
    private View itemView;
    private TextView txtName;
    private TextView txtRate;
    private TextView txtDate;
    private CircleImageView img;
    private Result courseModel;
//    private LikeButton likeButton;
    public UpComingCoursesViewHolder(View itemView , HomeListener listener , Context context) {
        super(itemView);
        this.context=context;
        this.itemView= itemView;
        this.listener=listener;
        itemView.setOnClickListener(this);
        initializeViews();
    }
    private void initializeViews() {
        txtName = itemView.findViewById(R.id.tvname);
        txtRate = itemView.findViewById(R.id.tvRate);
        txtDate = itemView.findViewById(R.id.tvDate);
        img = itemView.findViewById(R.id.img);
//        likeButton = itemView.findViewById(R.id.thumb_button);
//        likeButton.setOnClickListener(this);
//        likeButton.setOnAnimationEndListener(context);
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
        listener.onCourseClicked(courseModel);
    }
}
