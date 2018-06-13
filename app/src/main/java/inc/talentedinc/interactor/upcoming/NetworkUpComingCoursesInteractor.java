package inc.talentedinc.interactor.upcoming;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asmaa on 05/21/2018.
 */

public class NetworkUpComingCoursesInteractor implements UpComingCoursesInteractor {

    private ApiHomeEndpoint mApi = AppRetrofit.getInstance().getHomeApi();
    private ArrayList<Result> data;
    public static int totalPage ;

    @Override
    public void getUpComingCourses(int userId,int page, final OnCoursesResult onCoursesResult) {
//       dummyData();
//        onCoursesResult.onSuccess(data);

        Call<CoursesResponse> call;
        call = mApi.getUpComing(userId,page);
        call.clone().enqueue(new Callback<CoursesResponse>() {
            @Override
            public void onResponse(Call<CoursesResponse> call, Response<CoursesResponse> response) {
                data = new ArrayList<>();
                if (response.code()==APIUrls.SUCCESS) {
                    totalPage = response.body().getTotalNumberOfPages();
                    if (response.body().getResult().size() != 0) {
                        Log.i("totalItem", response.body().getTotalNumberOfUpComingCourses() + "");
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
    public void getSearchByName(int userId, String keyword, int page, final OnCoursesResult onCoursesResult) {
        Call<CoursesResponse> call;
        call = mApi.getSearchByName(userId,keyword,page);
        call.clone().enqueue(new Callback<CoursesResponse>() {
            @Override
            public void onResponse(Call<CoursesResponse> call, Response<CoursesResponse> response) {
                data = new ArrayList<>();
                if (response.body().getResult()!=null){
                    totalPage = response.body().getTotalNumberOfPages();
                    Log.i("totalItem",response.body().getTotalNumberOfUpComingCourses()+"" );
                    for (int i = 0; i < response.body().getResult().size(); i++) {
                        data.add(response.body().getResult().get(i));
                    }
                    onCoursesResult.onSuccess(data);
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
    public void getSearchByFilter(int userId, String category, String city, int page, final OnCoursesResult onCoursesResult) {

        Call<CoursesResponse> call;
        call = mApi.getSearchByFilter(userId,category,city,page);
        call.clone().enqueue(new Callback<CoursesResponse>() {
            @Override
            public void onResponse(Call<CoursesResponse> call, Response<CoursesResponse> response) {
                data = new ArrayList<>();
                if (response.body().getResult()!=null){
                    totalPage = response.body().getTotalNumberOfPages();
                    Log.i("totalItem",response.body().getTotalNumberOfUpComingCourses()+"" );
                    for (int i = 0; i < response.body().getResult().size(); i++) {
                        data.add(response.body().getResult().get(i));
                    }
                    onCoursesResult.onSuccess(data);
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
