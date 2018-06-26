package inc.talentedinc.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import inc.talentedinc.R;
import inc.talentedinc.adapter.MultiViewAdapter;
import inc.talentedinc.listener.AdapterListener;
import inc.talentedinc.model.Instructor;
import inc.talentedinc.model.User;
import inc.talentedinc.model.recycleview.RecycleModel;
import inc.talentedinc.presenter.BecomeInstructorPresenter;
import inc.talentedinc.presenter.BecomeInstructorPresenterImpl;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;

public class BecomeInstructorActivity extends AppCompatActivity implements AdapterListener {

    private RecyclerView mRecyclerView;
    private ArrayList<RecycleModel> recycleModels;
    private MultiViewAdapter adapter;
    private Button submitInstructor;
    private User user;
    private final int PICK_REQUEST = 77;
    private ImageView img;
    private int imgIndex;
    private BecomeInstructorPresenter presenter;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private Instructor instructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user = SharedPrefrencesSingleton.getSharedPrefUser(this);
        instructor = new Instructor();

        presenter = new BecomeInstructorPresenterImpl(this, this);

        recycleModels = new ArrayList<>();
        recycleModels.add(new RecycleModel (RecycleModel.SKILLS_TYPE, new ArrayList<String>(), null, null));
        recycleModels.add(new RecycleModel(RecycleModel.PORTOFOLIO_TYPE, null, null, new ArrayList<Uri>()));
        adapter = new MultiViewAdapter(recycleModels,this, this, presenter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

//        ..........................................mina.......................................

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

//        ....................................................................................................
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("res", "onResult");

        if(requestCode == PICK_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            Uri filePath = data.getData();
            adapter.addImg(filePath, imgIndex);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img.setImageBitmap(bitmap);
//                profilePresenter.uploadImage(filePath, user);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClickedImage(int i, ImageView img) {
        imgIndex = i;
        this.img = img;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_REQUEST);
    }

    @Override
    public Button getFBButton() {
        return loginButton;
    }


    public void emptyFieldsError(){

        Toast.makeText(this, "Fill All Fields", Toast.LENGTH_LONG).show();
    }

    public void setInstructor(Instructor instructor){

//        user.setInstructor(instructor);

        this.instructor = instructor;
        this.instructor.setUserId(user.getUserId());
        this.instructor.setUser(user);



//        presenter.becomeInstructor(instructor);
    }

    public void switchToHome(){
        user.setUserType(1);
        SharedPrefrencesSingleton.setSharedPrefUser(this, user);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

//    ..............................................mina...........................................

    //make graph call with user access token to get profile data
    private void makeGraphCall(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        if (object != null) {
                            //user to send to interests activity
                            createUser(object, loginResult);
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }


    // create user from facebook data to pass to interests activity
    private void createUser(JSONObject userJson, LoginResult loginResult) {

        String fbId = null;
        try {
            fbId = userJson.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loginResult.getAccessToken().getToken();

//        presenter.setFaceBookData(fbId, loginResult.getAccessToken().getToken());
        this.instructor.getUser().setFbId(fbId);
        this.instructor.getUser().setFbToken(loginResult.getAccessToken().getToken());
//        Log.i("fbInstructor", fbId);
        Log.i("fbInstructor", this.instructor.getUser().getFirstName());

        presenter.becomeInstructor(this.instructor);

    }

//    ...................................................................................................
}
