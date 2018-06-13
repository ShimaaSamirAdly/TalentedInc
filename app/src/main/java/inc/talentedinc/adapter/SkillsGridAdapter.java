package inc.talentedinc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.model.Categories;

/**
 * Created by MMM on 6/8/2018.
 */

public class SkillsGridAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> skills;

    public SkillsGridAdapter(Context context, ArrayList<String> skills){
        this.context = context;
        this.skills = skills;
    }

    public void setSkills(ArrayList<String> skills){
        this.skills = skills;
    }

    @Override
    public int getCount() {
        return skills.size();
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

        final View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        grid = inflater.inflate(R.layout.skills_grid_items, parent, false);

        final TextView text = grid.findViewById(R.id.txt);
        text.setText(skills.get(position));

//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                skills.remove(text.getText());
//                notifyDataSetChanged();
//            }
//        });

        return grid;
    }
}
