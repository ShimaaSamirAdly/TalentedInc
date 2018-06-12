package inc.talentedinc.view.fragmnts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.adapter.PortofolioAdapter;
import inc.talentedinc.adapter.SignUpInterestsAdapter;
import inc.talentedinc.adapter.SkillsGridAdapter;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.Instructor;
import inc.talentedinc.model.InstructorImages;
import inc.talentedinc.model.InstructorSkills;
import inc.talentedinc.model.InstructorVideos;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.profile.ProfilePresenter;
import inc.talentedinc.presenter.profile.ProfilePresenterImpl;

import static android.app.Activity.RESULT_OK;

import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.utilitis.SignupValidator;
import inc.talentedinc.view.activities.FollowersActivity;
import inc.talentedinc.view.activities.HomeActivity;
import inc.talentedinc.view.activities.OthersProfileActivity;
import inc.talentedinc.view.activities.UpdateInterestsActivity;
import inc.talentedinc.view.callbackinterfaces.SetDateTextView;
import inc.talentedinc.view.customviews.ExpandableHeightGridView;


public class ProfileFragment extends Fragment implements AdapterView.OnItemSelectedListener, SetDateTextView {

    /****************************** asmaa *************************/

    /******************************Shimaa**************************/

    private ImageView profileImage;
    private ImageView pickImage;
    private TextView userName;
    private ImageView editBasicInfo;
    private ImageView updateUser;
    private ImageView cancel;
    private TextView email;
    private EditText phone;
    private TextView dob;
    private Spinner location;
    private ImageView editInterests;
    private ExpandableHeightGridView interestsGridView;
    private TextView portofolioText;
    private ImageView editPortofolio;
    private ExpandableHeightGridView portofolioGridView;
    private TextView videosText;
    private ImageView editVideos;
    private LinearLayout videosLayout;
    private TextView skillsText;
    private ImageView editSkills;
    private ExpandableHeightGridView skillsGridView;
    private TextView following;
    private TextView followers;
    private EditText addSkill;
    private User user;
    private SignUpInterestsAdapter interestsAdapter;
    private PortofolioAdapter portofolioAdapter;
    private Uri filePath;
    private final int PICK_REQUEST = 77;
    private Bitmap theBitmap;
    private Uri imgUrl;
    private ProfilePresenter profilePresenter;
    private boolean isEditableVideo;
    private Collection<InstructorVideos> videosUrls;
    private SignupValidator validator;
    private SkillsGridAdapter skillsGridAdapter;
    private ArrayList<String> instructorSkills;


    /*****************************************************************/

    public ProfileFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        ***************************** Asmaa ***************************************
//        ((HomeActivity)getActivity()).whichFragment(HomeActivity.PROGILE);

//        ********************************************************************

        /******************************Shimaa*******************************************/

        validator = new SignupValidator();
        user = new User();
        profileImage = view.findViewById(R.id.profileImage);
        pickImage = view.findViewById(R.id.pickImage);
        userName = view.findViewById(R.id.userName);
        followers = view.findViewById(R.id.followers);
        following = view.findViewById(R.id.following);
        updateUser = view.findViewById(R.id.updateUser);
        cancel = view.findViewById(R.id.cancel);
        editBasicInfo = view.findViewById(R.id.edit_basic_info);
        email = view.findViewById(R.id.email);
//        email.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
        phone = view.findViewById(R.id.phone);
        phone.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
        phone.setEnabled(false);
        dob = view.findViewById(R.id.dob);
        dob.setEnabled(false);
//        dob.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
        final DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setDateSetter(this);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });
        location = view.findViewById(R.id.location);
        location.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.cities_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        location.setAdapter(adapter);
        location.setEnabled(false);
        location.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
        editInterests = view.findViewById(R.id.edit_interests);
        interestsGridView = view.findViewById(R.id.interestsGridView);
        interestsGridView.setExpanded(true);
        portofolioText = view.findViewById(R.id.portofolioText);
        portofolioGridView = view.findViewById(R.id.portofolioGridView);
        portofolioGridView.setExpanded(true);
//        editPortofolio = view.findViewById(R.id.edit_portofolio);
        videosText = view.findViewById(R.id.videosText);
        videosLayout = view.findViewById(R.id.videosLayout);
//        editVideos = view.findViewById(R.id.editVideos);
        skillsText = view.findViewById(R.id.skillsText);
//        editSkills = view.findViewById(R.id.editSkill);
        skillsGridView = view.findViewById(R.id.skillsLayout);
        followers = view.findViewById(R.id.followers);
        following = view.findViewById(R.id.following);
//        addSkill = view.findViewById(R.id.addSkill);
//        addVideo.setVisibility(View.GONE);
        isEditableVideo = false;


        profilePresenter = new ProfilePresenterImpl(this, getContext());


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

//                email.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
                phone.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
