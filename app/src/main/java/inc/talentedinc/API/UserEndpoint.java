package inc.talentedinc.API;

import inc.talentedinc.model.User;
import inc.talentedinc.model.response.MainResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by MMM on 5/27/2018.
 */

public interface UserEndpoint {

    @POST("/users/add")
    Call<MainResponse> insertUser(@Header("Device-Token") String deviceToken,@Body User user);

    @PUT("/users/update")
    Call<User> updateUser( @Header("Authorization") String token,@Body User user);

    @GET("users/{email}")
    Call<User> getUser(String email);


}
