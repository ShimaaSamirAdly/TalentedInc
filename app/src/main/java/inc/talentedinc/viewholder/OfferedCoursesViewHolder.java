package inc.talentedinc.viewholder;

import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import inc.talentedinc.R;

public class OfferedCoursesViewHolder extends RecyclerView.ViewHolder {

    private View offrerdCoursesCardView;
    private TextView offeredCourseNameTxt;
    private TextView offeredCourseCreatorTxt;
    private TextView offeredCourseDateTxt;
    private Button requestOfferedCourseBtn;
    private ImageView offeredCourseImageView;

    public OfferedCoursesViewHolder(View itemView) {
        super(itemView);
        offrerdCoursesCardView = itemView;
    }

    public TextView getOfferedCourseNameTxt() {
        if(offeredCourseNameTxt == null){
            offeredCourseNameTxt = offrerdCoursesCardView.findViewById(R.id.offered_course_name_txt);
        }
        return offeredCourseNameTxt;
    }

    public TextView getOfferedCourseCreatorTxt() {
        if(offeredCourseCreatorTxt == null){
            offeredCourseCreatorTxt = offrerdCoursesCardView.findViewById(R.id.offered_course_creator_name_txt);
        }
        return offeredCourseCreatorTxt;
    }

    public TextView getOfferedCourseDateTxt() {
        if(offeredCourseDateTxt == null){
            offeredCourseDateTxt = offrerdCoursesCardView.findViewById(R.id.offered_course_date_txt);
        }
        return offeredCourseDateTxt;
    }

    public Button getRequestOfferedCourseBtn() {
        if(requestOfferedCourseBtn == null){
            requestOfferedCourseBtn = offrerdCoursesCardView.findViewById(R.id.request_offered_course_btn);
        }
        return requestOfferedCourseBtn;
    }

    public ImageView getOfferedCourseImageView() {
        if(offeredCourseImageView == null){
            offeredCourseImageView = offrerdCoursesCardView.findViewById(R.id.offered_course_img);
        }
        return offeredCourseImageView;
    }

    public View getOffrerdCoursesCardView() {
        return offrerdCoursesCardView;
    }
}
