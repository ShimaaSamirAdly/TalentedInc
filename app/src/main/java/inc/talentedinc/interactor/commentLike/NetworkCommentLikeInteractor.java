package inc.talentedinc.interactor.commentLike;

import android.util.Log;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.ApiHomeEndpoint;
import inc.talentedinc.listener.OnCommentLikeRateResult;
import inc.talentedinc.model.response.BaseResponse;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asmaa on 06/04/2018.
 */

public class NetworkCommentLikeInteractor implements CommentLikeInteractor {


    private ApiHomeEndpoint mApi = AppRetrofit.getInstance().getHomeApi();

    @Override
    public void setLike(int userIid, int courseId, String courseDate, final OnCommentLikeRateResult onCommentLikeRateResult) {

        Call<BaseResponse> call;
        call = mApi.setLike(userIid,courseId,courseDate);
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
    public void setDisLike(int userIid, int courseId, String courseDate, final OnCommentLikeRateResult onCommentLikeRateResult) {

        Call<BaseResponse> call;
        call = mApi.setDisLike(userIid,courseId,courseDate);
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
    public void setComment(int userIid, int courseId, String courseDate, String comment, final OnCommentLikeRateResult onCommentLikeRateResult) {

        Call<BaseResponse> call;
        call = mApi.setComment(userIid,courseId,courseDate,comment);
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