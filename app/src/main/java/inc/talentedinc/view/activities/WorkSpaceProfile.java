package inc.talentedinc.view.activities;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.model.WorkSpace;
import inc.talentedinc.presenter.WorkSpaceProfilePresenter;

public class WorkSpaceProfile extends AppCompatActivity  implements WorkSpaceProfilePresenter.WorkSpaceProfileView {
    //----------------------------------------Alaa----------------------------------------------------//

    private TextView workSpaceName ;
    private TextView workSpaceEmail;
    private TextView workSpaceOpeningTime ;
    private TextView workSpaceClosingTime ;
    private TextView workSpaceFaceBookUrl ;
    private TextView workSpaceHolidays;
    private TextView workSapceAddress ;
    private ImageView workSpaceLogo;
    private ImageView workSpacePhoto1;
    private ImageView workSpacePhoto2 ;
    private ImageView workSpacePhoto3;
    private WorkSpace workSpace ;
    private WorkSpaceProfilePresenter workSpaceProfilePresenter ;
    //--------------------------------------------------------------------------------------------//
    //--------------------------------------------------------------------------------------------//



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_space_profile);

        workSpaceLogo = findViewById(R.id.workSpaceLogo);
        workSpaceEmail = findViewById(R.id.workSpaceEmail);
        workSpaceOpeningTime = findViewById(R.id.openTime);
        workSpaceClosingTime = findViewById(R.id.closeTime);
        workSpaceName = findViewById(R.id.workSpaceName);
        workSpaceFaceBookUrl = findViewById(R.id.facebookUrl);
        workSpaceHolidays = findViewById(R.id.holidays);
        workSapceAddress = findViewById(R.id.workSpaceAddress);
        workSpacePhoto1 = findViewById(R.id.imageUrl1);
        workSpacePhoto2 = findViewById(R.id.imageUrl2);
        workSpacePhoto3 = findViewById(R.id.imageUrl3);
        workSpaceProfilePresenter = new WorkSpaceProfilePresenter(this);
        workSpaceProfilePresenter.getWorkSpaceData(1);

    }

    //*******************************************************************************************//
    @Override
    public void sendWorkSpaceData(WorkSpace workSpace) {
        this.workSpace = workSpace ;
        setWorkspaceData();

    }

    //******************************************************************************************//
    @Override
    public void failToSendWorkSapceData() {
        Log.i("failedToLoadData","bazt");
    }

    private void setWorkspaceData(){
        //phones and discription//
        List<Object> photos =  workSpace.getWorkSpaceImagesCollection();
        String photo1 = (String) photos.get(0);
        String photo2 = (String) photos.get(1);
        String photo3 = (String) photos.get(2);


        Glide.with(this)
                .load("" + workSpace.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(workSpaceLogo);

        Glide.with(this)
                .load("" + photo1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(workSpacePhoto1);

        Glide.with(this)
                .load("" + photo2)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(workSpacePhoto2);
        Glide.with(this)
                .load("" + photo3)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(workSpacePhoto3);


        workSapceAddress.setText(workSpace.getAddress());
        workSpaceName.setText(workSpace.getName());
        workSpaceHolidays.setText(workSpace.getHoliday());
        workSpaceOpeningTime.setText(workSpace.getStartTime());
        workSpaceClosingTime.setText(workSpace.getClosingTime());
        workSpaceFaceBookUrl.setText(workSpace.getFacebookPageUrl());
        workSpaceEmail.setText(workSpace.getEmail());



    }
}
