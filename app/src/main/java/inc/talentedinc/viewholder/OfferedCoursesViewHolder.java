package inc.talentedinc.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import inc.talentedinc.R;

public class OfferedCoursesViewHolder extends RecyclerView.ViewHolder {

    private TextView courseTextView;
    private View coursesCardView;

    public OfferedCoursesViewHolder(View itemView) {
        super(itemView);
        coursesCardView = itemView;
    }

    public TextView getCourseTextView() {
        courseTextView = (TextView)coursesCardView.findViewById(R.id.txt);
        return courseTextView;
    }
}
