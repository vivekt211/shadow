package in.coderszone.shadow.events.contact;

import in.coderszone.shadow.vo.Contact;

/**
 * Created by Vivek on 10/29/2017.
 */

public class ContactSyncedEvent {
    private Contact contact;

    public ContactSyncedEvent(Contact contact) {
        contact = contact;
    }

    @Override
    public String toString() {
        return "ContactSyncedEvent{" +
                "contact=" + contact +
                '}';
    }

    public Contact getContact() {
        return contact;
    }
}
