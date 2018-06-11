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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import inc.talentedinc.R;
import inc.talentedinc.adapter.SignUpInterestsAdapter;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.signup.SignUpPresenter;
import inc.talentedinc.presenter.signup.SignUpPresenterImpl;


public class ThirdSignUpFragment extends Fragment {

    private SignUpPresenter presenter;
    private SignUpInterestsAdapter adapter;
    private GridView gridview;
    private User user;
    private Collection<Categories> interests;
    private HashMap<Integer, Categories> clickedCount;
    private View view;
    private ProgressBar loading;

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


        Bundle bundle = getArguments();

        if(bundle != null) {
            user = (User) bundle.getSerializable("user");
        }else {
            user = new User();
        }

        view =  inflater.inflate(R.layout.fragment_third_sign_up, container, false);

        presenter = new SignUpPresenterImpl(this, getContext());

        loading = view.findViewById(R.id.loading);

        presenter.getAllCategories();

        clickedCount = new HashMap<>();

        return view;
    }


    public void setData(List<Categories> categoriesList){

        loading.setVisibility(View.GONE);

        adapter = new SignUpInterestsAdapter(getContext(), categoriesList, user.getCategoryCollection());
        gridview = view.findViewById(R.id.gridview);
        gridview.setAdapter(adapter);

        if(user.getCategoryCollection() != null) {
            Iterator<Categories> iter = user.getCategoryCollection().iterator();
            while (iter.hasNext()) {
                Categories categories = iter.next();
                clickedCount.put(categories.getCategoryId(), categories);
            }

        }
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                TextView categoryText = v.findViewById(R.id.txt);
                ImageView categoryImg = v.findViewById(R.id.img);
                String categoryName = categoryText.getText().toString();
                Categories cat = new Categories(v.getId(), categoryName);

                if(clickedCount.containsKey(v.getId())){
                    HashMap<Integer, String> map = new HashMap<>();

                    categoryImg.setImageResource(getContext().getResources()
                            .getIdentifier(categoryName.toLowerCase(), "drawable", getContext().getPackageName()));
                    clickedCount.remove(v.getId());
                }else{

                    categoryImg.setImageResource(getContext().getResources()
                            .getIdentifier(categoryName.toLowerCase()+"selc", "drawable", getContext().getPackageName()));
                    clickedCount.put(v.getId(), cat);

                }
            }
        });

    }



    public User getUser(){

        interests = new ArrayList<>();
        interests = clickedCount.values();
        user.setCategoryCollection(interests);
        user.setUserType(0);
        user.setFollowersNumber(0);
        user.setFollowingNumber(0);

        return user;
    }

    public User getUpdatedUser(){

        if(interests.size() != 0) {
            interests = new ArrayList<>(clickedCount.values());
            user.setCategoryCollection(interests);
        }

        return user;
    }
}



