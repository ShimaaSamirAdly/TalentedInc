package inc.talentedinc.interactor.offeredcourse;

import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.API.GetOfferedCourses;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.User;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.model.offeredcourse.OfferedCoursesResponse;
import inc.talentedinc.presenter.OfferedCoursesPresenterInt;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferedCoursesFetcher {

    private OfferedCoursesPresenterInt offeredCoursesPresenterInt;
    private int totalPagesNumber;
    private int currentPageNumber;

    public OfferedCoursesFetcher(OfferedCoursesPresenterInt offeredCoursesPresenterInt) {
        this.offeredCoursesPresenterInt = offeredCoursesPresenterInt;
        currentPageNumber = 0;
    }

    public void fetchCourses(int page) {

        AppRetrofit.getInstance().getRetrofitInstance().create(GetOfferedCourses.class)
                .getOfferedCourses(page)
                .enqueue(new Callback<OfferedCoursesResponse>() {
                    @Override
                    public void onResponse(Call<OfferedCoursesResponse> call, Response<OfferedCoursesResponse> response) {
                        Log.i("RETROFIT", "" + response.code());
                        OfferedCoursesResponse offeredCoursesResponse = response.body();
                        totalPagesNumber = offeredCoursesResponse.getTotalPages();
                        offeredCoursesPresenterInt.notifyFragmentWithOfferedCourses(offeredCoursesResponse.getContent());
                    }

                    @Override
                    public void onFailure(Call<OfferedCoursesResponse> call, Throwable t) {
                        Log.i("RETROFIT", t.getMessage());
                        offeredCoursesPresenterInt.notifyFragmentWithError();
                    }
                });
    }

    public void requestCourse(Integer offeredCourseId, Integer instructorId) {
        AppRetrofit.getInstance().getRetrofitInstance().create(GetOfferedCourses.class)
                .instructorRequestOfferedCourse(instructorId, offeredCourseId)
                .enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        offeredCoursesPresenterInt.makeToastRequestResult(1);
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        offeredCoursesPresenterInt.makeToastRequestResult(0);
                    }
                });
    }

    public void fetchMoreCourses() {
        if (currentPageNumber < totalPagesNumber - 1) {
            fetchCourses(currentPageNumber++);
        }
    }
}
