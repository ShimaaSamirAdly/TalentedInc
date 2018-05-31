package inc.talentedinc.interactor.history;

import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.ApiHomeEndpoint;
import inc.talentedinc.listener.OnCoursesResult;
import inc.talentedinc.model.Result;
import inc.talentedinc.model.UpComingData;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asmaa on 05/21/2018.
 */

public class NetworkHistoryInteractor implements HistoryInteractor {

    private ApiHomeEndpoint mApi = AppRetrofit.getInstance().getHomeApi();
    private ArrayList<Result> data;

    public  static int totalPage = 1000;
    @Override
    public void getHistory(int page, final OnCoursesResult onCoursesResult) {
        Call<UpComingData> call;
        call = mApi.getHistory(APIUrls.API_KEY,page);
        call.clone().enqueue(new Callback<UpComingData>() {
            @Override
            public void onResponse(Call<UpComingData> call, Response<UpComingData> response) {
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
            public void onFailure(Call<UpComingData> call, Throwable t) {
                Log.e("error: ",t.getMessage());
                onCoursesResult.onFailure();
            }
        });

    }
}
