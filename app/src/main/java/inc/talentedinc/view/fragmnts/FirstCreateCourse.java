package inc.talentedinc.view.fragmnts;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import inc.talentedinc.R;
import inc.talentedinc.model.Course;
import inc.talentedinc.model.User;
import inc.talentedinc.view.callbackinterfaces.SetDateTextView;


public class FirstCreateCourse extends Fragment implements SetDateTextView {

    // --------------------------Alaa-----------------------------------------//
    private EditText courseName ;
    private TextView startDate ;
    private TextView endDate ;
    private EditText cost ;
    private Button pickStart ;
    private Button pickEnd ;
    private Course course = new Course() ;
    private boolean flag = true ;
    private FragmentManager supportFragmentManager;
    private String startDateString ;
    private String endDateString ;

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
        if( !courseName.getText().toString().equals("") && !startDate.getText().toString().equals("") && !endDate.getText().toString().equals("") && !cost.getText().toString().equals("") ) {
            course.setCourseName(courseName.getText().toString());
            Log.i("courseName",course.getCourseName());
            startDateString = startDate.getText().toString();
            endDateString = endDate.getText().toString();
            validateDate(startDateString,endDateString);
            course.setCost(Integer.parseInt(cost.getText().toString()));
        }
        else {
            course.setCourseName("userInvalidInputData");
        }
        return course;
    }


    public void validateDate(String start , String end) {

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        String formattedDate = df.format(c);
            int currentMonth = c.getMonth();

        String[] currentParts = formattedDate.split("/");
        String[] startParts = start.split("-");
        String[] endParts = end.split("-");


        if ( Integer.parseInt(startParts[1]) >= currentMonth && Integer.parseInt(startParts[2]) > Integer.parseInt(currentParts[0])  && Integer.parseInt(startParts[0]) == Integer.parseInt(currentParts[2]) ) {

            if (Integer.parseInt(startParts[1]) == Integer.parseInt(endParts[1])) {
                if (Integer.parseInt(startParts[2]) < Integer.parseInt(endParts[2])) {
                    course.setStartDate(startDate.getText().toString());
                    course.setEndDate(endDate.getText().toString());
                } else {
                    course.setStartDate("userEnterInvalidDate");
                }
            }
        if (Integer.parseInt(startParts[1]) < Integer.parseInt(endParts[1])) {
            course.setStartDate(startDate.getText().toString());
            course.setEndDate(endDate.getText().toString());
        } else {
            course.setStartDate("userEnterInvalidDate");

        }
    }
    else {
            course.setStartDate("userEnterInvalidDate");

        }

    }
}
