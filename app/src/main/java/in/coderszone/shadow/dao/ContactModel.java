package in.coderszone.shadow.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.runtime.TransactionManager;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import javax.inject.Inject;

import in.coderszone.shadow.App;
import in.coderszone.shadow.util.ValidationUtil;
import in.coderszone.shadow.vo.Contact;
import in.coderszone.shadow.vo.Contact$Table;

/**
 * Created by Vivek on 10/29/2017.
 */

public class ContactModel extends BaseModel {

    @Inject
    Context mAppContext;
    private static final String PREF_NAME = "feed_pref";
    private static final String KEY_LAST_FEED_TIMESTAMP = "timestamp";
    private static final String KEY_LOCAL_POST_ID = "local_post_id";
    private SharedPreferences mPrefs;


    public ContactModel(App app, SQLiteDatabase database) {
        super(app, database);
        app.getAppComponent().inject(this);
    }

    public void saveFeedTimestamp(long timestamp, @Nullable String userId) {
        getPref().edit().putLong(createUserTimestampKey(userId), timestamp).commit();
    }

    public long getLatestTimestamp(@Nullable String userId) {
        return getPref().getLong(createUserTimestampKey(userId), 0);
    }

    public synchronized long generateIdForNewLocalContact() {
        long id = getPref().getLong(KEY_LOCAL_POST_ID, Long.MIN_VALUE);
        getPref().edit().putLong(KEY_LOCAL_POST_ID, id + 1).commit();
        return id;
    }

    private SharedPreferences getPref() {
        if (mPrefs == null) {
            mPrefs = mAppContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
        return mPrefs;
    }

    private static String createUserTimestampKey(@Nullable String userId) {
        if (userId == null) {
            return KEY_LAST_FEED_TIMESTAMP;
        }
        return KEY_LAST_FEED_TIMESTAMP + "_" + userId;
    }

    public void clear() {
        getPref().edit().clear().commit();
    }
    public List<Contact> loadContactsSince(long since) {
        return new Select().from(Contact.class).where(
                Condition.column(Contact$Table.CREATED).greaterThan(since)
        ).queryList();
    }

    /*public List<Post> loadPostsOfUser(long userId, long since) {
        return new Select().from(Post.class).where(
                Condition.column(Post$Table.MCREATED).greaterThan(since)
        ).and(Condition.column(Post$Table.MUSERID).eq(userId)).queryList();
    }*/

    public Contact load(long id) {
        return new Select().from(Contact.class)
                .where(Condition.column(Contact$Table.ID).eq(id)).querySingle();
    }

    public synchronized void save(Contact contact) {
        ValidationUtil.validate(contact);
        saveValid(contact);
    }

    public synchronized void saveAll(final List<Contact> contacts) {
        ValidationUtil.pruneInvalid(contacts);
        if (contacts.isEmpty()) {
            return;
        }
        TransactionManager.transact(mSQLiteDatabase, new Runnable() {
            @Override
            public void run() {
                for (Contact contact : contacts) {
                    saveValid(contact);
                }
            }
        });
    }

    private void saveValid(Contact contact) {
        Contact existing = loadByContactName(contact.getName());
        if (existing == null) {
            contact.save();
        } else {
            contact.setId(existing.getId());
            contact.update();
        }
    }

    @Nullable
    public synchronized Contact loadByContactName(String contactName) {
        if (StringUtils.isEmpty(contactName)) {
            return null;
        }
        return new Select().from(Contact.class)
                .where(Condition.column(Contact$Table.NAME).eq(contactName))
                .querySingle();
    }

    public void delete(Contact contact) {
        contact.delete();
    }
}