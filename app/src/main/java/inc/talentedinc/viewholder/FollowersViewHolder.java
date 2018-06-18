package inc.talentedinc.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import inc.talentedinc.R;

/**
 * Created by MMM on 6/9/2018.
 */

public class FollowersViewHolder extends RecyclerView.ViewHolder {

    private View followersCard;
    private CircleImageView followerImg;
    private TextView followerName;
    private Button showProfile;

    public FollowersViewHolder(View itemView) {
        super(itemView);
        followersCard = itemView;

    }

    public View getFollowersCard(){
        return followersCard;
    }

    public CircleImageView getFollowerImg() {

        if(followerImg == null){
            followerImg = followersCard.findViewById(R.id.followerImage);
        }
        return followerImg;
    }

    public TextView getFollowerName() {

        if(followerName == null){
            followerName = followersCard.findViewById(R.id.followerName);
        }
        return followerName;
    }

    public Button getShowProfile() {

        if(showProfile == null){
            showProfile = followersCard.findViewById(R.id.showProfile);
        }
        return showProfile;
    }
}
