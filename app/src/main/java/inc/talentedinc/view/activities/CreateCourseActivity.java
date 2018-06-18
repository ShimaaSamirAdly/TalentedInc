package inc.talentedinc.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.adapter.CreateCourseViewPagerAdapter;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.Course;
import inc.talentedinc.model.Instructor;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.CreateCoursePresenter;
import inc.talentedinc.singleton.SharedPrefrencesSingleton;
import inc.talentedinc.view.callbackinterfaces.SetDateTextView;
import inc.talentedinc.view.fragmnts.DatePickerFragment;

public class CreateCourseActivity extends AppCompatActivity implements CreateCoursePresenter.CreateCourseView {

    //-----------------------------------------------------------------------------------------//

    private Button nextBtn ;
    private Course createdCourse = new Course() ;
    private ViewPager viewPager ;
    private CreateCourseViewPagerAdapter createCourseViewPagerAdapter ;

    //-----------------------------------------------------------------------------------------//


    private Button create ;

    private CreateCoursePresenter createCoursePresenter ;


    //----------------------------------Alaa------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

    //------------------------------------Alaa--------------------------------------------------//

        nextBtn = findViewById(R.id.nextBtn);
        viewPager = findViewById(R.id.create_course_view_pager);
        createCourseViewPagerAdapter = new CreateCourseViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(createCourseViewPagerAdapter);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAction();
            }
        });



        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCourse();
            }
        });

        createCoursePresenter = new CreateCoursePresenter(this);

     //-------------------------------------------------------------------------------------------//
    }






    private void createCourse(){
        getSecondFragmentData();

        User userAsInstructor = SharedPrefrencesSingleton.getSharedPrefUser(this);

        Instructor instructor = userAsInstructor.getInstructor() ;
        createdCourse.setInstructor(instructor);
        createCoursePresenter.courseCreated(createdCourse);

        Intent sendToHome = new Intent(this, HomeActivity.class);
        startActivity(sendToHome);

    }
    //--------------------------------------------------------------------------------------------------------------//

    @Override
    public void successToCreateCourse(int courseId) {

        Toast.makeText(this,"done",Toast.LENGTH_LONG).show();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("courseId",courseId);
        editor.commit();

    }

    //--------------------------------------------------------------------------------------------------------------//

    @Override
    public void failToCreateCourse() {

        Toast.makeText(this,"Fail",Toast.LENGTH_LONG).show();

    }


    //--------------------------------------------------------------------------------------------------------------//

    public void nextAction (){

        switch (viewPager.getCurrentItem()){
            case 0 :
                getFirstFragmentData();
                if(!createdCourse.getCourseName().equals("userInvalidInputData")) {
                    if(!createdCourse.getStartDate().equals("userEnterInvalidDate")) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        create.setVisibility(View.VISIBLE);
                        nextBtn.setVisibility(View.GONE);
                    }
                    else{
                        Toast.makeText(this,"Please choose a valid Start Date and End Date",Toast.LENGTH_LONG).show();

                    }
                }
                else{
                    Toast.makeText(this,"please enter a valid data",Toast.LENGTH_LONG).show();

                }
                break;

        }

    }
    //--------------------------------------------------------------------------------------------------------------//

    public void getFirstFragmentData(){
        Course firstCourse = createCourseViewPagerAdapter.getFirstCreateCourse().getCourse();
        createdCourse.setCourseName(firstCourse.getCourseName());
        createdCourse.setStartDate(firstCourse.getStartDate());
        createdCourse.setEndDate(firstCourse.getEndDate());
        createdCourse.setCost(firstCourse.getCost());
    }
    public void getSecondFragmentData(){
        Course secondCourse = createCourseViewPagerAdapter.getSecondCreateCourse().getCourse();
        createdCourse.setNumOfApplicants(secondCourse.getNumOfApplicants());
        createdCourse.setDescription(secondCourse.getDescription());
        createdCourse.setCategory(secondCourse.getCategory());
        createdCourse.setDuration(secondCourse.getDuration());


    }
    //--------------------------------------------------------------------------------------------------------------//

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            create.setVisibility(View.GONE);
            nextBtn.setVisibility(View.VISIBLE);
        }
    }

}
