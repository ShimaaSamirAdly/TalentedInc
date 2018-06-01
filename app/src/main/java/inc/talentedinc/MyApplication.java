package inc.talentedinc;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by asmaa on 05/31/2018.
 */

public class MyApplication  extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
