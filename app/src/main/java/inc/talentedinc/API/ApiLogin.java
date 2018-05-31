package inc.talentedinc.API;

import inc.talentedinc.model.response.MainResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Alaa on 5/20/2018.
 */

public interface ApiLogin {


    @FormUrlEncoded
    @POST("/api/users?")
    Call<MainResponse> checkUserLogin(@Field("email") String userEmail, @Field("password") String userPassword);

}
