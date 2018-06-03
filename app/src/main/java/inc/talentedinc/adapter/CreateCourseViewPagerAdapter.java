package inc.talentedinc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import inc.talentedinc.view.fragmnts.FirstCreateCourse;
import inc.talentedinc.view.fragmnts.FirstSignUpFragment;
import inc.talentedinc.view.fragmnts.SecondCreateCourse;
import inc.talentedinc.view.fragmnts.SecondSignUpFragment;
import inc.talentedinc.view.fragmnts.ThirdSignUpFragment;

/**
 * Created by Alaa on 6/1/2018.
 */

public class CreateCourseViewPagerAdapter extends FragmentStatePagerAdapter {


    //--------------------------------Alaa----------------------------//
    FirstCreateCourse firstCreateCourse ;
    SecondCreateCourse secondCreateCourse ;
    FragmentManager fragmentManager ;

    //--------------------------------------------------------------//

    public CreateCourseViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
    }



    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (firstCreateCourse == null) {
                    firstCreateCourse = new FirstCreateCourse();
                }
                firstCreateCourse.setSupportFragmentManager(fragmentManager);
                return firstCreateCourse;

            case 1:
                if (secondCreateCourse == null) {
                    secondCreateCourse = new SecondCreateCourse();
                }
                return secondCreateCourse;

        }



        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public FirstCreateCourse getFirstCreateCourse() {
        return firstCreateCourse;
    }

    public SecondCreateCourse getSecondCreateCourse() {
        return secondCreateCourse;
    }
}
