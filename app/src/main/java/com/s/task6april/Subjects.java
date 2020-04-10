package com.s.task6april;

import android.os.Parcel;
import android.os.Parcelable;

public class Subjects implements Parcelable {

    public Subjects() {
    }

    private String humanName;
    private String humanAddress;
    private String humanPhone;

    protected Subjects(Parcel in) {

        humanName = in.readString();
        humanAddress = in.readString();
        humanPhone = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(humanName);
        dest.writeString(humanAddress);
        dest.writeString(humanPhone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Subjects> CREATOR = new Creator<Subjects>() {
        @Override
        public Subjects createFromParcel(Parcel in) {
            return new Subjects(in);
        }

        @Override
        public Subjects[] newArray(int size)
        {
            return new Subjects[size];
        }
    };

    public String getHumanName() {
        return humanName;
    }
    public void setHumanName(String humanName) {
        this.humanName = humanName;
    }

    public String getHumanAddress() {
        return humanAddress;
    }
    public void setHumanAddress(String humanAddress) {
        this.humanAddress = humanAddress;
    }

    public String getHumanPhone() {
        return humanPhone;
    }
    public void setHumanPhone(String humanPhone) {
        this.humanPhone = humanPhone;
    }
}
