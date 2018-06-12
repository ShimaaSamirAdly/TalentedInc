package inc.talentedinc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.presenter.MyOfferedCoursePresenter;
import inc.talentedinc.viewholder.OfferedCoursesViewHolder;

public class MyOfferedCourseViewAdapter extends RecyclerView.Adapter<OfferedCoursesViewHolder> {

    private ArrayList<OfferedCourseDetailed> offeredCourses;
    private View offeredCourseView;
    private OfferedCoursesViewHolder offeredCoursesViewHolder;
    private Context myContext;
    private MyOfferedCoursePresenter offeredCoursesPresenter;
    public static String OFFERED_COURSE_OBJECT = "selected_offered_course";

    public MyOfferedCourseViewAdapter(ArrayList<OfferedCourseDetailed> offeredCourses) {
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
        holder.getOfferedCourseCreatorTxt().setVisibility(View.GONE);
        //set date text
        holder.getOfferedCourseDateTxt().setText(offeredCourses.get(position).getStartDate());
        //request course button
        holder.getRequestOfferedCourseBtn().setVisibility(View.GONE);
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
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    skipMemoryCache(true).
                    placeholder(R.drawable.default_course).
                    into(holder.getOfferedCourseImageView());
        }else {
            Glide.
                    with(myContext).
                    load(R.drawable.default_course).
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

    public void setOfferedCoursesPresenter(MyOfferedCoursePresenter offeredCoursesPresenter) {
        this.offeredCoursesPresenter = offeredCoursesPresenter;
    }
}