//                dob.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
                location.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);

                phone.setEnabled(true);
                dob.setEnabled(true);
                location.setEnabled(true);

                editBasicInfo.setVisibility(View.GONE);
                updateUser.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
            }
        });

        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                user.setCity(location.getText().toString());
                if(validator.validatePhone(phone.getText().toString())) {
                    user = SharedPrefrencesSingleton.getSharedPrefUser(getContext());
                    user.setUserDob(dob.getText().toString());
                    user.setPhone(phone.getText().toString());
                    if(location.getSelectedItemPosition() == 0) {
                        user.getCity().equals("Cairo");
                    }else{
                        user.getCity().equals("Alexandria");
                    }


                    profilePresenter.updateUser(user);

//                    email.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
                    phone.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
//                    dob.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
                    location.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);

//                    email.setEnabled(false);
                    phone.setEnabled(false);
                    dob.setEnabled(false);
                    location.setEnabled(false);

                    editBasicInfo.setVisibility(View.VISIBLE);
                    updateUser.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                }else{
                    Toast.makeText(getContext(), "Invalid Phone number", Toast.LENGTH_LONG).show();
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = SharedPrefrencesSingleton.getSharedPrefUser(getContext());
                phone.setText(user.getPhone());
                dob.setText(user.getUserDob());
                if(user.getCity().equals("Cairo")) {
                    location.setSelection(0);
                }else{
                    location.setSelection(1);
                }

                editBasicInfo.setVisibility(View.VISIBLE);
                updateUser.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);

                phone.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
                location.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
                dob.setEnabled(false);
                phone.setEnabled(false);
                location.setEnabled(false);
            }
        });



//        editSkills.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                addVideo.setVisibility(View.VISIBLE);
////                isEditableVideo = true;
////
//                if (!addSkill.getText().toString().equals("")) {
//                    InstructorSkills instructorSkills = new InstructorSkills(user.getUserId(), addSkill.getText().toString());
////                    skills.add(instructorSkills);
////                    addVideoTextView(instructorVideos);
//                    addSkill.setText("");
//                    Log.i("video", "" + videosUrls.size());
//                }else{
//                    Toast.makeText(getContext(), "Empty Field", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        return view;
    }


    public void setProfileType(User user){

//        Log.i("type", ""+user.getUserType());
        if(user.getInstructor() == null){
            portofolioText.setVisibility(View.GONE);
            portofolioGridView.setVisibility(View.GONE);

            videosText.setVisibility(View.GONE);
            videosLayout.setVisibility(View.GONE);

            skillsText.setVisibility(View.GONE);
            skillsGridView.setVisibility(View.GONE);
//
        }else if (user.getUserType() == 1){

//            user.setInstructor(new Instructor());
//            Collection<InstructorImages> im = new ArrayList<>();
//            im.add(new InstructorImages(1, "https://firebasestorage.googleapis.com/v0/b/talentedinc-bba9a.appspot.com/o/image%2F1396998a-cd40-44ba-bb2b-ab0eb7f12531?alt=media&token=1bdffac7-41ce-4765-9b99-3ddf4028c593"));
//            user.getInstructor().setInstructorImagesCollection(im);
            portofolioAdapter = new PortofolioAdapter(getActivity(), (List<InstructorImages>)user.getInstructor().getInstructorImagesCollection());
            portofolioGridView.setAdapter(portofolioAdapter);
//
//            InstructorVideos v = new InstructorVideos(1, "urlVideo");
//            Collection<InstructorVideos> nb = new ArrayList<>();
//            nb.add(new InstructorVideos("hhhhhhh"));
//            nb.add(new InstructorVideos("iiiiiiii"));
//            nb.add(new InstructorVideos("ppppppp"));
//            nb.add(new InstructorVideos("oooooooooo"));
//            nb.add(new InstructorVideos("oooooooooooooooo"));
//            nb.add(new InstructorVideos("eeeeeeeeeeeeeee"));
//            nb.add(new InstructorVideos("rrrrrrrrrrrrrrrr"));
//            user.setInstructor(new Instructor());
//            user.getInstructor().setInstructorUrlsCollection(nb);
//            videosLayout.removeAllViews();

           videosUrls = user.getInstructor().getInstructorUrlsCollection();
            Iterator iter = videosUrls.iterator();
            while (iter.hasNext()){
                addVideoTextView((InstructorVideos) iter.next());
            }

            instructorSkills = new ArrayList<>();
            Iterator skillsIter = user.getInstructor().getSkillsCollection().iterator();

            while(skillsIter.hasNext()) {
                Log.i("skills", ""+user.getInstructor().getSkillsCollection().size());
                InstructorSkills skill = (InstructorSkills) skillsIter.next();
                instructorSkills.add(skill.getSkillValue());
            }
            Log.i("skillss", ""+instructorSkills.size());
            skillsGridAdapter = new SkillsGridAdapter(getContext(), instructorSkills);
            skillsGridView.setAdapter(skillsGridAdapter);

//            editSkills.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    addSkill.setVisibility(View.VISIBLE);
//
//                }
//            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("res", "onResult");

        if(requestCode == PICK_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                profileImage.setImageBitmap(bitmap);
                profilePresenter.uploadImage(filePath, user);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setProfileImage(String imageUrl){

        Glide.with(getActivity())
                .load("" + imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(profileImage);
    }


    @Override
    public void onResume() {
        super.onResume();


        Bundle bundle = getArguments();

        if(bundle != null) {
            user = (User) bundle.getSerializable("user");

            editInterests.setVisibility(View.GONE);
            editBasicInfo.setVisibility(View.GONE);
            pickImage.setVisibility(View.GONE);

            setUserData(user);


        }else {

            profilePresenter.getCurrentUser();
            ((HomeActivity)getActivity()).whichFragment(HomeActivity.PROGILE);
            initView();

        }

    }

    public void setUserData(final User user){

        userName.setText(user.getFirstName()+" " + user.getLastName());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        dob.setText(user.getUserDob());
        if(user.getCity().equals("Cairo")) {
            location.setSelection(0);
        }else{
            location.setSelection(1);
        }
        following.setText(user.getFollowingNumber()+"\nFollowing");
        followers.setText(user.getFollowersNumber()+"\nFollowers");

        final Intent intent = new Intent(getContext(), FollowersActivity.class);
        intent.putExtra("userId", user.getUserId());
        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type", 0);
                startActivity(intent);
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });

        if(user.getImgUrl() != null) {
            setProfileImage(user.getImgUrl());
        }

        Log.i("interests", ""+user.getCategoryCollection().size());
        interestsAdapter = new SignUpInterestsAdapter(getActivity(), (List<Categories>) user.getCategoryCollection());
        interestsGridView.setAdapter(interestsAdapter);

        editInterests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("inters", "onclick");
                Intent intent = new Intent(getActivity(), UpdateInterestsActivity.class);
//                intent.putExtra("user", user);
                startActivity(intent);
//                interestsAdapter = new SignUpInterestsAdapter(getActivity(), (List<Categories>) user.getCategoryCollection());
//                interestsGridView.setAdapter(interestsAdapter);
            }
        });

        setProfileType(user);
    }


    public void addVideoTextView(InstructorVideos instructorVideos){

        final EditText urlText = new EditText(getContext());
        final InstructorVideos video = (InstructorVideos) instructorVideos;
        urlText.setTextSize(18);
        urlText.setTextColor(Color.BLUE);
        if(video.getUrlValue().toString().length() > 30) {
            urlText.setText(video.getUrlValue().substring(0, 30) + "\n" + video.getUrlValue().substring(30));
        }else{
            urlText.setText(video.getUrlValue());
        }
        urlText.setPadding(50,0,0,0);
        videosLayout.addView(urlText);

        urlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEditableVideo) {
                    Toast.makeText(getContext(), urlText.getText().toString(), Toast.LENGTH_LONG).show();
                    String url = urlText.getText().toString();
                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + url.substring(url.lastIndexOf("=")+1)));
                    startActivity(appIntent);
                }
