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

import java.io.IOException;
import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user = SharedPrefrencesSingleton.getSharedPrefUser(this);

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

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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


    public void emptyFieldsError(){

        Toast.makeText(this, "Fill All Fields", Toast.LENGTH_LONG).show();
    }

    public void setInstructor(Instructor instructor){

//        user.setInstructor(instructor);

        instructor.setUserId(user.getUserId());


        presenter.becomeInstructor(instructor);
    }

    public void switchToHome(){
        user.setUserType(1);
        SharedPrefrencesSingleton.setSharedPrefUser(this, user);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
