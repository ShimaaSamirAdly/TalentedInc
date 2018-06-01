package inc.talentedinc.interactor.offeredcourse;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.presenter.OfferedCoursesPresenterInt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferedCoursesFetcher {

    private OfferedCoursesPresenterInt offeredCoursesPresenterInt;

    public OfferedCoursesFetcher(OfferedCoursesPresenterInt offeredCoursesPresenterInt) {
        this.offeredCoursesPresenterInt = offeredCoursesPresenterInt;
    }

    public void fetchCourses(String baseUrl){
        RetrofitHandler.getOfferedCoursesService(baseUrl).getOfferedCourses().enqueue(new Callback<ArrayList<MinaCourse>>() {
            @Override
            public void onResponse(Call<ArrayList<MinaCourse>> call, Response<ArrayList<MinaCourse>> response) {
                ArrayList<MinaCourse> fetshedCourses = response.body();
                offeredCoursesPresenterInt.notifyFragmentWithOfferedCourses(fetshedCourses);
            }

            @Override
            public void onFailure(Call<ArrayList<MinaCourse>> call, Throwable t) {
                offeredCoursesPresenterInt.notifyFragmentWithError();
            }
        });
    }
}
