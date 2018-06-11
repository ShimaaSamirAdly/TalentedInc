package inc.talentedinc.API;

import java.util.List;

import inc.talentedinc.model.Categories;
import inc.talentedinc.model.WorkSpace;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alaa on 6/9/2018.
 */

public interface ApiWorkSpaceProfile {

    @GET(APIUrls.WORK_SPACE_PROFILE)
    Call<WorkSpace> getWorkSpaceProfile(@Query("workSpaceId") Integer workSpaceId);
}
