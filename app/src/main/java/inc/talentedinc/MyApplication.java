package inc.talentedinc;

import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by asmaa on 05/31/2018.
 */

public class MyApplication  {
//    @Override
    protected void attachBaseContext(Context base) {
      ///  super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
