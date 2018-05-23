package inc.talentedinc.API.profile;

import retrofit2.http.PUT;

/**
 * Created by MMM on 5/23/2018.
 */

public interface ProfileEndpoint {

    @PUT("/users/update")
    Call<User> updateUser(User user);
}
