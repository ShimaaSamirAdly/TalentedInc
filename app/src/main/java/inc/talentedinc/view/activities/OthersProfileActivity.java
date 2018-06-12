package inc.talentedinc.view.activities;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;

import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.adapter.PortofolioAdapter;
import inc.talentedinc.adapter.SignUpInterestsAdapter;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.profile.OthersProfilePresenterImpl;
import inc.talentedinc.view.customviews.ExpandableHeightGridView;
import inc.talentedinc.view.fragmnts.ProfileFragment;

import static android.view.View.GONE;

public class OthersProfileActivity extends AppCompatActivity {

    private int userId;
    private OthersProfilePresenterImpl othersProfilePresenter;
    private FloatingActionButton follow;
    private FloatingActionButton unfollow;
    FragmentTransaction fragmentTransaction;
    ProfileFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_profile);

        userId = getIntent().getIntExtra("userId", 0);

        othersProfilePresenter = new OthersProfilePresenterImpl(this, this);

        follow = findViewById(R.id.follow);
        unfollow = findViewById(R.id.unfollow);
        follow.setVisibility(GONE);
        unfollow.setVisibility(GONE);

//        findViewById(R.id.edit_basic_info).setVisibility(GONE);

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onresume", "hhhh");
        othersProfilePresenter.getProfileData(userId);
    }


    public void setUserData(final User user){
        Log.i("followw", ""+ user.getUserId());

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();


        final User visitedUser = user;
        Log.i("followw", ""+ visitedUser.getUserId());
//        following.setVisibility(View.VISIBLE);

        if(user.isFollowing() == true){
            unfollow.setVisibility(View.VISIBLE);
            follow.setVisibility(GONE);
        }else{
            follow.setVisibility(View.VISIBLE);
            unfollow.setVisibility(View.GONE);
        }

        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("followw", ""+ visitedUser.getUserId());
                othersProfilePresenter.followUser(visitedUser);
            }
        });

        unfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                othersProfilePresenter.unfollowUser(visitedUser);
            }
        });

    }

    public void refreshActivity(){
        Log.i("refreshActiv", "refresh");
        finish();
        startActivity(getIntent());
    }
}
