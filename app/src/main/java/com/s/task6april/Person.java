package com.s.task6april;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{

    private Subjects humanData;

    public Person(){
    }


    protected Person(Parcel in) {

        humanData = in.readParcelable(Subjects.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(humanData, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public Subjects getHumanData() {
        return humanData;
    }
    public void setHumanData(Subjects humanData) {
        this.humanData = humanData;
    }
}
