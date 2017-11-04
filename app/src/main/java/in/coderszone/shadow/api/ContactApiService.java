package in.coderszone.shadow.api;


import java.util.List;

import in.coderszone.shadow.beans.ContactApiResponse;
import in.coderszone.shadow.vo.Contact;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Vivek on 10/29/2017.
 */

public interface ContactApiService {

    @GET("/contact/list")
    Call<List<Contact>> getContactsList();

    @POST("/contact/save")
    Call<ContactApiResponse> postContact(@Body Contact contact);

   /*
    @Inject
    EventBus eventBus;

    public ContactApiService(App app) {
        app.getAppComponent().inject(this);
    }

    public void saveUpdateContacts(List<Contact> contactList){
        JSONObject jsonBody = null;
        try {
            jsonBody = new JSONObject(new Gson().toJson(contactList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue = volleyWebService.getRequestQueue();
        JsonObjectRequest<ContactApiResponse> myReq = new JsonObjectRequest<ContactApiResponse>(Request.Method.POST,
                "http://validate.jsontest.com/?json={'key':'value'}",
                ContactApiResponse.class,
                reqSuccessListener(),
                reqErrorListener(),jsonBody);

        queue.add(myReq);

        //
    }

    private Response.Listener<ContactApiResponse> reqSuccessListener() {
        return new Response.Listener<ContactApiResponse>() {
            @Override
            public void onResponse(ContactApiResponse response) {
                eventBus.post(response);
            }
        };
    }

    private Response.Listener<ContactApiResponse> reqErrorListener() {
        return new Response.Listener<ContactApiResponse>() {
            @Override
            public void onResponse(ContactApiResponse response) {
                eventBus.post(response);
            }
        };
    }*/

}
