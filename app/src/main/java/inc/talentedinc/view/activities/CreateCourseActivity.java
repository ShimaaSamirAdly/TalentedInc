package inc.talentedinc.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import inc.talentedinc.R;
import inc.talentedinc.model.Course;
import inc.talentedinc.view.callbackinterfaces.SetDateTextView;
import inc.talentedinc.view.fragmnts.DatePickerFragment;

public class CreateCourseActivity extends AppCompatActivity implements SetDateTextView,AdapterView.OnItemSelectedListener {

    private EditText courseName ;
    private EditText startDate ;
    private EditText endDate ;
    private EditText cost ;
    private EditText numOfApplicants ;
    private EditText description ;
    private Spinner categories;
    private Button pickStart ;
    private Button pickEnd ;
    private Button create ;
    private Course course ;

    private boolean flag = true ;


    //----------------------------------Alaa------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        course = new Course() ;
        final DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setDateSetter(this);
        courseName = findViewById(R.id.courseName);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        cost = findViewById(R.id.cost);
        numOfApplicants = findViewById(R.id.NumOfApplicants);
        description = findViewById(R.id.description);
        pickStart  = findViewById(R.id.pickStart);
        pickEnd = findViewById(R.id.pickEnd);
        categories = findViewById(R.id.categories_snippir);
        categories.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapter);

        pickStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(getSupportFragmentManager() , "DateStart");
                flag = true;
            }
        });

        pickEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(getSupportFragmentManager(),"DateEnded");
                flag = false ;
            }
        });

        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCourse();
            }
        });

     //-------------------------------------------------------------------------------------------//
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0 :
                course.setCategory("Arts");
                break;

            case 1 :
                course.setCategory("Music");
                break;

            case 2 :
                course.setCategory("Fashion");
                break;
            case 3 :
                course.setCategory("IT");
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        course.setCategory("");
    }

    @Override
    public void setDateTextView(String date) {
        if (flag == true){
            startDate.setText(date);
            course.setStartDate(date);

        }
        else {
            endDate.setText(date);
            course.setEndDate(date);

        }


    }


    private void createCourse(){
        //set data to course //
        course.setCourseName(courseName.getText().toString());
        course.setStartDate(startDate.getText().toString());
        course.setEndDate(endDate.getText().toString());
        course.setCost(Integer.parseInt( cost.getText().toString()));
        course.setDescription(description.getText().toString());
        course.setNumOfApplicants(Integer.parseInt(numOfApplicants.getText().toString()));

        //hyro7 lel offered courses//

    }
}
