package inc.talentedinc.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import inc.talentedinc.R;
import inc.talentedinc.listener.HomeListener;
import inc.talentedinc.model.Result;
import inc.talentedinc.utilitis.EndlessAdapter;
import inc.talentedinc.viewholder.UpComingCoursesViewHolder;

/**
 * Created by asmaa on 05/21/2018.
 */

public class HomeAdapter extends EndlessAdapter<Result> {

    public static final String UPCOMING ="upcoming";
    public static final String HISTORY ="history";

    private String s;
    private HomeListener listener;
    public HomeAdapter(String s ,LinearLayoutManager linearLayoutManager,HomeListener listener) {
        super(linearLayoutManager);
        this.s=s;
        this.listener=listener;
        setLoadingView(R.layout.item_loader);
    }

    @Override
    protected RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card_design, parent, false);
        return new UpComingCoursesViewHolder(s,view,listener,parent.getContext());
    }

    @Override
    protected void bindMyViewController(RecyclerView.ViewHolder holder, int position) {
        ((UpComingCoursesViewHolder)holder).setData(getDataItem(position));
    }
}
