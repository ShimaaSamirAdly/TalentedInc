package inc.talentedinc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.model.InstructorImages;

/**
 * Created by MMM on 6/1/2018.
 */

public class PortofolioAdapter extends BaseAdapter {

    private List<InstructorImages> imagesUrl = new ArrayList<>();
    private Context context;

    public PortofolioAdapter(Context context, List<InstructorImages> urls){

        this.context = context;
        this.imagesUrl = urls;
    }

    @Override
    public int getCount() {
        return imagesUrl.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.i("ada", "adapter");
        grid = inflater.inflate(R.layout.portofolio_grid_item, parent, false);
        Log.i("setView", "adapter");
        ImageView imageView = grid.findViewById(R.id.img);

            Glide.with(context)
                    .load(imagesUrl.get(position).getImageUrl())
                    .into(imageView);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        imageView.setImageResource(imagesUrl.get(position));
//            text.setText(categories.get(position).getName());
//            grid.setId(categories.get(position).getCategoryId());

        return grid;
    }
}
