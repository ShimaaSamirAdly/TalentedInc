package inc.talentedinc.interactor.createcourse;

import android.util.Log;

import inc.talentedinc.API.ApiCreateCourse;
import inc.talentedinc.listener.OnCreateCourse;
import inc.talentedinc.model.Course;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alaa on 6/1/2018.
 */

public class CreateCourseImpl implements CreateCourseInter {
    ApiCreateCourse apiCreateCourse = AppRetrofit.getInstance().getApiCreateCourse();


    @Override
    public void createCourse(Course course, final OnCreateCourse onCreateCourse) {
    apiCreateCourse.createCourse(course).enqueue(new Callback<Object>() {
        @Override
        public void onResponse(Call<Object> call, Response<Object> response) {

            //mfrod eni hna hfok el object da w ageb el gwah
            onCreateCourse.onSuccess(response.body());

            Log.i("done",response.body().toString());
        }

        @Override
        public void onFailure(Call<Object> call, Throwable t) {
            onCreateCourse.onFail();
            call.cancel();
            Log.i("fail",call.toString());
        }
    });

    }
}
