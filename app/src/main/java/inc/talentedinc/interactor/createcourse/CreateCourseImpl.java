package inc.talentedinc.interactor.createcourse;

import android.util.Log;

import inc.talentedinc.API.ApiCreateCourse;
import inc.talentedinc.listener.OnCreateCourse;
import inc.talentedinc.model.Course;
import inc.talentedinc.model.response.CreateCourseResponse;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Alaa on 6/1/2018.
 */

public class CreateCourseImpl implements CreateCourseInter {
    ApiCreateCourse apiCreateCourse = AppRetrofit.getInstance().getApiCreateCourse();


    @Override
    public void createCourse(Course course, final OnCreateCourse onCreateCourse) {
    apiCreateCourse.createCourse(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),course).enqueue(new Callback<CreateCourseResponse>() {
        @Override
        public void onResponse(Call<CreateCourseResponse> call, Response<CreateCourseResponse> response) {

            onCreateCourse.onSuccess(response.body());

            Log.i("done",response.body().toString());
        }

        @Override
        public void onFailure(Call<CreateCourseResponse> call, Throwable t) {
            onCreateCourse.onFail();
            call.cancel();
            Log.i("fail",call.toString());
        }
    });

    }
}
