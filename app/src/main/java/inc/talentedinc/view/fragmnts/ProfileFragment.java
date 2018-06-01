package inc.talentedinc.view.fragmnts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import inc.talentedinc.R;
import inc.talentedinc.view.activities.HomeActivity;

public class ProfileFragment extends Fragment {

    /****************************** asmaa *************************/

    public ProfileFragment() {
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
        View view =inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        return view;
    }
    void initView(View v){
        ((HomeActivity)getActivity()).fab.setVisibility(View.GONE);
    }
    /******************************  *************************/



}
