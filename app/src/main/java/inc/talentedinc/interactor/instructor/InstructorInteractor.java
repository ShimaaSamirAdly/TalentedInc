package inc.talentedinc.interactor.instructor;

import inc.talentedinc.listener.InstructorListener;
import inc.talentedinc.model.Instructor;

/**
 * Created by MMM on 6/8/2018.
 */

public interface InstructorInteractor {

    public void becomeInstructor(Instructor instructor, InstructorListener listener);
}
