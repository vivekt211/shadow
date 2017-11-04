package in.coderszone.shadow;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.raizlabs.android.dbflow.config.FlowManager;

import in.coderszone.shadow.api.ApiModule;
import in.coderszone.shadow.di.AppComponent;
import in.coderszone.shadow.di.ApplicationModule;
import in.coderszone.shadow.di.DaggerAppComponent;

/**
 * Created by Vivek on 10/29/2017.
 */

public class App extends Application {
    private static AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }
}