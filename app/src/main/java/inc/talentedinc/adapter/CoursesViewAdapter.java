package inc.talentedinc.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.viewholder.OfferedCoursesViewHolder;

public class CoursesViewAdapter extends RecyclerView.Adapter<OfferedCoursesViewHolder> {


    private ArrayList<MinaCourse> offeredCourses;
    private View offeredCourseView;
    private OfferedCoursesViewHolder offeredCoursesViewHolder;

    public CoursesViewAdapter(ArrayList<MinaCourse> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }

    @NonNull
    @Override
    public OfferedCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        offeredCourseView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card_view, parent, false);
        offeredCoursesViewHolder = new OfferedCoursesViewHolder(offeredCourseView);
        return offeredCoursesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OfferedCoursesViewHolder holder, int position) {
        holder.getCourseTextView().setText("MinaCourse : "+offeredCourses.get(position).getPublishedCoursePK().getPublishedCourseId());
    }

    @Override
    public int getItemCount() {

        return offeredCourses.size();
    }
}
