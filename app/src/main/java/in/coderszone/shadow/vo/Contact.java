package in.coderszone.shadow.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.apache.commons.lang3.StringUtils;

import in.coderszone.shadow.dao.AppDatabase;
import in.coderszone.shadow.util.DateUtil;
import in.coderszone.shadow.util.Validation;
import in.coderszone.shadow.util.ValidationFailedException;

/**
 * Created by Vivek on 10/29/2017.
 */

@Table(databaseName = AppDatabase.NAME)
public class Contact extends BaseModel implements Validation {
    @Column
    @PrimaryKey
    @JsonProperty("id")
    String id;

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", created=" + created +
                ", mPending=" + mPending +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Column
    @JsonProperty("name")
    String name;

    @Column
    @JsonProperty("phone_number")
    String phoneNumber;

    @Column
    @JsonIgnore
    long created;

    @Column
    @JsonIgnore
    boolean mPending;

    @Column
    @JsonProperty("user_id")
    String userId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @JsonIgnore
    public long getCreated() {
        return created;
    }

    @JsonIgnore
    public void setCreated(long created) {
        this.created = created;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean ismPending() {
        return mPending;
    }

    public void setmPending(boolean mPending) {
        this.mPending = mPending;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("created_at")
    public void setDate(String date) {
        created = DateUtil.parseDate(date);

    }

    public String compositeUniqueKey() {
        return userId;
    }

    public void validate() {

        if (created < 1) {
            throw new ValidationFailedException("invalid created date");
        }
        if (StringUtils.isEmpty(name)) {
            throw new ValidationFailedException("invalid Contact Name");
        }
        if (StringUtils.isEmpty(userId)) {
            throw new ValidationFailedException("invalid User id");
        }
    }
}
