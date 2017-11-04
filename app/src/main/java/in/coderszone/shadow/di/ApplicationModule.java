package in.coderszone.shadow.di;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationManagerCompat;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.di.DependencyInjector;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.coderszone.shadow.App;
import in.coderszone.shadow.controller.ContactController;
import in.coderszone.shadow.dao.AppDatabase;
import in.coderszone.shadow.dao.ContactModel;
import in.coderszone.shadow.job.BaseJob;

import static in.coderszone.shadow.util.L.getJobLogger;


/**
 * Created by Vivek on 10/29/2017.
 */

@Module
public class ApplicationModule {
    private final App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public ContactModel contactModel(SQLiteDatabase database) {
        return new ContactModel(mApp, database);
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mApp;
    }

    @Provides
    @Singleton
    public ContactController contactController() {
        return new ContactController(mApp.getAppComponent());
    }

    @Provides
    @Singleton
    public EventBus eventBus() {
        return new EventBus();
    }

    @Provides
    @Singleton
    public SQLiteDatabase database() {
        return FlowManager.getDatabase(AppDatabase.NAME).getWritableDatabase();
    }

    @Provides
    @Singleton
    public JobManager jobManager() {
        Configuration config = new Configuration.Builder(mApp)
                .consumerKeepAlive(45)
                .maxConsumerCount(3)
                .minConsumerCount(1)
                .customLogger(getJobLogger())
                .injector(new DependencyInjector() {
                    @Override
                    public void inject(Job job) {
                        if (job instanceof BaseJob) {
                            ((BaseJob) job).inject(mApp.getAppComponent());
                        }
                    }
                })
                .build();
        return new JobManager(mApp, config);
    }

    @Provides
    @Singleton
    public NotificationManagerCompat notificationCompat() {
        return NotificationManagerCompat.from(mApp);
    }

    /*@Provides
    @Singleton
    public SaveContactJob saveContactJob(){
        return new SaveContactJob();
    }*/

}
