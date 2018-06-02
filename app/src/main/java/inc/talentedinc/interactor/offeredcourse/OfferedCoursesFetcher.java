package inc.talentedinc.interactor.offeredcourse;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
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
        RetrofitHandler.getOfferedCoursesService(baseUrl).getOfferedCourses().enqueue(new Callback<ArrayList<OfferedCourse>>() {
            @Override
            public void onResponse(Call<ArrayList<OfferedCourse>> call, Response<ArrayList<OfferedCourse>> response) {
                ArrayList<OfferedCourse> fetshedCourses = response.body();
                offeredCoursesPresenterInt.notifyFragmentWithOfferedCourses(fetshedCourses);
            }

            @Override
            public void onFailure(Call<ArrayList<OfferedCourse>> call, Throwable t) {
                offeredCoursesPresenterInt.notifyFragmentWithError();
            }
        });
    }
}
