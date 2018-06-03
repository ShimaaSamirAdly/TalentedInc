package inc.talentedinc.view.fragmnts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import inc.talentedinc.R;
import inc.talentedinc.view.activities.HomeActivity;

public class NotificationFragment extends Fragment {

    /****************************** asmaa *************************/

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        initView(view);
        return view;
    }
//        ***************************** Asmaa ***************************************

    void initView(View v){
        ((HomeActivity)getActivity()).whichFragment(HomeActivity.NOTIFICATION);

        ((HomeActivity)getActivity()).fab.setVisibility(View.GONE);
    }
    /******************************  *************************/

}
