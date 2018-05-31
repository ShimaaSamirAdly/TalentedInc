package inc.talentedinc.interactor.offeredcourse;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import inc.talentedinc.model.Course;
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
        RetrofitHandler.getOfferedCoursesService(baseUrl).getOfferedCourses().enqueue(new Callback<ArrayList<Course>>() {
            @Override
            public void onResponse(Call<ArrayList<Course>> call, Response<ArrayList<Course>> response) {
                ArrayList<Course> fetshedCourses = response.body();
                offeredCoursesPresenterInt.notifyFragmentWithOfferedCourses(fetshedCourses);
            }

            @Override
            public void onFailure(Call<ArrayList<Course>> call, Throwable t) {
                offeredCoursesPresenterInt.notifyFragmentWithError();
            }
        });
    }
}
