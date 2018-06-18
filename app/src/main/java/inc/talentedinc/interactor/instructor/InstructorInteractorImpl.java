package inc.talentedinc.interactor.instructor;

import android.util.Log;

import java.util.List;

import inc.talentedinc.API.CategoryEndpoint;
import inc.talentedinc.API.InstructorEndpoint;
import inc.talentedinc.listener.CategoriesListener;
import inc.talentedinc.listener.InstructorListener;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.Instructor;
import inc.talentedinc.model.User;
import inc.talentedinc.singleton.AppRetrofit;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by MMM on 6/8/2018.
 */

public class InstructorInteractorImpl implements InstructorInteractor {

    InstructorEndpoint instructorEndpoint = AppRetrofit.getInstance().getRetrofitInstance().create(InstructorEndpoint.class);

    public void becomeInstructor(final Instructor instructor, final InstructorListener listener){

            Call<User> call = instructorEndpoint.becomeInstructor(SharedPrefrencesSingleton.getSharedPrefToken(getApplicationContext()),instructor);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.i("conn", ""+response.code());
                    if(response.code() == 200) {
                        listener.onSuccessPending(instructor);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.i("conn", t.getMessage());
                }
            });
        }
    }

