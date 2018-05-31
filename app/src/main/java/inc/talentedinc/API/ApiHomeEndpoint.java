package inc.talentedinc.API;

/**
 * Created by asmaa on 05/17/2018.
 */

import inc.talentedinc.model.UpComingData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * interface to all retrofit requests , type of method request and parameters or request body
 * and handle this with model classes to parse json response to pojo classes
 */
public interface ApiHomeEndpoint {

    @GET(APIUrls.UPCOMING)
    Call<UpComingData> getUpComing(@Query("api_key") String apiKey,
                               @Query("page") int page);

    @GET(APIUrls.SEARCH_UPCOMING)
    Call<UpComingData> getSearchResult(@Query("api_key") String apiKey,
                                     @Query("query") String keyWord,
                                     @Query("page") int page);


    @GET(APIUrls.HISTORY)
    Call<UpComingData> getHistory(@Query("api_key") String apiKey,
                               @Query("page") int page);

    // comment
    // like
    // rate


}
