package inc.talentedinc.interactor.register;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.ApiHomeEndpoint;
import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.model.request.MainRequest;
import inc.talentedinc.model.response.BaseResponse;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by asmaa on 06/07/2018.
 */

public class NetworkRegisterInteractor implements RegisterInteractor {
    private ApiHomeEndpoint mApi = AppRetrofit.getInstance().getHomeApi();

    @Override
    public void setRegister(MainRequest mainRequest, final OnCommentLikeRateResult onCommentLikeRateResult) {
        Call<BaseResponse> call;
        call = mApi.setRegisterCourse(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),mainRequest);
        call.clone().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.code()== APIUrls.SUCCESS) {

                    if (response.body() != null) {
                        onCommentLikeRateResult.onSuccess(response.body());
                    }
                }else {
                    onCommentLikeRateResult.onFailure();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onCommentLikeRateResult.onFailure();

            }
        });
    }

    @Override
    public void unRegister(MainRequest mainRequest, final OnCommentLikeRateResult onCommentLikeRateResult) {
        Call<BaseResponse> call;
        call = mApi.unRegister(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),mainRequest);
        call.clone().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.code()== APIUrls.SUCCESS) {

                    if (response.body() != null) {
                        onCommentLikeRateResult.onSuccess(response.body());
                    }
                }else {
                    onCommentLikeRateResult.onFailure();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onCommentLikeRateResult.onFailure();

            }
        });

    }
}
