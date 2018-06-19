package inc.talentedinc.interactor.history;

import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.ApiHomeEndpoint;
import inc.talentedinc.listener.OnCoursesResult;
import inc.talentedinc.model.CourseComment;
import inc.talentedinc.model.HostingWorkSpaceId;
import inc.talentedinc.model.InstructorId;
import inc.talentedinc.model.Result;
import inc.talentedinc.model.response.CoursesResponse;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by asmaa on 05/21/2018.
 */

public class NetworkHistoryInteractor implements HistoryInteractor {

    private ApiHomeEndpoint mApi = AppRetrofit.getInstance().getHomeApi();
    private ArrayList<Result> data;

    public  static int totalPage =1;

    @Override
    public void getHistory(int userId ,int page, final OnCoursesResult onCoursesResult) {

        Call<CoursesResponse> call;
        call = mApi.getHistory(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),userId,APIUrls.finishedCourses,page);
        call.clone().enqueue(new Callback<CoursesResponse>() {
            @Override
            public void onResponse(Call<CoursesResponse> call, Response<CoursesResponse> response) {
                data = new ArrayList<>();
                if (response.code()== APIUrls.SUCCESS) {
                    totalPage = response.body().getTotalNumberOfPages();
                    Log.i("ttttttt", response.body().getTotalNumberOfUpComingCourses() + "");
                    if (response.body().getResult().size() > 0) {
                        for (int i = 0; i < response.body().getResult().size(); i++) {
                            data.add(response.body().getResult().get(i));
                        }
                        onCoursesResult.onSuccess(data);
                    }
                }
            }
            @Override
            public void onFailure(Call<CoursesResponse> call, Throwable t) {
                Log.e("error: ",t.getMessage());
                onCoursesResult.onFailure();
            }
        });
    }

    @Override
    public void getRegister(int userId, int page, final OnCoursesResult onCoursesResult) {
        Call<CoursesResponse> call;
        call = mApi.getHistory(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),userId,APIUrls.finishedCourses,page);
        call.clone().enqueue(new Callback<CoursesResponse>() {
            @Override
            public void onResponse(Call<CoursesResponse> call, Response<CoursesResponse> response) {
                data = new ArrayList<>();
                if (response.code()== APIUrls.SUCCESS) {
                    totalPage = response.body().getTotalNumberOfPages();
                    Log.i("ttttttt", response.body().getTotalNumberOfUpComingCourses() + "");
                    if (response.body().getResult().size() > 0) {
                        for (int i = 0; i < response.body().getResult().size(); i++) {
                            if (response.body().getResult().get(i).getCourseStatus()==0)
                            data.add(response.body().getResult().get(i));
                        }
                        onCoursesResult.onSuccess(data);
                    }
                }
            }
            @Override
            public void onFailure(Call<CoursesResponse> call, Throwable t) {
                Log.e("error: ",t.getMessage());
                onCoursesResult.onFailure();
            }
        });

    }
}
