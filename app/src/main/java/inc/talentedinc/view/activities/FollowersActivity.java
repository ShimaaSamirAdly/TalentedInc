package inc.talentedinc.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.adapter.FollowersAdapter;
import inc.talentedinc.model.Followers;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.FollowersPresenter;
import inc.talentedinc.presenter.FollowersPresenterImpl;
import inc.talentedinc.presenter.profile.OthersProfilePresenter;
import inc.talentedinc.presenter.profile.OthersProfilePresenterImpl;

public class FollowersActivity extends AppCompatActivity {

    private FollowersAdapter followersAdapter;
    private FollowersPresenter followersPresenter;
    private RecyclerView recyclerView;
    private int flag;
    LinearLayoutManager coursesLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        recyclerView = findViewById(R.id.recycleView);
        coursesLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(coursesLayoutManager);

        followersPresenter = new FollowersPresenterImpl(this, this);

        flag = getIntent().getIntExtra("type", 0);
        int userId = getIntent().getIntExtra("userId",-1);

        if(flag == 0){
            followersPresenter.getFollowers(userId);
        }else{
            followersPresenter.getFollowing(userId);
        }



//        followersAdapter = new FollowersAdapter(this, )
    }

    public void setData(ArrayList<Followers> followers){

        Log.i("followersNO", "setData  "+ followers.size());

        followersAdapter = new FollowersAdapter(this, followers, followersPresenter);
        recyclerView.setAdapter(followersAdapter);
//        followersAdapter.notifyDataSetChanged();
    }


}
