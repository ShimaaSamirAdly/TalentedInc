package inc.talentedinc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.presenter.OfferedCoursesPresenter;
import inc.talentedinc.presenter.OfferedCoursesPresenterInt;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.activities.OfferedCourseDetailsActivity;
import inc.talentedinc.viewholder.OfferedCoursesViewHolder;

public class OfferedCoursesViewAdapter extends RecyclerView.Adapter<OfferedCoursesViewHolder> {


    private ArrayList<OfferedCourseDetailed> offeredCourses;
    private View offeredCourseView;
    private OfferedCoursesViewHolder offeredCoursesViewHolder;
    private Context myContext;
    private OfferedCoursesPresenterInt offeredCoursesPresenter;
    public static String OFFERED_COURSE_OBJECT = "selected_offered_course";

    public OfferedCoursesViewAdapter(ArrayList<OfferedCourseDetailed> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }

    @NonNull
    @Override
    public OfferedCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        offeredCourseView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.offered_course_card_design, parent, false);
        offeredCoursesViewHolder = new OfferedCoursesViewHolder(offeredCourseView);
        return offeredCoursesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OfferedCoursesViewHolder holder, final int position) {

        //course name
        holder.getOfferedCourseNameTxt().setText(offeredCourses.get(position).getName());
        //workspace name
        if(offeredCourses.get(position).getHostingWorkSpaceId()!= null){
            holder.getOfferedCourseCreatorTxt().setText(offeredCourses.get(position).getHostingWorkSpaceId().getName());
        }
        //set date text
        holder.getOfferedCourseDateTxt().setText(offeredCourses.get(position).getStartDate());
        //request course button
        holder.getRequestOfferedCourseBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request the course
                Integer offeredCourseId = offeredCourses.get(position).getOfferedCourseId();
                Integer instructorId = SharedPrefrencesSingleton.getSharedPrefUser(myContext).getUserId();
                offeredCoursesPresenter.requestOfferedCourse(offeredCourseId,instructorId);
            }
        });
        //go to details activity
        holder.getOffrerdCoursesCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offeredCoursesPresenter.gotoDetailedCourseView(offeredCourses.get(position));
            }
        });

        if(offeredCourses.get(position).getImageUrl() != null) {
            Glide.
                    with(myContext).
                    load(offeredCourses.get(position).getImageUrl()).
                    centerCrop().
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    skipMemoryCache(true).
                    placeholder(R.drawable.default_course).
                    into(holder.getOfferedCourseImageView());
        }else {
            Glide.
                    with(myContext).
                    load(R.drawable.default_course).
                    centerCrop().
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    skipMemoryCache(true).
                    into(holder.getOfferedCourseImageView());
        }
    }

    @Override
    public int getItemCount() {
        return offeredCourses.size();
    }

    public void setMyContext(Context myContext) {
        this.myContext = myContext;
    }

    public void setOfferedCoursesPresenter(OfferedCoursesPresenterInt offeredCoursesPresenter) {
        this.offeredCoursesPresenter = offeredCoursesPresenter;
    }
}
