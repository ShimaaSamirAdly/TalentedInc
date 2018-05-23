package inc.talentedinc.view.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.view.fragmnts.HistoryFragment;
import inc.talentedinc.view.fragmnts.NotificationFragment;
import inc.talentedinc.view.fragmnts.OfferedCoursesFragment;
import inc.talentedinc.view.fragmnts.ProfileFragment;
import inc.talentedinc.view.fragmnts.UpComingCoursesFragment;

public class HomeActivity extends AppCompatActivity {

    /****************************** asmaa *************************/
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new UpComingCoursesFragment() ,"upComing")
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_history:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new HistoryFragment(),"history").addToBackStack(null).commit();
                    return true;
                case R.id.navigation_offered:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new OfferedCoursesFragment(),"offered").addToBackStack(null).commit();
                    return true;
                case R.id.navigation_notifications:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new NotificationFragment(),"notification").addToBackStack(null).commit();
                    return true;
                case R.id.navigation_profile:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new ProfileFragment(),"profile").addToBackStack(null).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new UpComingCoursesFragment() ,"upComing")
                .addToBackStack(null)
                .commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    /******************************  *************************/

}
