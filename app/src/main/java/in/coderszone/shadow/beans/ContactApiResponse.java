package in.coderszone.shadow.beans;

import in.coderszone.shadow.vo.Contact;

/**
 * Created by Vivek on 10/29/2017.
 */

public class ContactApiResponse {

    String id;
    Integer responseCode;
    String responseMessage;
    Contact contact;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
