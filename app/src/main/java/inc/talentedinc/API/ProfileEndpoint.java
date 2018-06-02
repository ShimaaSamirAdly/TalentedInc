package inc.talentedinc.API;


import inc.talentedinc.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Created by MMM on 5/23/2018.
 */

public interface ProfileEndpoint {

    @PUT("/users/update")
    Call<User> updateUser(@Body User user);

    @GET("users/{email}")
    Call<User> getUser(String email);
}
