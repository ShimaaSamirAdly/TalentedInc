package inc.talentedinc.presenter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

import inc.talentedinc.adapter.MultiViewAdapter;
import inc.talentedinc.interactor.instructor.InstructorInteractor;
import inc.talentedinc.interactor.instructor.InstructorInteractorImpl;
import inc.talentedinc.interactor.uploadimage.UploadImageInteractor;
import inc.talentedinc.interactor.uploadimage.UploadImageInteractorImpl;
import inc.talentedinc.listener.InstructorListener;
import inc.talentedinc.listener.UploadImageListener;
import inc.talentedinc.model.Instructor;
import inc.talentedinc.model.InstructorImages;
import inc.talentedinc.model.InstructorSkills;
import inc.talentedinc.model.InstructorVideos;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.activities.BecomeInstructorActivity;

/**
 * Created by MMM on 6/8/2018.
 */

public class BecomeInstructorPresenterImpl implements BecomeInstructorPresenter, UploadImageListener, InstructorListener {

    MultiViewAdapter adapter;
    BecomeInstructorActivity view;
    Context context;
    UploadImageInteractor uploadImageInteractor;
    Instructor instructor;
    Collection<InstructorImages> instructorImages;
    ArrayList<Uri> imgsUri;
    InstructorInteractor instructorInteractor;

    public BecomeInstructorPresenterImpl(Context context, BecomeInstructorActivity view){

        this.view = view;
        this.context = context;
        this.uploadImageInteractor = new UploadImageInteractorImpl();
        this.instructorImages = new ArrayList<>();
        imgsUri = new ArrayList<>();
        instructorInteractor = new InstructorInteractorImpl();
    }

    @Override
    public void createInstructor(ArrayList<String> skills, ArrayList<Uri> imgs, String url) {

        boolean flag = true;
        Log.i("skills", ""+skills.size());
        Log.i("skills", ""+url.isEmpty());
        if(skills.size() == 0 || url.isEmpty()){

            flag = false;
        }else{
            for(int i=0; i<imgs.size(); i++){
                if(imgs.get(i) == null){
                    Log.i("skills", ""+i+" "+ null);
                    flag = false;
                    break;
                }
            }
        }

        if(flag){
            instructor = new Instructor();
            instructor.setUserId(SharedPrefrencesSingleton.getSharedPrefUser(context).getUserId());
            Collection<InstructorSkills> instructorSkills = new ArrayList<>();
            for(int i=0; i<skills.size(); i++){
                instructorSkills.add(new InstructorSkills(skills.get(i)));
            }

            imgsUri = imgs;
            for(int i=0; i<imgs.size(); i++){

                uploadImageInteractor.uploadImage(imgs.get(i), this);
            }

            Collection<InstructorVideos> instructorVideos = new ArrayList<>();
            instructorVideos.add(new InstructorVideos(url));

            instructor.setInstructorUrlsCollection(instructorVideos);
            instructor.setSkillsCollection(instructorSkills);

//            becomeInstructor(instructor);

        }else{

            view.emptyFieldsError();
        }
    }

    @Override
    public void becomeInstructor(Instructor instructor) {

        Log.i("skillscount", ""+instructor.getSkillsCollection().size());

        instructorInteractor.becomeInstructor(instructor, this);
    }

    @Override
    public void setFaceBookData(String fbId, String token) {

    }

    @Override
    public void onSuccessUploadImage(String imageUrl) {

        instructorImages.add(new InstructorImages(imageUrl));

        if(imgsUri.size() == instructorImages.size()){
            instructor.setInstructorImagesCollection(instructorImages);
            view.setInstructor(instructor);
        }
    }

    @Override
    public void onSuccessPending(Instructor instructor) {
        Log.i("becomeInst", "success");
        User user = SharedPrefrencesSingleton.getSharedPrefUser(context);
//        user.setUserType(1);
        user.setInstructor(instructor);
        view.switchToHome();
    }

    @Override
    public void onFailedConnection() {
        Toast.makeText(context, "No Internet Connection...", Toast.LENGTH_SHORT).show();
    }
}
