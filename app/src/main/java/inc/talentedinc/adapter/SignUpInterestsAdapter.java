package inc.talentedinc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.model.Categories;

/**
 * Created by MMM on 5/26/2018.
 */

public class SignUpInterestsAdapter extends BaseAdapter {

    private Context mContext;
    private List<Categories> categories;

    public SignUpInterestsAdapter(Context c, List<Categories> categories) {
        mContext = c;
        this.categories = categories;
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

        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            grid = inflater.inflate(R.layout.interests_grid_items, parent, false);

            ImageView imageView = grid.findViewById(R.id.img);
            TextView text = grid.findViewById(R.id.txt);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
        Categories category = categories.get(position);
        String categoryName = category.getName();
            imageView.setImageResource(mContext.getResources().
                    getIdentifier(categoryName.toLowerCase(), "drawable", mContext.getPackageName()));
        text.setText(category.getName());
        grid.setId(category.getCategoryId());


        return grid;
    }




//    public void setCategories(List<Categories> categories){
//
//        this.categories = categories;
//    }
}
