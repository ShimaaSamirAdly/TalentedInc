package inc.talentedinc.view.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import inc.talentedinc.R;
import inc.talentedinc.adapter.SignUPViewPagerAdapter;
import inc.talentedinc.model.User;

public class SignUpActivity extends AppCompatActivity {

    /******************************mina*************************/
    private TextView dobTxtVw;
    private Button nextBtn;
    private User signedUpUser = new User();
    private ViewPager signUpViewPager;
    private SignUPViewPagerAdapter signUPViewPagerAdapter;

    /***********************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /******************************mina*************************/


        nextBtn = (Button) findViewById(R.id.nextBtn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performNext();
            }
        });

        signUpViewPager = (ViewPager) findViewById(R.id.sign_up_view_pager);
        signUPViewPagerAdapter = new SignUPViewPagerAdapter(getSupportFragmentManager());
        signUpViewPager.setAdapter(signUPViewPagerAdapter);

        /***********************************************************/
    }

    /******************************mina*************************/

    private void performNext() {
        switch (signUpViewPager.getCurrentItem()){
            case 0:
                getFirstSignupData();
                signUpViewPager.setCurrentItem(signUpViewPager.getCurrentItem() + 1);
                break;

            case 1:
                getSecondSignupData();
                signUpViewPager.setCurrentItem(signUpViewPager.getCurrentItem() + 1);
                break;

            case 2:
                break;
        }
    }

    private void getFirstSignupData() {
        User tempUser = signUPViewPagerAdapter.getFirstSignUpFragment().getUser();
        signedUpUser.setEmail(tempUser.getEmail());
        signedUpUser.setPassword(tempUser.getPassword());
        signedUpUser.setPhone(tempUser.getPhone());
        signedUpUser.setCity(tempUser.getCity());
    }

    private void getSecondSignupData(){
        User tempUser = signUPViewPagerAdapter.getSecondSignUpFragment().getUser();
        signedUpUser.setFirstName(tempUser.getFirstName());
        signedUpUser.setLastName(tempUser.getLastName());
        signedUpUser.setUserDob(tempUser.getUserDob());
        signedUpUser.setGender(tempUser.getGender());
    }

    @Override
    public void onBackPressed() {
        if (signUpViewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            signUpViewPager.setCurrentItem(signUpViewPager.getCurrentItem() - 1);
        }
    }

    /***********************************************************/
}
