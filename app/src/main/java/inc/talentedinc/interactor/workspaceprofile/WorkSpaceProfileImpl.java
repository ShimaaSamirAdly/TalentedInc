package inc.talentedinc.interactor.workspaceprofile;

import android.util.Log;

import inc.talentedinc.API.ApiWorkSpaceProfile;
import inc.talentedinc.listener.OnReceivedWorkSpaceProfile;
import inc.talentedinc.model.WorkSpace;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alaa on 6/9/2018.
 */

public class WorkSpaceProfileImpl implements WorkSpaceProfileInter {
    ApiWorkSpaceProfile apiWorkSpaceProfile = AppRetrofit.getInstance().getApiWorkSpaceProfile();

    @Override
    public void receivedProfile( Integer workSpaceProfile , final OnReceivedWorkSpaceProfile receivedWorkSpaceProfile) {
        apiWorkSpaceProfile.getWorkSpaceProfile(workSpaceProfile).enqueue(new Callback<WorkSpace>() {
            @Override
            public void onResponse(Call<WorkSpace> call, Response<WorkSpace> response) {
                Log.i("WorkSpaceIsHere",response.body().getName());
                receivedWorkSpaceProfile.onSuccess(response.body());


            }

            @Override
            public void onFailure(Call<WorkSpace> call, Throwable t) {
                Log.i("workSpaceFailed",call.toString() );
                receivedWorkSpaceProfile.onFail();
                call.cancel();
            }
        });

    }
}
