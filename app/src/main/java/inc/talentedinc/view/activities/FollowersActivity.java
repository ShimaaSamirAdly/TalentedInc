package inc.talentedinc.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.adapter.FollowersAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        recyclerView = findViewById(R.id.recycleView);

        followersPresenter = new FollowersPresenterImpl(this, this);

        flag = getIntent().getIntExtra("type", 0);

        if(flag == 0){
            followersPresenter.getFollowers();
        }else{
            followersPresenter.getFollowing();
        }



//        followersAdapter = new FollowersAdapter(this, )
    }

    public void setData(ArrayList<User> followers){

        followersAdapter = new FollowersAdapter(this, followers, followersPresenter);
        recyclerView.setAdapter(followersAdapter);
    }


}
