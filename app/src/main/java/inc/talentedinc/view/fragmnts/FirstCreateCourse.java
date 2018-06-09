package inc.talentedinc.view.fragmnts;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import inc.talentedinc.R;
import inc.talentedinc.model.Course;
import inc.talentedinc.model.User;
import inc.talentedinc.view.callbackinterfaces.SetDateTextView;


public class FirstCreateCourse extends Fragment implements SetDateTextView {

    // --------------------------Alaa-----------------------------------------//
    private EditText courseName ;
    private EditText startDate ;
    private EditText endDate ;
    private EditText cost ;
    private Button pickStart ;
    private Button pickEnd ;
    private Course course = new Course() ;
    private boolean flag = true ;
    private FragmentManager supportFragmentManager;

    // --------------------------Alaa ---------------------------------------//

    public FirstCreateCourse(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View firstCreation = inflater.inflate(R.layout.fragment_first_create_course, container, false);

        final DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setDateSetter(this);
        courseName = firstCreation.findViewById(R.id.courseName);
        startDate = firstCreation.findViewById(R.id.startDate);
        endDate = firstCreation.findViewById(R.id.endDate);
        cost = firstCreation.findViewById(R.id.cost);
        pickStart  = firstCreation.findViewById(R.id.pickStart);
        pickEnd = firstCreation.findViewById(R.id.pickEnd);

        pickStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(supportFragmentManager , "DateStart");
                flag = true;
            }
        });

        pickEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(supportFragmentManager,"DateEnded");
                flag = false ;
            }
        });

        return firstCreation;
    }


    @Override
    public void setDateTextView(String date,int year) {
        if (flag == true){
            startDate.setText(date);
            course.setStartDate(date);

        }
        else {
            endDate.setText(date);
            course.setEndDate(date);

        }

    }

    public void setSupportFragmentManager(FragmentManager supportFragmentManager) {
        this.supportFragmentManager = supportFragmentManager;
    }

    public Course getCourse() {
        if(courseName.getText()!=null && startDate.getText()!=null && endDate.getText()!=null && cost.getText()!=null ) {
            course.setCourseName(courseName.getText().toString());
            course.setStartDate(startDate.getText().toString());
            course.setEndDate(endDate.getText().toString());
            course.setCost(Integer.parseInt(cost.getText().toString()));
        }
        else {
            Toast.makeText(getContext(),"please enter a valid data",Toast.LENGTH_LONG).show();
        }
        return course;
    }
}
