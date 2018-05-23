package inc.talentedinc.view.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.view.fragmnts.HistoryFragment;
import inc.talentedinc.view.fragmnts.NotificationFragment;
import inc.talentedinc.view.fragmnts.ProfileFragment;
import inc.talentedinc.view.fragmnts.UpComingCoursesFragment;

public class HomeActivity extends AppCompatActivity {

    /****************************** asmaa *************************/

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }
    /***************************************************************/

    /****************************** asmaa *************************/

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);

    }
    /******************************  *************************/

    /****************************** asmaa *************************/

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new UpComingCoursesFragment(), "upComing");
        adapter.addFrag(new HistoryFragment(), "history");
        adapter.addFrag(new NotificationFragment(), "notification");
        adapter.addFrag(new ProfileFragment(), "profile");
        viewPager.setAdapter(adapter);
    }

    /******************************  *************************/

    /****************************** asmaa *************************/

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
       // private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
           // mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return null to display only the icon
            return null;
        }
    }
    /******************************  *************************/

}
