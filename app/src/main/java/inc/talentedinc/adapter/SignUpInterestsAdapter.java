package inc.talentedinc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.model.Categories;

/**
 * Created by MMM on 5/26/2018.
 */

public class SignUpInterestsAdapter extends BaseAdapter {

    private Context mContext;
    private List<Categories> categories;
    private Collection<Categories> userCategories;

    public SignUpInterestsAdapter(Context c, List<Categories> categories) {
        mContext = c;
        this.categories = categories;
        this.userCategories = null;
    }

    public SignUpInterestsAdapter(Context c, List<Categories> categories, Collection<Categories> userCategories) {
        mContext = c;
        this.categories = categories;
        this.userCategories = userCategories;
    }

    public int getCount() {
        Log.i("count", ""+categories.size());
        return categories.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("inters", "getView");
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            grid = inflater.inflate(R.layout.interests_grid_items, parent, false);

            boolean flag = true;

            ImageView imageView = grid.findViewById(R.id.img);
            TextView text = grid.findViewById(R.id.txt);

        Categories category = categories.get(position);
        String categoryName = category.getName();
        if(userCategories != null) {
            Iterator<Categories> iter = userCategories.iterator();
            while (iter.hasNext()) {
                Categories iterCategory = iter.next();
                if (category.getCategoryId() == iterCategory.getCategoryId()) {

                    flag = false;
                    break;
                }
            }
        }

        if(flag){

            imageView.setImageResource(mContext.getResources().
                    getIdentifier(categoryName.toLowerCase(), "drawable", mContext.getPackageName()));
        }else{

            imageView.setImageResource(mContext.getResources().
                    getIdentifier(categoryName.toLowerCase() + "selc", "drawable", mContext.getPackageName()));
        }

        text.setText(category.getName());
        grid.setId(category.getCategoryId());


        return grid;
    }




//    public void setCategories(List<Categories> categories){
//
//        this.categories = categories;
//    }
}
