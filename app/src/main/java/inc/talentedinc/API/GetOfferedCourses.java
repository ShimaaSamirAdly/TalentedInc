package inc.talentedinc.API;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCoursesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetOfferedCourses {

    @GET("/offeredcourse/nodetails")
        //@FormUrlEncoded
    Call<OfferedCoursesResponse> getOfferedCourses();
}
