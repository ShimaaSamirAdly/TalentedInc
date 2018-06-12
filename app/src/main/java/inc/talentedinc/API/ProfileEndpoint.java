package inc.talentedinc.API;


import java.util.ArrayList;

import inc.talentedinc.model.Followers;
import inc.talentedinc.model.OtherUsers;
import inc.talentedinc.model.User;
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
    Call<Void> updateUser(@Body User user);

    @GET("/users/{id}/profile")
    Call<OtherUsers> getUserProfile(@Path("id") int id, @Query("userId") int userId, @Header("Cache-Control") String cacheControl);

    @GET("/users/{id}/myProfile")
    Call<User> getCurrentUserProfile(@Path("id") int userId, @Header("Cache-Control") String cacheControl);

    @POST("/users/followUser")
    Call<User> followUser(@Query("id") int currentUserId, @Query("userToFollowId") int followedUserId);

    @DELETE("/users/unFollowUser")
    Call<User> unfollowUser(@Query("id") int currentUserId, @Query("userToUnFollowId") int followedUserId);

    @GET("/users/{id}/following")
    Call<ArrayList<Followers>> getFollowing(@Path("id") int userId);

    @GET("/users/{id}/followers")
    Call<ArrayList<Followers>> getFollowers(@Path("id") int userId);
}
