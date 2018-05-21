package inc.talentedinc.interactor.upcoming;

import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.ApiUpComingEndpoint;
import inc.talentedinc.listener.OnCoursesResult;
import inc.talentedinc.model.MoviesData;
import inc.talentedinc.model.Result;
import inc.talentedinc.singleton.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asmaa on 05/21/2018.
 */

public class NetworkUpComingCoursesInteractor implements UpComingCoursesInteractor {

    private ApiUpComingEndpoint mApi = App.getInstance().getUpComingApi();
    private ArrayList<Result> data;

    public  static int totalPage = 1000;
    @Override
    public void getUpComingCourses(int page, final OnCoursesResult onCoursesResult) {
        Call<MoviesData> call;
        call = mApi.getMovies(APIUrls.API_KEY,page);
        call.clone().enqueue(new Callback<MoviesData>() {
            @Override
            public void onResponse(Call<MoviesData> call, Response<MoviesData> response) {

                data = new ArrayList<>();
                if (response.body()!=null){
                    totalPage = response.body().getTotalPages();
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        data.add(response.body().getResults().get(i));
                    }
                    onCoursesResult.onSuccess(data);
                }
            }

            @Override
            public void onFailure(Call<MoviesData> call, Throwable t) {
                Log.e("error: ",t.getMessage());
                onCoursesResult.onFailure();
            }
        });

    }
}
