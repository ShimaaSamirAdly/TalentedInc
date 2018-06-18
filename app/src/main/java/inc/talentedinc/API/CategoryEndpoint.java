package inc.talentedinc.API;

import java.util.List;
import inc.talentedinc.model.Categories;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by MMM on 5/27/2018.
 */

public interface CategoryEndpoint {

    @GET("/categories")
    Call<List<Categories>> getAllCategories(@Header("Authorization") String token);
}
