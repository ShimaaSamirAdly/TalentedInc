package inc.talentedinc.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import inc.talentedinc.view.callbackinterfaces.SignupActivityHandler;
import inc.talentedinc.view.fragmnts.FirstSignUpFragment;
import inc.talentedinc.view.fragmnts.SecondSignUpFragment;
import inc.talentedinc.view.fragmnts.ThirdSignUpFragment;

public class SignUPViewPagerAdapter extends FragmentStatePagerAdapter {


//    ............................. mina .............................
    private FirstSignUpFragment firstSignUpFragment;
    private SecondSignUpFragment secondSignUpFragment;
    private ThirdSignUpFragment thirdSignUpFragment;
    private FragmentManager fragmentManager;
//    ...................................................................

    public SignUPViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {

//        .................................................mina .............................
        switch (position) {
            case 0:
                if (firstSignUpFragment == null) {
                    firstSignUpFragment = new FirstSignUpFragment();
                }
                return firstSignUpFragment;

            case 1:
                if (secondSignUpFragment == null) {
                    secondSignUpFragment = new SecondSignUpFragment();
                    secondSignUpFragment.setSupportFragmentManager(fragmentManager);
                }
                return secondSignUpFragment;

//          ..................................................................................

            case 2:
                if (thirdSignUpFragment == null) {
                    thirdSignUpFragment = new ThirdSignUpFragment();
                }
                return thirdSignUpFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

//    .................................... mina ...............................................
    public FirstSignUpFragment getFirstSignUpFragment() {
        return firstSignUpFragment;
    }

    public SecondSignUpFragment getSecondSignUpFragment() {
        return secondSignUpFragment;
    }
//    ............................................................................................

    public ThirdSignUpFragment getThirdSignUpFragment() {
        return thirdSignUpFragment;
    }
}
