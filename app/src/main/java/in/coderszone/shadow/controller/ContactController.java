package in.coderszone.shadow.controller;

import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.path.android.jobqueue.JobManager;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

import javax.inject.Inject;

import dagger.Lazy;
import in.coderszone.shadow.di.AppComponent;
import in.coderszone.shadow.events.contact.ContactSyncedEvent;
import in.coderszone.shadow.job.Contact.SaveContactJob;
import in.coderszone.shadow.vo.Contact;

/**
 * Created by Vivek on 10/29/2017.
 */

public class ContactController {
    @Inject
    JobManager mJobManager;
    @Inject
    EventBus mEventBus;
    @Inject
    Context mAppContext;
    @Inject
    Lazy<NotificationManagerCompat> mNotificationManagerCompat;

    public ContactController(AppComponent appComponent) {
        appComponent.inject(this);
        mEventBus.register(this);
    }

    public void onEventMainThread(ContactSyncedEvent event) {
       /* if (event.didNotifyUser() || !event.isSyncFailure()) {
            return;
        }
        Intent intent = FeedActivity.intentForSendPost(mAppContext, event.getText());
        PendingIntent pendingIntent = PendingIntent.getActivity(mAppContext,
                0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mAppContext)
                .setSmallIcon(R.drawable.ic_action_backup)
                .setContentTitle(mAppContext.getString(R.string.cannot_sync_post, event.getText()))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        mNotificationManagerCompat.get().notify(1, builder.build());
*/
        Toast.makeText(mAppContext,"ContactSyncedEvent recieved "+event.toString(),Toast.LENGTH_LONG).show();
    }

    public void sendPostAsync(Contact contact) {
        mJobManager.addJobInBackground(new SaveContactJob(contact,UUID.randomUUID().toString(),"TestUserId"));
    }

}
