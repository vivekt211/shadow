package in.coderszone.shadow.job.Contact;

import com.path.android.jobqueue.Params;
import com.path.android.jobqueue.RetryConstraint;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import in.coderszone.shadow.api.ContactApiService;
import in.coderszone.shadow.beans.ContactApiResponse;
import in.coderszone.shadow.dao.ContactModel;
import in.coderszone.shadow.di.AppComponent;
import in.coderszone.shadow.events.contact.ContactSyncedEvent;
import in.coderszone.shadow.events.contact.NewContactEvent;
import in.coderszone.shadow.job.BaseJob;
import in.coderszone.shadow.job.NetworkException;
import in.coderszone.shadow.util.L;
import in.coderszone.shadow.vo.Contact;
import retrofit.Response;

/**
 * Created by Vivek on 10/29/2017.
 */

public class SaveContactJob extends BaseJob {

    private static final String GROUP = "Contacts";

    private final Contact contact;

    @Inject
    transient ContactApiService contactApiService;

    @Inject
    transient EventBus mEventBus;

    @Inject
    transient ContactModel contactModel;

    public SaveContactJob(Contact contact, String uuid, String userId) {
        super(new Params(BACKGROUND).groupBy(GROUP).requireNetwork().persist());
        this.contact = contact;
        this.contact.setId(uuid);
    }

    @Override
    public void inject(AppComponent appComponent) {
        super.inject(appComponent);
        appComponent.inject(this);
    }

    @Override
    public void onAdded() {

        contact.setmPending(true);
        // make sure whatever time we put here is greater / eq to last known time in database.
        // this will work around issues related to client's time.
        // this time is temporary anyways as it will be overriden when it is synched to server
        long feedTs = contactModel.getLatestTimestamp(null);
        long now = System.currentTimeMillis();
        contact.setCreated(Math.max(feedTs, now) + 1);
        L.d("assigned timestamp %s to the post", contact.getCreated());
        contactModel.save(contact);
        mEventBus.post(new NewContactEvent(contact));
    }

    @Override
    public void onRun() throws Throwable {
        Contact dbContact = contactModel.loadByContactName(contact.getName());
        if (dbContact != null && ! dbContact.ismPending()) {
            // looks like post probably arrived from somewhere else. Good Job!
           // mEventBus.post(new UpdatedPostEvent(post)); //Incase of contacts no use case
            return;
        }
        Response<ContactApiResponse> response = contactApiService.postContact(contact)
                .execute();
        if (response.isSuccess()) {
            ContactApiResponse body = response.body();
            body.getContact().setmPending(false);
            contactModel.save(body.getContact());
            //mUserModel.save(body.getUser());
            mEventBus.post(new NewContactEvent(body.getContact()));
        } else {
            throw new NetworkException(response.code());
        }
    }

    @Override
    protected int getRetryLimit() {

        return 2;
        //mDemoConfig.getNewPostRetryCount();
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        if (shouldRetry(throwable)) {
            // For the purposes of the demo, just back off 250 ms.
            RetryConstraint constraint = RetryConstraint.createExponentialBackoff(runCount, 250);
            constraint.setApplyNewDelayToGroup(true);
            return constraint;
        }
        return RetryConstraint.CANCEL;
    }

    @Override
    protected void onCancel() {
        Contact post = contactModel.loadByContactName(contact.getName());
        if (post != null) {
            contactModel.delete(post);
        }
        mEventBus.post(new ContactSyncedEvent(contact));
    }
}
