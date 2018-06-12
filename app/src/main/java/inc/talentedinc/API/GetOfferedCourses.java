package inc.talentedinc.API;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.User;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.model.offeredcourse.OfferedCourseWorkspace;
import inc.talentedinc.model.offeredcourse.OfferedCoursesResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetOfferedCourses {

    @GET("/offeredcourse")
        //@FormUrlEncoded
    Call<OfferedCoursesResponse> getOfferedCourses(@Query("page") int page);

    @POST("/InstructorReqOfferedCourse/requestcourse")
    Call<Object> instructorRequestOfferedCourse(@Query("instructorId")Integer instrctorId,@Query("courseId")Integer courseId);

    @Headers("Cache-Control: no-cache")
    @GET("/offeredcourse/offeredcoursebyinstuctor")
    Call<ArrayList<OfferedCourseDetailed>> getMyOfferedCourse(@Query("insructorId")Integer instrctorId);

    @GET("/offeredcourse/requestedWorkSpaces")
    Call<ArrayList<OfferedCourseWorkspace>> getCourseRequests(@Query("courseId")Integer offeredCourseId);

    @POST("/offeredcourse/acceptWorkSpaceRequest")
    Call<Void> acceptCourse(@Query("courseId") int courseId, @Query("workSpaceId") Integer workSpaceId);
}
