package inc.talentedinc.view.fragmnts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import inc.talentedinc.R;
import inc.talentedinc.adapter.SignUpInterestsAdapter;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.signup.SignUpPresenter;
import inc.talentedinc.presenter.signup.SignUpPresenterImpl;


public class ThirdSignUpFragment extends Fragment {

    SignUpPresenter presenter;
    SignUpInterestsAdapter adapter;
    GridView gridview;
    User user;
    Collection<Categories> interests;
    Set<Integer> clickedCount;
    View view;

    public ThirdSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        user = new User();
        view =  inflater.inflate(R.layout.fragment_third_sign_up, container, false);

        presenter = new SignUpPresenterImpl(this, getContext());

        presenter.getAllCategories();

        interests = new ArrayList<Categories>();

        clickedCount = new HashSet<>();

        List<Categories> categoriesList = new ArrayList<>();

        adapter = new SignUpInterestsAdapter(getActivity(), categoriesList);
        gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(adapter);
        Log.i("setView", adapter.toString());

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                TextView t = v.findViewById(R.id.txt);
                String d = t.getText().toString();
                Log.i("text", ""+v.getId());
                if(clickedCount.contains(v.getId())){
                    t.setTextColor(Color.BLACK);
                    interests.remove(new Categories(v.getId(), d));
                    clickedCount.remove(v.getId());
                }else{
                    clickedCount.add(v.getId());
                    interests.add(new Categories(v.getId(), d));
                    t.setTextColor(Color.BLUE);
                }

            }
        });



        return view;
    }


    public void setData(List<Categories> categoriesList){
        Toast.makeText(getContext(), "setData", Toast.LENGTH_LONG).show();
        adapter = new SignUpInterestsAdapter(getContext(), categoriesList);
        gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                TextView t = v.findViewById(R.id.txt);
                String d = t.getText().toString();
                Log.i("text", ""+v.getId());
                if(clickedCount.contains(v.getId())){
                    t.setTextColor(Color.BLACK);
                    interests.remove(new Categories(v.getId(), "programming"));
                    clickedCount.remove(v.getId());
                }else{
                    clickedCount.add(v.getId());
                    interests.add(new Categories(v.getId(), "programming"));
                    t.setTextColor(Color.BLUE);
                }

            }
        });

    }



    public User getUser(){

        user.setCategoryCollection(interests);
        user.setUserType(0);

        return user;
    }

//    public void goToProfile(){

//        Intent intent = new Intent(getContext(), MainActivity.class);
////                intent.putExtra("user", user);
//        startActivity(intent);
//    }

}
