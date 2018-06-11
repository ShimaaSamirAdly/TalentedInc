package inc.talentedinc.API;

import inc.talentedinc.model.Instructor;
import inc.talentedinc.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by MMM on 6/8/2018.
 */

public interface InstructorEndpoint {

    @POST("/instructors/add")
    Call<User> becomeInstructor(@Body Instructor instructor);
}
