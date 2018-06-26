package inc.talentedinc.API;


import java.util.ArrayList;

import inc.talentedinc.model.Followers;
import inc.talentedinc.model.OtherUsers;
import inc.talentedinc.model.User;
import inc.talentedinc.model.request.FollowRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MMM on 5/23/2018.
 */

public interface ProfileEndpoint {

    @PUT("/users/update")
    Call<Void> updateUser(@Header("Authorization") String token,@Body User user);

    @GET("/users/{id}/profile")
    Call<OtherUsers> getUserProfile(@Header("Authorization") String token,@Path("id") int id, @Query("userId") int userId, @Header("Cache-Control") String cacheControl);

    @GET("/users/{id}/myProfile")
    Call<User> getCurrentUserProfile(@Header("Authorization") String token,@Path("id") int userId, @Header("Cache-Control") String cacheControl);

    @POST("/users/followUser")
    Call<Void> followUser(@Header("Authorization") String token, @Body FollowRequest followRequest);

    @DELETE("/users/unFollowUser")
    Call<Void> unfollowUser(@Header("Authorization") String token,@Query("id") int currentUserId, @Query("userToUnFollowId") int followedUserId);

    @GET("/users/{id}/following")
    Call<ArrayList<Followers>> getFollowing(@Header("Authorization") String token,@Path("id") int userId);

    @GET("/users/{id}/followers")
    Call<ArrayList<Followers>> getFollowers(@Header("Authorization") String token,@Path("id") int userId);
}
