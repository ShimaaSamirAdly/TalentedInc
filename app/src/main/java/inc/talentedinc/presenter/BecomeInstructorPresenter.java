package inc.talentedinc.presenter;

import android.net.Uri;

import java.util.ArrayList;

import inc.talentedinc.model.Instructor;

/**
 * Created by MMM on 6/8/2018.
 */

public interface BecomeInstructorPresenter {

    public void createInstructor(ArrayList<String> skills, ArrayList<Uri>imgs, String url);

    public void becomeInstructor(Instructor instructor);
}
