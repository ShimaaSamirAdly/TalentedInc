package inc.talentedinc.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import inc.talentedinc.R;
import inc.talentedinc.model.Result;
import inc.talentedinc.presenter.UpComingDetailsPresenter;
import inc.talentedinc.utilitis.ActionUtils;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class UpComingDetailsActivity extends AppCompatActivity implements UpComingDetailsPresenter.ViewUpComingDetails, MaterialRatingBar.OnRatingChangeListener {

    /****************************** asmaa *************************/

    public static final String COURSE ="course";
    private UpComingDetailsPresenter presenter;
    private MaterialRatingBar ratingBar;


    private ImageView img;
    private TextView tvName, tvVote, tvId, tvOverView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming_details);
        initView();
    }

    /****************************** *************************/

    /****************************** asmaa *************************/

    private void initView(){
        ratingBar = findViewById(R.id.mRating);
        ratingBar.setOnRatingChangeListener(this);
        img=findViewById(R.id.img);
        tvName =findViewById(R.id.tvname);
        tvId =findViewById(R.id.tvId);
        tvVote=findViewById(R.id.tvVote);
        tvOverView=findViewById(R.id.tvOverView);
        presenter = new UpComingDetailsPresenter();
        presenter.setView((Result) getIntent().getSerializableExtra(COURSE),this);
//        ColorRatingBar colorRatingBar = new ColorRatingBar(this);
//        colorRatingBar.setRatingProgressColor(R.color.colorPrimary);
//        colorRatingBar.setRatingEmptyColor(R.color.colorAccent);
//        colorRatingBar.setRating(3.0f);
    }
    /******************************  *************************/

    /****************************** asmaa *************************/

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setTitle(String title) {
        tvName.setText(title);

    }

    @Override
    public void setOverview(String overview) {
        tvOverView.setText(overview);
    }

    @Override
    public void setPoster(String imgs) {
        if(imgs != null ) {
            Glide.with(this).load("http://image.tmdb.org/t/p/w185/"+imgs).centerCrop().placeholder(R.drawable.ic_launcher_background).into(img);
        }
    }

    @Override
    public void setVote(float vote) {
        tvVote.setText(String.valueOf(vote));
    }


    @Override
    public void setId(int id) {
        tvId.setText(String.valueOf(id));

    }

    @Override
    public void isFav(boolean isFav) {


    }

    @Override
    public void showDone(String msg) {
        ActionUtils.showToast(this,msg);
    }

    @Override
    public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
        ActionUtils.showToast(this, rating+"");

    }

    /******************************  *************************/

}
