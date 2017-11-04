package in.coderszone.shadow.events.contact;

import in.coderszone.shadow.vo.Contact;

/**
 * Created by Vivek on 10/29/2017.
 */

public class NewContactEvent {
    private Contact contact;

    public NewContactEvent(Contact contact) {
        contact = contact;
    }

    public Contact getContact() {
        return contact;
    }
}
