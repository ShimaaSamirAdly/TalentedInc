package inc.talentedinc.viewholder;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import inc.talentedinc.R;
import inc.talentedinc.view.customviews.ExpandableHeightGridView;

/**
 * Created by MMM on 6/8/2018.
 */

public class SkillsViewHolder extends RecyclerView.ViewHolder {

    private EditText skillText;
    private View skillsCard;
    private ImageView addSkill;
    private ExpandableHeightGridView skillsData;

    public SkillsViewHolder(View itemView) {
        super(itemView);
        //addSkill = itemView.findViewById(R.id.addSkill);
        skillsCard = itemView;
//        skillsData = itemView.findViewById(R.id.skillsData);
//        skillsData.setExpanded(true);

    }

    public EditText getSkillText(){
        if(skillText == null){
            skillText = skillsCard.findViewById(R.id.skillText);
        }

        return skillText;
    }

    public ImageView getAddSkill(){

        if(addSkill == null){
            addSkill = skillsCard.findViewById(R.id.addSkill);
        }

        return addSkill;
    }

    public ExpandableHeightGridView getSkillsData(){
        if(skillsData == null){
            skillsData = skillsCard.findViewById(R.id.skillsData);
            skillsData.setExpanded(true);
        }

        return skillsData;
    }
}
