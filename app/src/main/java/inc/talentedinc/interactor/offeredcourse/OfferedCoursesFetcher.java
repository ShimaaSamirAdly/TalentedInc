package inc.talentedinc.interactor.offeredcourse;

import java.util.ArrayList;

import inc.talentedinc.API.GetOfferedCourses;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCoursesResponse;
import inc.talentedinc.presenter.OfferedCoursesPresenterInt;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferedCoursesFetcher {

    private OfferedCoursesPresenterInt offeredCoursesPresenterInt;

    public OfferedCoursesFetcher(OfferedCoursesPresenterInt offeredCoursesPresenterInt) {
        this.offeredCoursesPresenterInt = offeredCoursesPresenterInt;
    }

    public void fetchCourses() {

        AppRetrofit.getInstance().getRetrofitInstance().create(GetOfferedCourses.class).getOfferedCourses(0).enqueue(new Callback<OfferedCoursesResponse>() {
            @Override
            public void onResponse(Call<OfferedCoursesResponse> call, Response<OfferedCoursesResponse> response) {
                OfferedCoursesResponse offeredCoursesResponse = response.body();
                offeredCoursesPresenterInt.notifyFragmentWithOfferedCourses(offeredCoursesResponse.getContent());
            }

            @Override
            public void onFailure(Call<OfferedCoursesResponse> call, Throwable t) {
                offeredCoursesPresenterInt.notifyFragmentWithError();
            }
        });
    }
}
