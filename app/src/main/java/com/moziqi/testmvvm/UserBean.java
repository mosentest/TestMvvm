package com.moziqi.testmvvm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by moziqi on 16-4-30.
 */
public class UserBean implements Parcelable{

    private int id;
    private String name;


    public UserBean() {
    }

    public UserBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected UserBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}
