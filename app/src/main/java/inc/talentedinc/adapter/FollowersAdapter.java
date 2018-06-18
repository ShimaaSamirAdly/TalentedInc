package inc.talentedinc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.model.Followers;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.FollowersPresenter;
import inc.talentedinc.presenter.FollowersPresenterImpl;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.activities.OthersProfileActivity;
import inc.talentedinc.viewholder.FollowersViewHolder;
import inc.talentedinc.viewholder.OfferedCoursesViewHolder;

/**
 * Created by MMM on 6/9/2018.
 */

public class FollowersAdapter extends RecyclerView.Adapter {

    private ArrayList<Followers> followers;
    private View followersView;
    private FollowersViewHolder followersViewHolder;
    private FollowersPresenter followersPresenter;
    private Context context;
    private User currentUser;

    public FollowersAdapter(Context context, ArrayList<Followers> followers, FollowersPresenter followersPresenter){
        this.context = context;
        this.followers = followers;
        this.followersPresenter = followersPresenter;
        currentUser = SharedPrefrencesSingleton.getSharedPrefUser(context);
        Log.i("followersNO", "adapteeeer"+ followers.size());
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i("followersNO", "createHolder"+ followers.size());

        followersView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.followers_layout, parent, false);
        followersViewHolder = new FollowersViewHolder(followersView);
        return followersViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        Log.i("followersNO", ""+ followers.size());

        ((FollowersViewHolder)holder).getFollowerName().setText(followers.get(position).getFirstName()+" "+followers.get(position).getLastName());

        if(followers.get(position).getImgUrl() != null) {
            Glide.with(context)
                    .load(followers.get(position).getImgUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((FollowersViewHolder) holder).getFollowerImg());
        }else{
            ((FollowersViewHolder) holder).getFollowerImg().setImageResource(R.drawable.com_facebook_profile_picture_blank_portrait);
        }

        if(currentUser.getUserId() == followers.get(position).getUserId()){
            ((FollowersViewHolder)holder).getShowProfile().setVisibility(View.GONE);
        }else {
            ((FollowersViewHolder) holder).getShowProfile().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                followersPresenter.unfollow(followers.get(position).getUserId());
                    Intent intent = new Intent(context, OthersProfileActivity.class);
                    intent.putExtra("userId", followers.get(position).getUserId());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        Log.i("followersNO", "getItem"+ followers.size());
        return followers.size();
    }
}
