package inc.talentedinc.API;

/**
 * Created by asmaa on 05/17/2018.
 */

import inc.talentedinc.model.MoviesData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * interface to all retrofit requests , type of method request and parameters or request body
 * and handle this with model classes to parse json response to pojo classes
 */
public interface ApiUpComingEndpoint {

    @GET(APIUrls.DISCOVER_MOVIES)
    Call<MoviesData> getMovies(@Query("api_key") String apiKey,
                               @Query("page") int page);

    @GET(APIUrls.SEARCH_MOVIES)
    Call<MoviesData> getSearchResult(@Query("api_key") String apiKey,
                                     @Query("query") String keyWord,
                                     @Query("page") int page);
}
