package in.coderszone.shadow.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.coderszone.shadow.service.ContactService;

/**
 * Created by Vivek on 10/29/2017.
 */
@Module
public class ContactServiceModule {

    ContactService contactService;

    public ContactServiceModule(ContactService contactService){
        this.contactService =contactService;
    }

    @Provides
    @Singleton
    ContactService provideContactService() {
        return this.contactService;
    }


}
