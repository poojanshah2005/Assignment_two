package com.poojanshah.assignment_two.model;

/**
 * Created by shahp on 14/07/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Music implements Parcelable
{

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    public final static Parcelable.Creator<Music> CREATOR = new Creator<Music>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Music createFromParcel(Parcel in) {
            Music instance = new Music();
            instance.resultCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, (com.poojanshah.assignment_two.model.Result.class.getClassLoader()));
            return instance;
        }

        public Music[] newArray(int size) {
            return (new Music[size]);
        }

    }
            ;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(resultCount);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}