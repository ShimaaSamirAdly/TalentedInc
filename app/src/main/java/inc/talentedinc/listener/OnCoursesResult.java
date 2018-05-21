package inc.talentedinc.listener;

import java.util.ArrayList;

import inc.talentedinc.model.Result;

/**
 * Created by asmaa on 05/21/2018.
 */

public interface OnCoursesResult {
    void onSuccess(ArrayList<Result> listData);
    void onFailure();
}
