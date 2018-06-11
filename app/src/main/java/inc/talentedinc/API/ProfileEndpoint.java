package inc.talentedinc.API;


import java.util.ArrayList;

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

    @GET("/users/{id}/myProfile")
    Call<User> getCurrentUserProfile(@Path("id") int userId);

    @GET("/users/followUser")
    Call<User> followUser(@Query("id") int currentUserId, @Query("userToFollowId") int followedUserId);

    @GET("/users/unFollowUser")
    Call<User> unfollowUser(@Query("id") int currentUserId, @Query("userToUnFollowId") int followedUserId);

    @GET("/users/{id}/following")
    Call<ArrayList<User>> getFollowing(@Path("id") int userId);

    @GET("/users/{id}/followers")
    Call<ArrayList<User>> getFollowers(@Path("id") int userId);
}
