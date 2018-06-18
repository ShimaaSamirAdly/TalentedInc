package inc.talentedinc.presenter;

import android.util.Log;

import java.util.List;

import inc.talentedinc.interactor.categories.CategoriesInteractor;
import inc.talentedinc.interactor.categories.CategoriesInteractorImpl;
import inc.talentedinc.listener.CategoriesListener;
import inc.talentedinc.model.Categories;

/**
 * Created by Alaa on 6/4/2018.
 */

public class CategoriesPresenter {

    CategoriesView view;
    private CategoriesInteractor categoriesInteractor;


    public CategoriesPresenter(CategoriesView view) {
        this.view = view;
        categoriesInteractor = new CategoriesInteractorImpl();

    }

    public void getCategories() {
        categoriesInteractor.getAllCategories(new CategoriesListener() {
            @Override
            public void onSuccess(List<Categories> categoriesList) {
                view.recievedCategories(categoriesList);
                Log.i("categoryGat","done");
            }

            @Override
            public void onFailed() {

                Log.i("fail", "faild to get categories");
            }

            @Override
            public void onFailedConnection() {

            }
        });


    }


    public interface CategoriesView {

        void recievedCategories(List<Categories> categories);

    }
}
