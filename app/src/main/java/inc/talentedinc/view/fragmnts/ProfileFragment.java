package inc.talentedinc.view.fragmnts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.adapter.PortofolioAdapter;
import inc.talentedinc.adapter.SignUpInterestsAdapter;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.InstructorVideos;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.profile.ProfilePresenter;
import inc.talentedinc.presenter.profile.ProfilePresenterImpl;

import static android.app.Activity.RESULT_OK;

import inc.talentedinc.view.activities.HomeActivity;


public class ProfileFragment extends Fragment {

    /****************************** asmaa *************************/

    /******************************Shimaa**************************/

    private ImageView profileImage;
    private ImageView pickImage;
    private TextView userName;
    private TextView followers;
    private TextView following;
    private ImageView editBasicInfo;
    private ImageView updateUser;
    private EditText email;
    private EditText phone;
    private EditText dob;
    private EditText location;
    private ImageView editInterests;
    private GridView interestsGridView;
    private TextView portofolioText;
    private ImageView editPortofolio;
    private GridView portofolioGridView;
    private TextView videosText;
    private ImageView editVideos;
    private LinearLayout videosLayout;
    private TextView skillsText;
    private ImageView editSkills;
    private LinearLayout skillsLayout;
    private User user;
    private SignUpInterestsAdapter interestsAdapter;
    private PortofolioAdapter portofolioAdapter;
    private Uri filePath;
    private final int PICK_REQUEST = 77;
    private Bitmap theBitmap;
    private Uri imgUrl;
    private ProfilePresenter profilePresenter;


    /*****************************************************************/

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_profile, container, false);

//        ***************************** Asmaa ***************************************
        ((HomeActivity)getActivity()).whichFragment(HomeActivity.PROGILE);

//        ********************************************************************

        /******************************Shimaa*******************************************/


        profileImage = view.findViewById(R.id.profileImage);
        pickImage = view.findViewById(R.id.pickImage);
        userName = view.findViewById(R.id.userName);
        followers = view.findViewById(R.id.followers);
        following = view.findViewById(R.id.following);
        updateUser = view.findViewById(R.id.updateUser);
        editBasicInfo = view.findViewById(R.id.edit_basic_info);
        email = view.findViewById(R.id.email);
        email.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
        phone = view.findViewById(R.id.phone);
        phone.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
        dob = view.findViewById(R.id.dob);
        dob.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
        location = view.findViewById(R.id.location);
        location.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
        editInterests = view.findViewById(R.id.edit_interests);
        interestsGridView = view.findViewById(R.id.interestsGridView);
        portofolioText = view.findViewById(R.id.portofolioText);
        portofolioGridView = view.findViewById(R.id.portofolioGridView);
        editPortofolio = view.findViewById(R.id.edit_portofolio);
        videosText = view.findViewById(R.id.videosText);
        videosLayout = view.findViewById(R.id.videosLayout);
        editVideos = view.findViewById(R.id.editVideos);
        skillsText = view.findViewById(R.id.skillsText);
        editSkills = view.findViewById(R.id.editSkill);
        skillsLayout = view.findViewById(R.id.skillsLayout);

        profilePresenter = new ProfilePresenterImpl(this, getContext());

        user = (User) getActivity().getIntent().getSerializableExtra("user");

        //Log.i("type", ""+user.getUserType());
        userName.setText(user.getFirstName()+" " + user.getLastName());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        dob.setText(user.getUserDob());
        location.setText(user.getCity());

        interestsAdapter = new SignUpInterestsAdapter(getActivity(), (List<Categories>) user.getCategoryCollection());
        interestsGridView.setAdapter(interestsAdapter);

        setProfileType();

        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_REQUEST);
            }
        });


        editBasicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
                phone.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
                dob.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
                location.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);

                email.setEnabled(true);
                phone.setEnabled(true);
                dob.setEnabled(true);
                location.setEnabled(true);

                editBasicInfo.setVisibility(View.GONE);
                updateUser.setVisibility(View.VISIBLE);
            }
        });

        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setCity(location.getText().toString());
                user.setUserDob(dob.getText().toString());
                user.setPhone(phone.getText().toString());

                profilePresenter.updateUser(user);

                email.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
                phone.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
                dob.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
                location.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);

                email.setEnabled(false);
                phone.setEnabled(false);
                dob.setEnabled(false);
                location.setEnabled(false);

                editBasicInfo.setVisibility(View.VISIBLE);
                updateUser.setVisibility(View.GONE);
            }
        });


        return view;
    }


    public void setProfileType(){

        Log.i("type", ""+user.getUserType());
        if(user.getUserType() == 0){
            portofolioText.setVisibility(View.GONE);
            portofolioGridView.setVisibility(View.GONE);
            editPortofolio.setVisibility(View.GONE);

            videosText.setVisibility(View.GONE);
            videosLayout.setVisibility(View.GONE);
            editVideos.setVisibility(View.GONE);

            skillsText.setVisibility(View.GONE);
            skillsLayout.setVisibility(View.GONE);
            editSkills.setVisibility(View.GONE);

        }else{

            portofolioAdapter = new PortofolioAdapter(getActivity(), user.getInstructor().getInstructorImagesCollection());
            portofolioGridView.setAdapter(portofolioAdapter);

            Collection<InstructorVideos> videosUrls = user.getInstructor().getInstructorUrlsCollection();
            while (videosUrls.iterator().hasNext()){
                final TextView urlText = new TextView(getContext());
                urlText.setText(videosUrls.iterator().next().getUrlValue());

                urlText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), urlText.getText().toString(), Toast.LENGTH_LONG).show();
                        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + urlText.getText()));
                        startActivity(appIntent);
                    }
                });
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                profileImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//        initView(view);
//        return view;
//    }
//    void initView(View v){
//        ((HomeActivity)getActivity()).fab.setVisibility(View.GONE);
//    }

    /******************************  *************************/



}
