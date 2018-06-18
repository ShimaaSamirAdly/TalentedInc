package inc.talentedinc.API;

import inc.talentedinc.model.Course;
import inc.talentedinc.model.response.CreateCourseResponse;
import inc.talentedinc.model.response.MainResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Alaa on 6/1/2018.
 */

public interface ApiCreateCourse {


    @POST (APIUrls.CREATE_COURSE)
    Call<CreateCourseResponse> createCourse(@Body Course course);
}
