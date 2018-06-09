package inc.talentedinc.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import inc.talentedinc.R;

/**
 * Created by MMM on 6/8/2018.
 */

public class PortofolioViewHolder extends RecyclerView.ViewHolder {

    private View portofolioCard;
    private ImageView firstImg;
    private ImageView secondImg;
    private EditText videoUrl;
    private Button submitInstructor;

    public PortofolioViewHolder(View itemView) {
        super(itemView);

        portofolioCard = itemView;
    }

    public ImageView getFirstImg(){

        if(firstImg == null){
            firstImg = portofolioCard.findViewById(R.id.firstImage);
        }

        return firstImg;
    }

    public ImageView getSecondImg(){

        if(secondImg == null){
            secondImg = portofolioCard.findViewById(R.id.secondImage);
        }

        return secondImg;
    }

    public EditText getVideoUrl(){
        if(videoUrl == null){
            videoUrl = portofolioCard.findViewById(R.id.videoUrl);
        }

        return videoUrl;
    }

    public Button getSubmitInstructor(){

        if(submitInstructor == null){
            submitInstructor = portofolioCard.findViewById(R.id.submitInstructor);
        }

        return  submitInstructor;
    }
}
