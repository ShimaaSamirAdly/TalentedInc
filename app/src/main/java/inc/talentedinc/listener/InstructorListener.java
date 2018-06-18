package inc.talentedinc.listener;

import android.content.Intent;

import inc.talentedinc.model.Instructor;

/**
 * Created by MMM on 6/8/2018.
 */

public interface InstructorListener {

    public void onSuccessPending(Instructor instructor);

    public void onFailedConnection();
}
