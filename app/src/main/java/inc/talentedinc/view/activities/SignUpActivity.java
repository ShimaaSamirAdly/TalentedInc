package inc.talentedinc.view.activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import inc.talentedinc.R;
import inc.talentedinc.adapter.SignUPViewPagerAdapter;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.signup.SignUpPresenter;
import inc.talentedinc.presenter.signup.SignUpPresenterImpl;
import inc.talentedinc.utilitis.SignupValidator;
import inc.talentedinc.view.fragmnts.ProfileFragment;

public class SignUpActivity extends AppCompatActivity {

    /******************************mina*************************/
    private TextView dobTxtVw;
    private Button nextBtn;
    private User signedUpUser = new User();
    private ViewPager signUpViewPager;
    private SignUPViewPagerAdapter signUPViewPagerAdapter;
    private SignupValidator signupValidator = SignupValidator.getValidationInstance();

    /***********************************************************/

    /*****************************Shimaa***********************/

    private SignUpPresenter presenter;

    /**********************************************************/

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

        Intent intent =getIntent();
        if(intent.getSerializableExtra(LoginActivity.INTENT_USER)!= null){
            signedUpUser = (User) intent.getSerializableExtra(LoginActivity.INTENT_USER);
            signUpViewPager.setCurrentItem(2);
        }

        /***********************************************************/
    }

    /******************************mina*************************/

    private void performNext() {
        switch (signUpViewPager.getCurrentItem()) {
            case 0:
                if(getFirstSignupData()) {
                    signUpViewPager.setCurrentItem(signUpViewPager.getCurrentItem() + 1);
                }
                break;

            case 1:
                if(getSecondSignupData()) {
                    signUpViewPager.setCurrentItem(signUpViewPager.getCurrentItem() + 1);
                }
                break;
/************************************************Shimaa*************************************/
            case 2:
                getThirdSignUpData();
                presenter = new SignUpPresenterImpl(this);
                presenter.insertUser(signedUpUser);
                switchToProfile();
                break;
            /********************************************************************************************/
        }
    }

    private boolean getFirstSignupData() {
        User tempUser = signUPViewPagerAdapter.getFirstSignUpFragment().getUser();
        if (signupValidator.validatemail(tempUser.getEmail())) {
            signedUpUser.setEmail(tempUser.getEmail());
            if (signupValidator.validatePassword(tempUser.getPassword())) {
                signedUpUser.setPassword(tempUser.getPassword());
                if (signupValidator.validatePhone(tempUser.getPhone())) {
                    signedUpUser.setPhone(tempUser.getPhone());
                }else {
                    Toast.makeText(this, "invalid phone number", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(this, "password mus be at least 6 characters", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        }
        signedUpUser.setCity(tempUser.getCity());
        return true;
    }

    private boolean getSecondSignupData() {
        User tempUser = signUPViewPagerAdapter.getSecondSignUpFragment().getUser();
        if(signupValidator.validateNotEmptyString(tempUser.getFirstName())){
            signedUpUser.setFirstName(tempUser.getFirstName());
            if(signupValidator.validateNotEmptyString(tempUser.getLastName())){
                signedUpUser.setLastName(tempUser.getLastName());
                if(signupValidator.validateNotEmptyString(tempUser.getUserDob())){
                    signedUpUser.setUserDob(tempUser.getUserDob());
                }else{
                    Toast.makeText(this,"you have to enter a valid date of birth",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(this,"you have to enter you last name",Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(this,"you have to enter you first name",Toast.LENGTH_SHORT).show();
            return false;
        }
        signedUpUser.setGender(tempUser.getGender());
        return true;
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

    /********************Shimaa*********************************/

    private void getThirdSignUpData() {

        User user = signUPViewPagerAdapter.getThirdSignUpFragment().getUser();
        signedUpUser.setCategoryCollection(user.getCategoryCollection());
        signedUpUser.setUserType(user.getUserType());
    }


    public void switchToProfile() {

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user", (Serializable) signedUpUser);
        intent.putExtra("direction", "profile");
        startActivity(intent);
    }

    /************************************************************/
}
