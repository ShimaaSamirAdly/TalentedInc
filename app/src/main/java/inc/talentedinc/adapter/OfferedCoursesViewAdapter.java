package inc.talentedinc.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.viewholder.OfferedCoursesViewHolder;

public class OfferedCoursesViewAdapter extends RecyclerView.Adapter<OfferedCoursesViewHolder> {


    private ArrayList<OfferedCourse> offeredCourses;
    private View offeredCourseView;
    private OfferedCoursesViewHolder offeredCoursesViewHolder;

    public OfferedCoursesViewAdapter(ArrayList<OfferedCourse> offeredCourses) {
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
    public void onBindViewHolder(@NonNull OfferedCoursesViewHolder holder, int position) {

        holder.getOfferedCourseNameTxt().setText(offeredCourses.get(position).getName());
        if(offeredCourses.get(position).getCourseCreator()!= null){
            holder.getOfferedCourseCreatorTxt().setText(offeredCourses.get(position).getCourseCreator().getFirstName()+" "+offeredCourses.get(position).getCourseCreator().getLastName());
        }else {
            holder.getOfferedCourseCreatorTxt().setText(offeredCourses.get(position).getHostingWorkSpaceId().getName());
        }
        holder.getOfferedCourseDateTxt().setText(offeredCourses.get(position).getStartDate());
        holder.getRequestOfferedCourseBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request the course
            }
        });
    }

    @Override
    public int getItemCount() {
        return offeredCourses.size();
    }
}
