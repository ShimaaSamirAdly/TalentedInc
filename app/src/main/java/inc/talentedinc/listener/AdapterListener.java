package inc.talentedinc.listener;

import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by MMM on 6/8/2018.
 */

public interface AdapterListener {

    public void onClickedImage(int i, ImageView img);

    public Button getFBButton();

}