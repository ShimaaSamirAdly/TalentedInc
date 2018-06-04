package inc.talentedinc.API;


import inc.talentedinc.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MMM on 5/23/2018.
 */

public interface ProfileEndpoint {

    @PUT("/users/update")
    Call<User> updateUser(@Body User user);

    @GET("/users/{id}/profile")
    Call<User> getUserProfile(@Path("id") int id, @Query("userId") int userId);
}
