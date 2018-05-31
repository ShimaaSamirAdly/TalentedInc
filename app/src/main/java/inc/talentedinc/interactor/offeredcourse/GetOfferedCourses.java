package inc.talentedinc.interactor.offeredcourse;

import java.util.ArrayList;

import inc.talentedinc.model.Course;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetOfferedCourses {

    @GET("/getofferedcourses")
        //@FormUrlEncoded
    Call<ArrayList<Course>> getOfferedCourses();
}
