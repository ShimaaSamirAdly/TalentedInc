package inc.talentedinc.API;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetOfferedCourses {

    @GET("/getofferedcourse")
        //@FormUrlEncoded
    Call<ArrayList<OfferedCourse>> getOfferedCourses();
}
