package inc.talentedinc.listener;

import java.util.List;

import inc.talentedinc.model.Categories;

/**
 * Created by MMM on 5/27/2018.
 */

public interface CategoriesListener {

    public void onSuccess(List<Categories> categoriesList);

    public void onFailed();

    public void onFailedConnection();
}
