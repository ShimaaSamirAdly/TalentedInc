package inc.talentedinc.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import inc.talentedinc.R;
import inc.talentedinc.model.User;

public class Signin extends AppCompatActivity {

    /******************************************mina************************************/
    private CallbackManager callbackManager;
    private LoginButton loginButton;

    /***************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        /******************************************mina************************************/

        // setting up a button to go to sign up
        Button gotoSignUpBtn = (Button) findViewById(R.id.goto_signup_btn);
        gotoSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });

        //facebook login
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
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
                exception.printStackTrace();
            }
        });


        // to generate hash key


//                try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "inc.talentedinc",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

        /***************************************************************************************/
    }

    /******************************************mina************************************/

    //make graph call with user access token to get profile data
    private void makeGraphCall(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        if (object != null) {
                            //user to send to interests activity
                            User user = createUser(object, loginResult);
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
        user.setFbToken(loginResult.getAccessToken().getToken());

        return user;
    }
    /***************************************************************************************/
}
