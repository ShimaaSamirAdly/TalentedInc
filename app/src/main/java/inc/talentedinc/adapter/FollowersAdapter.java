package inc.talentedinc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.FollowersPresenter;
import inc.talentedinc.presenter.FollowersPresenterImpl;
import inc.talentedinc.view.activities.OthersProfileActivity;
import inc.talentedinc.viewholder.FollowersViewHolder;
import inc.talentedinc.viewholder.OfferedCoursesViewHolder;

/**
 * Created by MMM on 6/9/2018.
 */

public class FollowersAdapter extends RecyclerView.Adapter {

    private ArrayList<User> followers;
    private View followersView;
    private FollowersViewHolder followersViewHolder;
    private FollowersPresenter followersPresenter;
    private Context context;

    public FollowersAdapter(Context context, ArrayList<User> followers, FollowersPresenter followersPresenter){
        this.context = context;
        this.followers = followers;
        this.followersPresenter = followersPresenter;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        followersView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.followers_layout, parent, false);
        followersViewHolder = new FollowersViewHolder(followersView);
        return followersViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((FollowersViewHolder)holder).getFollowerName().setText(followers.get(position).getFirstName()+" "+followers.get(position).getFirstName());

        Glide.with(context)
                .load(followers.get(position).getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(((FollowersViewHolder)holder).getFollowerImg());

        ((FollowersViewHolder)holder).getShowProfile().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                followersPresenter.unfollow(followers.get(position).getUserId());
                Intent intent = new Intent(context, OthersProfileActivity.class);
                intent.putExtra("userId", followers.get(position).getUserId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