//                else
//
//                    videosLayout.removeView(urlText);
//                    videosUrls.remove(video);
//                    Log.i("video", ""+videosUrls.size());
//                }
            }
        });

    }


//    public void addSkillTextView(InstructorSkills instructorSkills){
//
//        final TextView skillValue = new TextView(getContext());
//        final InstructorSkills skill = (InstructorSkills) instructorSkills;
//        skillValue.setTextSize(16);
//        skillValue.setTextColor(Color.BLUE);
//        urlText.setText(video.getUrlValue());
//        urlText.setPadding(50,0,0,0);
//        videosLayout.addView(urlText);
//
//        urlText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!isEditableVideo) {
//                    Toast.makeText(getContext(), urlText.getText().toString(), Toast.LENGTH_LONG).show();
//                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + urlText.getText()));
//                    startActivity(appIntent);
//                }else{
//
//                    videosLayout.removeView(urlText);
//                    videosUrls.remove(video);
//                    Log.i("video", ""+videosUrls.size());
//                }
//            }
//        });
//
//    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                user.setCity("Cairo");
                break;
            case 1:
                user.setCity("Alexandria");
                break;
            default:
                user.setCity("Alexandria");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    void initView(){

        ((HomeActivity)getActivity()).fab.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).becomeInstructor.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDateTextView(String date, int year) {
        if(validator.validateDob(year)) {
            dob.setText(date);
        }else{
            Toast.makeText(getContext(), "Invalid Date", Toast.LENGTH_LONG).show();
        }

//        ((HomeActivity)getActivity()).whichFragment(HomeActivity.PROGILE);
    }

    /******************************  *************************/

}
