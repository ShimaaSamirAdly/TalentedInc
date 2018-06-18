package inc.talentedinc.factory;

import inc.talentedinc.interactor.categories.CategoriesInteractor;
import inc.talentedinc.interactor.categories.CategoriesInteractorImpl;
import inc.talentedinc.interactor.commentLike.CommentLikeInteractor;
import inc.talentedinc.interactor.commentLike.NetworkCommentLikeInteractor;
import inc.talentedinc.interactor.history.HistoryInteractor;
import inc.talentedinc.interactor.history.NetworkHistoryInteractor;
import inc.talentedinc.interactor.rate.NetworkRateInteractor;
import inc.talentedinc.interactor.rate.RateInteractor;
import inc.talentedinc.interactor.register.NetworkRegisterInteractor;
import inc.talentedinc.interactor.register.RegisterInteractor;
import inc.talentedinc.interactor.upcoming.NetworkUpComingCoursesInteractor;
import inc.talentedinc.interactor.upcoming.UpComingCoursesInteractor;

/**
 * Created by asmaa on 05/21/2018.
 */

public class Factory {
    public static UpComingCoursesInteractor provideUpComing(){
        return new NetworkUpComingCoursesInteractor();
    }

    public static HistoryInteractor provideHistory(){
        return new NetworkHistoryInteractor();
    }

    public static CommentLikeInteractor provideCommentLike(){
        return new NetworkCommentLikeInteractor();
    }

    public static RateInteractor provideRate(){
        return new NetworkRateInteractor();
    }

    public static CategoriesInteractor provideCategories(){
        return new CategoriesInteractorImpl();
    }

    public static RegisterInteractor provideRegister(){
        return new NetworkRegisterInteractor();
    }

}
