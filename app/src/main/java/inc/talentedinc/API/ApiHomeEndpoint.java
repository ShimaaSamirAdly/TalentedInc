package inc.talentedinc.API;

/**
 * Created by asmaa on 05/17/2018.
 */

import inc.talentedinc.model.UpComingData;
import inc.talentedinc.model.response.BaseResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * interface to all retrofit requests , type of method request and parameters or request body
 * and handle this with model classes to parse json response to pojo classes
 */
public interface ApiHomeEndpoint {

    // upcoming
    @GET(APIUrls.UPCOMING)
    Call<UpComingData> getUpComing(@Query("api_key") String apiKey,
                                   @Query("page") int page);
    // search
    @GET(APIUrls.SEARCH_UPCOMING)
    Call<UpComingData> getSearchResult(@Query("api_key") String apiKey,
                                       @Query("query") String keyWord,
                                       @Query("page") int page);
    // history
    @GET(APIUrls.HISTORY)
    Call<UpComingData> getHistory(@Query("api_key") String apiKey,
                                  @Query("page") int page);
    // like
    @POST(APIUrls.LIKE)
    Call<BaseResponse> setLike(@Query("userId") int userId,
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
