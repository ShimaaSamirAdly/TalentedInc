package inc.talentedinc.factory;

import inc.talentedinc.interactor.upcoming.NetworkUpComingCoursesInteractor;
import inc.talentedinc.interactor.upcoming.UpComingCoursesInteractor;

/**
 * Created by asmaa on 05/21/2018.
 */

public class Factory {
    public static UpComingCoursesInteractor provideUpComing(){
        return new NetworkUpComingCoursesInteractor();
    }
}