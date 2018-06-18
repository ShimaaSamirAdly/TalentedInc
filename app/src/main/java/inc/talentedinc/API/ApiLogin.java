package inc.talentedinc.API;

import inc.talentedinc.model.User;
import inc.talentedinc.model.UserLogin;
import inc.talentedinc.model.response.MainResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Alaa on 5/20/2018.
 */

public interface ApiLogin {

    @POST(APIUrls.USER_LOGIN)
    Call<User> checkUserLogin(@Header("Device-Token") String token,@Body UserLogin userLogin);

}
