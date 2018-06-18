package inc.talentedinc.view.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import inc.talentedinc.R;
import inc.talentedinc.model.User;
import inc.talentedinc.model.UserLogin;
import inc.talentedinc.model.response.MainResponse;
import inc.talentedinc.presenter.LoginPresenter;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.LoginView ,
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

 //*************************************************************************************************//

//---------------------------------------Alaa------------------------------------------------------//

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private static final int RC_SIGN_IN = 007;
    private SignInButton btnSignIn;
    private TextView email ;
    private TextView password ;
    private LoginPresenter loginPresenter ;
    private UserLogin userLogin ;
    private Button loginBtn;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private User gmailUser ;
    private Button signUp ;
    private User signedUpUser ;


//--------------------------------------------------------------------------------------------------//

    /******************************************mina************************************/
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    public static String INTENT_USER = "user_from_social";

    /***************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---------------------------------Alaa--------------------------------------------------//


        signedUpUser = SharedPrefrencesSingleton.getSharedPrefUser(this);
        if(signedUpUser != null){
            Intent intent = new Intent(this , HomeActivity.class);
            startActivity(intent);
            finish();
        }




        btnSignIn = findViewById(R.id.sign_in_button);
        signUp = findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
                finish();

            }
        });


        btnSignIn.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        userLogin = new UserLogin();

        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);



        //------------------------------------------------------------------------------------//

        /******************************************mina************************************/


        //facebook login
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.fb_login);
        //setting permissions
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday"));
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                makeGraphCall(loginResult);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
                Log.i("fbfb",exception.toString());
                exception.printStackTrace();
            }
        });


        // to generate hash key


                try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "inc.talentedinc",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        /***************************************************************************************/

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //------------------------------------------------------------------------------------//
    //------------------------------------Alaa--------------------------------------------//


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("Status", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            gmailUser = new User();
            gmailUser.setEmail(acct.getEmail());
            gmailUser.setFirstName(acct.getDisplayName());
            gmailUser.setLastName(acct.getFamilyName());
            gmailUser.setImgUrl(acct.getPhotoUrl().toString());
            gmailUser.setGoogleToken(acct.getIdToken());
            gmailUser.setGoogleId(acct.getId());
            completeSignup(gmailUser);
            Log.i("response", "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String email = acct.getEmail();

            Log.i("userData", "Name: " + personName + ", email: " + email
                    );


        } else {
            // Signed out, show unauthenticated UI.
            Log.i("Error",result.getStatus().toString());
        }
    }
    //------------------------------------------------------------------------------------//

//---------------------------------------Alaa------------------------------------------------------//

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.sign_in_button:
                signIn();

                break;

            case  R.id.login_btn :

                email = findViewById(R.id.email_text);
                password = findViewById(R.id.password_text);


                if (email.getText() != null && email.getText().toString().matches(emailPattern) && password.getText() != null){

                    loginPresenter = new LoginPresenter();

                    userLogin.setEmail(email.getText().toString());

                    userLogin.setPassword( password.getText().toString());
                    Log.i("das","ethabb");
                    loginPresenter.setView( userLogin, this);

                }
                else {
                    Toast.makeText(this,"please a valid data",Toast.LENGTH_LONG).show();
                }
                break;


        }
    }
    //------------------------------------------------------------------------------------//
    //---------------------------------------Alaa-------------------------------------------------//

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.i("onConnectionFailed", "onConnectionFailed:" + connectionResult);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    //------------------------------------------------------------------------------------//

    @Override
    protected void onStart() {
        super.onStart();

        //---------------------------------------Alaa------------------------------------------------------//

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d("userCash", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            //showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
//---------------------------------------------------------------------------------------------------//

    }

//---------------------------------------Alaa------------------------------------------------------//

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }


    @Override
    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void sendUserData(User response) {

        if(response !=null) {
            Log.i("userEmail", response.getEmail());
            SharedPrefrencesSingleton.setSharedPrefUser(this,response);

            Intent sendToHome = new Intent(this, HomeActivity.class);
            startActivity(sendToHome);
        }

    }

    @Override
    public void loginInvalid() {
        Toast.makeText(this,"Please enter a valid Email or Password",Toast.LENGTH_SHORT ).show();
    }

    //---------------------------End of Alaa-----------------------------------------------------//
    //**********************************************************************************************//

    /******************************************mina************************************/

    //make graph call with user access token to get profile data
    private void makeGraphCall(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        if (object != null) {
                            //user to send to interests activity
                            User user = createUser(object, loginResult);
                            //go to categories to complete sign up
                            completeSignup(user);
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    // create user from facebook data to pass to interests activity
    private User createUser(JSONObject userJson, LoginResult loginResult) {

        User user = new User();
        try {
            user.setEmail(userJson.getString("email"));
            user.setFirstName(userJson.getString("name"));
            user.setFbId(userJson.getString("id"));
            user.setImgUrl("http://graph.facebook.com/" + userJson.getString("id") + "/picture?type=large");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //user.setFbToken(loginResult.getAccessToken().getToken());

        return user;
    }


    private void completeSignup(User user){

        Intent intent = new Intent(this,SignUpActivity.class);
        intent.putExtra(LoginActivity.INTENT_USER,user);
        startActivity(intent);
        finish();
    }
    /***************************************************************************************/


}
