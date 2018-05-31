package inc.talentedinc.view.callbackinterfaces;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;

public interface EndlessScrollHandler {

    void showData(ArrayList<MinaCourse> courses);
    void hideProgressBar();
    void showProgressBar();
    void makeErrorToast();

}
