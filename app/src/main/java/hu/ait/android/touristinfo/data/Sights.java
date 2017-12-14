package hu.ait.android.touristinfo.data;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by oliviakim on 12/5/17.
 */

public class Sights extends RealmObject implements Parcelable {
//    @PrimaryKey
//    private String sightsID;

    public Sights() {}

    private String name;
    private Double rating;
    private Double distance;
    private boolean done;

    public Sights(String name, Double rating, Double distance, boolean done) {
        this.name = name;
        this.rating = rating;
        this.distance = distance;
        this.done = done;

    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
//    public String getSightsID() {
//        return sightsID;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    protected Sights(Parcel in) {
//        sightsID = in.readString();
        name = in.readString();
        rating = in.readDouble();
//        distance = in.readDouble();
        done = in.readByte() != 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Sights createFromParcel(Parcel in) {
            return new Sights(in);
        }

        @Override
        public Sights[] newArray(int size) {
            return new Sights[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(sightsID);
        parcel.writeString(name);
        parcel.writeDouble(rating);
//        parcel.writeDouble(distance);
        parcel.writeByte((byte) (done ? 1 : 0));
    }
}
