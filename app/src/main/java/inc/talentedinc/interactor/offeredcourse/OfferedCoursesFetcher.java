package inc.talentedinc.interactor.offeredcourse;

import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.API.GetOfferedCourses;
import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.User;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.model.offeredcourse.OfferedCourseWorkspace;
import inc.talentedinc.model.offeredcourse.OfferedCoursesResponse;
import inc.talentedinc.presenter.MyOfferedCoursePresenter;
import inc.talentedinc.presenter.OfferedCoursesPresenterInt;
import inc.talentedinc.presenter.RequestsPresenter;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferedCoursesFetcher {

    private OfferedCoursesPresenterInt offeredCoursesPresenterInt;
    private int totalPagesNumber;
    private int currentPageNumber;
    private MyOfferedCoursePresenter myOfferedCoursePresenter;
    private RequestsPresenter requestsPresenter;
    public static volatile OfferedCoursesFetcher offeredCoursesFetcher;

    private OfferedCoursesFetcher() {
        currentPageNumber = 0;
    }

    public static OfferedCoursesFetcher sharedInstance(){
        if(offeredCoursesFetcher == null){
            synchronized (OfferedCoursesFetcher.class){
                if (offeredCoursesFetcher == null){
                    offeredCoursesFetcher = new OfferedCoursesFetcher();
                }
            }
        }
        return offeredCoursesFetcher;
    }

    public void fetchCourses(int page) {

        AppRetrofit.getInstance().getRetrofitInstance().create(GetOfferedCourses.class)
                .getOfferedCourses(page)
                .enqueue(new Callback<OfferedCoursesResponse>() {
                    @Override
                    public void onResponse(Call<OfferedCoursesResponse> call, Response<OfferedCoursesResponse> response) {
                        Log.i("RETROFIT", "" + response.body());
                        OfferedCoursesResponse offeredCoursesResponse = response.body();
//                        totalPagesNumber = offeredCoursesResponse.getTotalPages();
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

    public void setMyOfferedCoursePresenter(MyOfferedCoursePresenter myOfferedCoursePresenter) {
        this.myOfferedCoursePresenter = myOfferedCoursePresenter;
    }

    public void fetchMyOfferedCourses(int instructorId) {
        //fix instructor id
        AppRetrofit.getInstance().getRetrofitInstance().create(GetOfferedCourses.class)
                .getMyOfferedCourse(instructorId).enqueue(new Callback<ArrayList<OfferedCourseDetailed>>() {
            @Override
            public void onResponse(Call<ArrayList<OfferedCourseDetailed>> call, Response<ArrayList<OfferedCourseDetailed>> response) {
                myOfferedCoursePresenter.notifyMyOfferedCoursesFetched(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<OfferedCourseDetailed>> call, Throwable t) {
                //notify error
            }
        });
    }

    public void getCourseRequests(Integer offeredCourseId) {
        AppRetrofit.getInstance().getRetrofitInstance().create(GetOfferedCourses.class)
                .getCourseRequests(offeredCourseId).enqueue(new Callback<ArrayList<OfferedCourseWorkspace>>() {
            @Override
            public void onResponse(Call<ArrayList<OfferedCourseWorkspace>> call, Response<ArrayList<OfferedCourseWorkspace>> response) {
                requestsPresenter.notifyWithRequestsWorkspaces(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<OfferedCourseWorkspace>> call, Throwable t) {
                //notify with error
            }
        });
    }

    public void acceptCourse(int courseId, Integer workSpaceId) {
        AppRetrofit.getInstance().getRetrofitInstance().create(GetOfferedCourses.class)
                .acceptCourse(courseId,workSpaceId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    requestsPresenter.worSpaceAccepted();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void setOfferedCoursesPresenterInt(OfferedCoursesPresenterInt offeredCoursesPresenterInt) {
        this.offeredCoursesPresenterInt = offeredCoursesPresenterInt;
    }

    public void setRequestsPresenter(RequestsPresenter requestsPresenter) {
        this.requestsPresenter = requestsPresenter;
    }
}
