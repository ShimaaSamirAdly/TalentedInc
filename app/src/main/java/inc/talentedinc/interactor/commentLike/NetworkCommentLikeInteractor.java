package inc.talentedinc.interactor.commentLike;

import android.util.Log;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.ApiHomeEndpoint;
import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.model.request.CommentRequest;
import inc.talentedinc.model.request.MainRequest;
import inc.talentedinc.model.response.BaseResponse;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by asmaa on 06/04/2018.
 */

public class NetworkCommentLikeInteractor implements CommentLikeInteractor {


    private ApiHomeEndpoint mApi = AppRetrofit.getInstance().getHomeApi();

    @Override
    public void setLike(MainRequest mainRequest, final OnCommentLikeRateResult onCommentLikeRateResult) {

        Call<BaseResponse> call;
        call = mApi.setLike(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),mainRequest);
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
    public void setDisLike(int userIid,int courseId,String courseDate, final OnCommentLikeRateResult onCommentLikeRateResult) {

        Call<BaseResponse> call;
        call = mApi.setDisLike(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),userIid,courseId,courseDate);
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
                Log.i("DisLike",t.getMessage());
                onCommentLikeRateResult.onFailure();
            }
        });
    }

    @Override
    public void setComment(CommentRequest commentRequest, final OnCommentLikeRateResult onCommentLikeRateResult) {

        Call<BaseResponse> call;
        call = mApi.setComment(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),commentRequest);
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
