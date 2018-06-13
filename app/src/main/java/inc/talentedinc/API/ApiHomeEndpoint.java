package inc.talentedinc.API;

/**
 * Created by asmaa on 05/17/2018.
 */

import inc.talentedinc.model.response.BaseResponse;
import inc.talentedinc.model.response.CoursesResponse;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * interface to all retrofit requests , type of method request and parameters or request body
 * and handle this with model classes to parse json response to pojo classes
 */
public interface ApiHomeEndpoint {

    // upcoming
    @GET(APIUrls.UPCOMING)
    Call<CoursesResponse> getUpComing(@Query("userId") int userId,
                                      @Query("page") int page);
    // search by name
    @GET(APIUrls.SEARCH_BY_NAME)
    Call<CoursesResponse> getSearchByName(@Query("userId") int userId,
                                          @Query("courseName") String courseName,
                                          @Query("page") int page);
    // search by filter
    @GET(APIUrls.SEARCH_BY_FILTER)
    Call<CoursesResponse> getSearchByFilter(@Query("userId") int userId,
                                            @Query("category") String category,
                                            @Query("city") String city,
                                            @Query("page") int page);
    // history
    @GET(APIUrls.HISTORY+"/{userId}/{finishedCourses}")
    Call<CoursesResponse> getHistory(@Path("userId") int userId,
                                  @Path("finishedCourses") String finishedCourses,
                                  @Query("page")int page);
    // register course
    @POST(APIUrls.REGISTER_COURSE)
    Call<BaseResponse> setRegisterCourse(@Query("userId") int userId,
                                         @Query("courseId") int courseId,
                                         @Query("courseDate")String date);
    // unRegister course
    @DELETE(APIUrls.UNREGISTER_COURSE)
    Call<BaseResponse> unRegister(@Query("userId") int userId,
                                  @Query("courseId") int courseId,
                                  @Query("courseDate")String date);
    // like
    @POST(APIUrls.LIKE)
    Call<BaseResponse> setLike(@Query("userId") int userId,
                               @Query("courseId") int courseId,
                               @Query("courseDate")String date);

    // disLike
    @DELETE(APIUrls.DISLIKE)
    Call<BaseResponse> setDisLike(@Query("userId") int userId,
                                  @Query("courseId") int courseId,
                                  @Query("courseDate")String date);
    // comment
    @POST(APIUrls.COMMENT)
    Call<BaseResponse> setComment(@Query("userId") int userId,
                                  @Query("courseId") int courseId,
                                  @Query("courseDate")String date,
                                  @Query("comment")String comment);
    // rate
    @POST(APIUrls.RATE)
    Call<BaseResponse> setRate(@Query("userId") int userId,
                                  @Query("courseId") int courseId,
                                  @Query("courseDate")String date,
                                  @Query("courseRate")float courseRate,
                                  @Query("instructorRate")float instructorRate,
                                  @Query("workSpaceRate")float workSpaceRate);
}
