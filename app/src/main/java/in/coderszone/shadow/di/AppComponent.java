package in.coderszone.shadow.di;

import javax.inject.Singleton;

import dagger.Component;
import in.coderszone.shadow.api.ApiModule;
import in.coderszone.shadow.api.ContactApiService;
import in.coderszone.shadow.controller.ContactController;
import in.coderszone.shadow.dao.ContactModel;
import in.coderszone.shadow.job.Contact.SaveContactJob;
import in.coderszone.shadow.service.ContactService;

/**
 * Created by Vivek on 10/29/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class,ApiModule.class})
public interface AppComponent {

/*
    JobManager jobManager();

    ContactModel contactModel();

    EventBus eventBus();

     ContactController contactController();

    Context appContext();

    ContactApiService contactApiService();

    NotificationManagerCompat notificationManagerCompat();
*/



    void inject(ContactController contactController);

    void inject(ContactModel contactModel);

    void inject(SaveContactJob saveContactJob);

    void inject(ContactApiService contactApiService);

    void inject(ContactService contactService);

}
