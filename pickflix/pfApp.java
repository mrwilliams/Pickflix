package mobi.kewi.pickflix;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Kevin on 19/06/17.
 */

public class pfApp extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
}