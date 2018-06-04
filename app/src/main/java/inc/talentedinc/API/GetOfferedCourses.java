package inc.talentedinc.API;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.User;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCoursesResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetOfferedCourses {

    @GET("/offeredcourse")
        //@FormUrlEncoded
    Call<OfferedCoursesResponse> getOfferedCourses(@Query("page") int page);

    @GET("/{id}/profile")
        //@FormUrlEncoded
    Call<User> getUser(@Path("id")int id);
}
