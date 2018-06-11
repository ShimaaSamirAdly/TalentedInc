package inc.talentedinc.view.fragmnts;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.Course;
import inc.talentedinc.presenter.CategoriesPresenter;


public class SecondCreateCourse extends Fragment implements AdapterView.OnItemSelectedListener ,CategoriesPresenter.CategoriesView {

    //---------------------------Alaa-----------------------------------------//
    private EditText numOfApplicants ;
    private EditText description ;
    private EditText duration ;
    private Spinner categories;
    private Course course  = new Course();
    private List<Categories> allCategories ;
    private  List<String> receivedCategories = new ArrayList<String>();
    ;
    private CategoriesPresenter categoriesPresenter ;


    //-------------------------------------------------------------------------//

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View secondCreation = inflater.inflate(R.layout.fragment_second_create_course, container, false);
        numOfApplicants = secondCreation.findViewById(R.id.NumOfApplicants);
        description = secondCreation.findViewById(R.id.description);
        duration = secondCreation.findViewById(R.id.duration);

        categories = secondCreation.findViewById(R.id.categories_snippir);
        categories.setOnItemSelectedListener(this);
        categoriesPresenter = new CategoriesPresenter(this);
        categoriesPresenter.getCategories();

           return  secondCreation ;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if(allCategories !=null) {
                    course.setCategory(allCategories.get(position));
                }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        course.setCategory(null);
    }

    public Course getCourse() {
        course.setDuration(Integer.parseInt(duration.getText().toString()));
        course.setDescription(description.getText().toString());
        course.setNumOfApplicants(Integer.parseInt(numOfApplicants.getText().toString()));
        return course;
    }

    @Override
    public void recievedCategories(List<Categories> categories) {
        allCategories = categories ;

//        if (allCategories != null){
//            Log.i("gab",allCategories.get(0).getName());
//
//            for (int i = 0 ; i < allCategories.size() ; i++){
//                receivedCategories.add(allCategories.get(i).getName()) ;
//            }
//            addItemsToSnipper();
//        }
    }

    private void addItemsToSnipper(){
        if(receivedCategories.size()>0) {
            Log.i("helloCategory", receivedCategories.get(1));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>( getContext(),
                    android.R.layout.simple_list_item_1, receivedCategories);
            categories.setAdapter(adapter);
        }

    }

}

