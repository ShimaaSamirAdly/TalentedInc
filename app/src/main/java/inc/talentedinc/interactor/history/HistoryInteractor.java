package inc.talentedinc.interactor.history;

import inc.talentedinc.listener.OnCoursesResult;

/**
 * Created by asmaa on 05/21/2018.
 */

public interface HistoryInteractor {
    void getHistory(int page, OnCoursesResult onCoursesResult);

}