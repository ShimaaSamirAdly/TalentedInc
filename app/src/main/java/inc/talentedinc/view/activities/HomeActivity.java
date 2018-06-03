package inc.talentedinc.view.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.view.fragmnts.HistoryFragment;
import inc.talentedinc.view.fragmnts.NotificationFragment;
import inc.talentedinc.view.fragmnts.OfferedCoursesFragment;
import inc.talentedinc.view.fragmnts.ProfileFragment;
import inc.talentedinc.view.fragmnts.UpComingCoursesFragment;

public class HomeActivity extends AppCompatActivity {
    public FloatingActionButton fab;
    private   BottomNavigationView navigation;
    public static final String UPCOMING ="upcoming";
    public static final String HISTORY ="history";
    public static final String OFFERD ="offerd";
    public static final String PROGILE ="PROFILE";
    public static final String NOTIFICATION ="notification";
   /// public static final String UPCOMING ="upcoming";


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
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new UpComingCoursesFragment() ,"upComing")
                .addToBackStack(null)
                .commit();
        navigation.getMenu().getItem(0).setChecked(true);
        fab = findViewById(R.id.fab);

    }

    public void whichFragment(String s){
        switch (s){
            case UPCOMING:
                navigation.getMenu().getItem(0).setChecked(true);
                break;
            case HISTORY:
                navigation.getMenu().getItem(1).setChecked(true);

                break;
            case OFFERD:
                navigation.getMenu().getItem(2).setChecked(true);

                break;
            case NOTIFICATION:
                navigation.getMenu().getItem(3).setChecked(true);

                break;
            case PROGILE:
                navigation.getMenu().getItem(4).setChecked(true);
                break;

        }
    }

    @Override
    public void onBackPressed() {
         if(getSupportFragmentManager().getBackStackEntryCount() == 1){
         ///    navigation.getMenu().getItem(0).setChecked(true);
             finish();
         }else {
             super.onBackPressed();

         }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //   if ()
//        final UpComingCoursesFragment fragment = (UpComingCoursesFragment) getSupportFragmentManager().findFragmentByTag("upComing");
//        if (getFragmentManager().getBackStackEntryCount() < 0) {
//            finish();
//        }
    }

    /******************************  *************************/

}
