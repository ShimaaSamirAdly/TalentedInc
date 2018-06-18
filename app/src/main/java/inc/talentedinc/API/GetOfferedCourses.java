package inc.talentedinc.API;

import java.util.ArrayList;

import inc.talentedinc.model.MinaCourse;
import inc.talentedinc.model.User;
import inc.talentedinc.model.offeredcourse.OfferedCourse;
import inc.talentedinc.model.offeredcourse.OfferedCourseDetailed;
import inc.talentedinc.model.offeredcourse.OfferedCourseWorkspace;
import inc.talentedinc.model.offeredcourse.OfferedCoursesResponse;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetOfferedCourses {

    @Headers("Cache-Control: no-cache")
    @GET("/offeredcourse/createdByWorkspaces")
    Call<OfferedCoursesResponse> getOfferedCourses(@Header("Authorization") String token, @Query("page") int page, @Query("instructorId") int instructorId);

    @POST("/InstructorReqOfferedCourse/requestcourse")
    Call<Object> instructorRequestOfferedCourse(@Header("Authorization") String token,@Query("instructorId")Integer instrctorId,@Query("courseId")Integer courseId);

    @Headers("Cache-Control: no-cache")
    @GET("/offeredcourse/offeredcoursebyinstuctor")

    Call<ArrayList<OfferedCourseDetailed>> getMyOfferedCourse(@Header("Authorization") String token,@Query("insructorId")Integer instrctorId,@Query("page")int page);

    @GET("/offeredcourse/requestedWorkSpaces")
    Call<ArrayList<OfferedCourseWorkspace>> getCourseRequests(@Header("Authorization") String token,@Query("courseId")Integer offeredCourseId);

    @POST("/offeredcourse/acceptWorkSpaceRequest")
    Call<Void> acceptCourse(@Header("Authorization") String token,@Query("courseId") int courseId, @Query("workSpaceId") Integer workSpaceId);

    @DELETE("InstructorReqOfferedCourse/cancelCourseRequest")
    Call<Void> cancelCourseRequest(@Header("Authorization") String token,@Query("instructorId")Integer instrctorId,@Query("courseId")Integer courseId);
}
