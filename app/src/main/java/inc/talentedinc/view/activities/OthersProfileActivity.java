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
    private FloatingActionButton following;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_profile);

        userId = getIntent().getIntExtra("userId", 0);

        othersProfilePresenter = new OthersProfilePresenterImpl(this, this);

        following = findViewById(R.id.following);
        following.setVisibility(GONE);

//        findViewById(R.id.edit_basic_info).setVisibility(GONE);

    }


    @Override
    protected void onResume() {
        super.onResume();
        othersProfilePresenter.getProfileData(userId);
    }


    public void setUserData(final User user){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();

        following.setVisibility(View.VISIBLE);

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.isFollowing() == true){
                    othersProfilePresenter.unfollowUser(user);
                }else{
                    othersProfilePresenter.followUser(user);
                }
            }
        });

    }
}
