package inc.talentedinc.view.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import inc.talentedinc.R;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.UpdateInterestsPresenter;
import inc.talentedinc.presenter.UpdateInterestsPresenterImpl;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.fragmnts.ProfileFragment;
import inc.talentedinc.view.fragmnts.ThirdSignUpFragment;

public class UpdateInterestsActivity extends AppCompatActivity {

    private User user;
    private Button save;
    private UpdateInterestsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_interests);

//        user = (User) getIntent().getSerializableExtra("user");
        user = SharedPrefrencesSingleton.getSharedPrefUser(this);
        presenter = new UpdateInterestsPresenterImpl(this, this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        final ThirdSignUpFragment fragment = new ThirdSignUpFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        fragmentTransaction.add(R.id.interests, fragment);
        fragmentTransaction.commit();

        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = fragment.getUpdatedUser();
                presenter.updateUser(user);
            }
        });

    }


    public void switchToHome(){

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
