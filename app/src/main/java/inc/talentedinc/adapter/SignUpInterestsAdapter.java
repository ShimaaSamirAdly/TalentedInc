package inc.talentedinc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


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
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Toast.makeText(mContext, "adapteer", Toast.LENGTH_LONG).show();
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.i("ada", "adapter");
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.interests_grid_items, parent, false);
        Log.i("setView", "adapter");
            ImageView imageView = grid.findViewById(R.id.img);
            TextView text = grid.findViewById(R.id.txt);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(mThumbIds[position]);
//            text.setText(categories.get(position).getName());
//            grid.setId(categories.get(position).getCategoryId());

        return grid;
    }


    private Integer[] mThumbIds = {
            R.drawable.camerahd, R.drawable.camerahd,
            R.drawable.camerahd, R.drawable.camerahd,
            R.drawable.camerahd, R.drawable.camerahd,
            R.drawable.camerahd, R.drawable.camerahd,
            R.drawable.camerahd, R.drawable.camerahd,
            R.drawable.camerahd, R.drawable.camerahd
    };

//    public void setCategories(List<Categories> categories){
//
//        this.categories = categories;
//    }
}
