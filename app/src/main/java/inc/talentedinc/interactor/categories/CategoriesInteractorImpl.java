package inc.talentedinc.interactor.categories;

import android.util.Log;

import java.util.List;

import inc.talentedinc.API.CategoryEndpoint;
import inc.talentedinc.listener.CategoriesListener;
import inc.talentedinc.model.Categories;
import inc.talentedinc.singleton.AppRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MMM on 6/1/2018.
 */

public class CategoriesInteractorImpl implements CategoriesInteractor {


    CategoryEndpoint categoryEndpoint = AppRetrofit.getInstance().getRetrofitInstance().create(CategoryEndpoint.class);

    @Override
    public void getAllCategories(final CategoriesListener listener) {

        Call<List<Categories>> call = categoryEndpoint.getAllCategories();
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                Log.i("conn", "Success");
                Log.i("callDone",response.body().toString());

                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Log.i("conn", "failed");
                Log.i("callFail","notYet");
            }
        });
    }
}
