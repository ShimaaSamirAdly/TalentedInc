package inc.talentedinc.interactor.history;

import android.util.Log;

import java.util.ArrayList;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.ApiHomeEndpoint;
import inc.talentedinc.listener.OnCoursesResult;
import inc.talentedinc.model.CourseComment;
import inc.talentedinc.model.HostingWorkSpaceId;
import inc.talentedinc.model.InstructorId;
import inc.talentedinc.model.Result;
import inc.talentedinc.model.response.CoursesResponse;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by asmaa on 05/21/2018.
 */

public class NetworkHistoryInteractor implements HistoryInteractor {

    private ApiHomeEndpoint mApi = AppRetrofit.getInstance().getHomeApi();
    private ArrayList<Result> data;

    public  static int totalPage = 1;
    void dummyData(){
        totalPage =1;
        data = new ArrayList<>();
        Result r1 = new Result();
        r1.setName("Asmaa Test");
        r1.setDescription("ay kalam b2a mat5no2nash :D");
        r1.setDurationHours(200);
        r1.setNumberOfComments(1);
        r1.setNumberOfLikes(3);
        r1.setOfferedCourseId(1);
        r1.setLiked(true);

        InstructorId i1 = new InstructorId();
        i1.setUserId(2);
        r1.setInstructorId(i1);

        HostingWorkSpaceId h = new HostingWorkSpaceId();
        h.setAddress("address");
        h.setName("workspace");
        r1.setHostingWorkSpaceId(h);

        CourseComment c1 = new CourseComment();
        c1.setComment("test1");
        c1.setUserIdOfComment(1);
        c1.setTime("8/5/2018");

        CourseComment c2 = new CourseComment();
        c2.setComment("test1");
        c2.setUserIdOfComment(1);
        c2.setTime("8/5/2018");

        CourseComment c3 = new CourseComment();
        c3.setComment("test1");
        c3.setUserIdOfComment(1);
        c3.setTime("8/5/2018");

        ArrayList<CourseComment> t = new ArrayList<>();
        t.add(c1);
        t.add(c2);
        t.add(c3);

        r1.setCourseComments(t);
        data.add(r1);

        Result r2 = new Result();
        r2.setName("Asmaa Test");
        r2.setDescription("ay kalam b2a mat5no2nash :D");
        r2.setDurationHours(200);
        r2.setNumberOfComments(1);
        r2.setNumberOfLikes(3);
        r2.setOfferedCourseId(1);
        r2.setLiked(false);

        data.add(r1);

        Result r3 = new Result();
        r3.setName("Asmaa Test");
        r3.setLiked(true);
        data.add(r3);

        Result r4 = new Result();
        r4.setName("Asmaa Test");
        r4.setLiked(false);
        data.add(r4);

        Result r5 = new Result();
        r5.setName("Asmaa Test");
        r5.setLiked(false);
        data.add(r5);

        Result r6 = new Result();
        r6.setName("Asmaa Test");
        r6.setLiked(false);
        data.add(r6);

        Result r7 = new Result();
        r7.setName("Asmaa Test");
        r7.setLiked(false);
        data.add(r7);
    }
    @Override
    public void getHistory(int userId ,int page, final OnCoursesResult onCoursesResult) {
//        dummyData();
//        onCoursesResult.onSuccess(data);
       // Log.i("TOKKEN",SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()));

        Call<CoursesResponse> call;
        call = mApi.getHistory(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),userId,APIUrls.finishedCourses,page);
        call.clone().enqueue(new Callback<CoursesResponse>() {
            @Override
            public void onResponse(Call<CoursesResponse> call, Response<CoursesResponse> response) {
                data = new ArrayList<>();
                if (response.code()== APIUrls.SUCCESS) {
                    totalPage = response.body().getTotalNumberOfPages();
                    Log.i("ttttttt", response.body().getTotalNumberOfUpComingCourses() + "");
                    if (response.body().getResult().size() != 0) {
                        for (int i = 0; i < response.body().getResult().size(); i++) {
                            data.add(response.body().getResult().get(i));
                        }
                        onCoursesResult.onSuccess(data);
                    }
                }
            }
            @Override
            public void onFailure(Call<CoursesResponse> call, Throwable t) {
                Log.e("error: ",t.getMessage());
                onCoursesResult.onFailure();
            }
        });
    }
}
