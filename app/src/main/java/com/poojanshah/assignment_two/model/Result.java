package com.poojanshah.assignment_two.model;

/**
 * Created by shahp on 14/07/2017.
 */
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Result extends RealmObject implements Parcelable
{

    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("artistId")
    @Expose
    private Integer artistId;
    @SerializedName("collectionId")
    @Expose
    private Integer collectionId;
    @PrimaryKey
    @SerializedName("trackId")
    @Expose
    private Integer trackId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("collectionCensoredName")
    @Expose
    private String collectionCensoredName;
    @SerializedName("trackCensoredName")
    @Expose
    private String trackCensoredName;
    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;
    @SerializedName("collectionViewUrl")
    @Expose
    private String collectionViewUrl;
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;
    @SerializedName("artworkUrl30")
    @Expose
    private String artworkUrl30;
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("collectionPrice")
    @Expose
    private Double collectionPrice;
    @SerializedName("trackPrice")
    @Expose
    private Double trackPrice;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("collectionExplicitness")
    @Expose
    private String collectionExplicitness;
    @SerializedName("trackExplicitness")
    @Expose
    private String trackExplicitness;
    @SerializedName("discCount")
    @Expose
    private Integer discCount;
    @SerializedName("discNumber")
    @Expose
    private Integer discNumber;
    @SerializedName("trackCount")
    @Expose
    private Integer trackCount;
    @SerializedName("trackNumber")
    @Expose
    private Integer trackNumber;
    @SerializedName("trackTimeMillis")
    @Expose
    private Integer trackTimeMillis;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("isStreamable")
    @Expose
    private Boolean isStreamable;
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            Result instance = new Result();
            instance.wrapperType = ((String) in.readValue((String.class.getClassLoader())));
            instance.kind = ((String) in.readValue((String.class.getClassLoader())));
            instance.artistId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.collectionId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.trackId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.artistName = ((String) in.readValue((String.class.getClassLoader())));
            instance.collectionName = ((String) in.readValue((String.class.getClassLoader())));
            instance.trackName = ((String) in.readValue((String.class.getClassLoader())));
            instance.collectionCensoredName = ((String) in.readValue((String.class.getClassLoader())));
            instance.trackCensoredName = ((String) in.readValue((String.class.getClassLoader())));
            instance.artistViewUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.collectionViewUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.trackViewUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.previewUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.artworkUrl30 = ((String) in.readValue((String.class.getClassLoader())));
            instance.artworkUrl60 = ((String) in.readValue((String.class.getClassLoader())));
            instance.artworkUrl100 = ((String) in.readValue((String.class.getClassLoader())));
            instance.collectionPrice = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.trackPrice = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.collectionExplicitness = ((String) in.readValue((String.class.getClassLoader())));
            instance.trackExplicitness = ((String) in.readValue((String.class.getClassLoader())));
            instance.discCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.discNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.trackCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.trackNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.trackTimeMillis = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.country = ((String) in.readValue((String.class.getClassLoader())));
            instance.currency = ((String) in.readValue((String.class.getClassLoader())));
            instance.primaryGenreName = ((String) in.readValue((String.class.getClassLoader())));
            instance.isStreamable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            return instance;
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
            ;

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public Double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public Double getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(Double trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    public Integer getDiscCount() {
        return discCount;
    }

    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }

    public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public Boolean getIsStreamable() {
        return isStreamable;
    }

    public void setIsStreamable(Boolean isStreamable) {
        this.isStreamable = isStreamable;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(wrapperType);
        dest.writeValue(kind);
        dest.writeValue(artistId);
        dest.writeValue(collectionId);
        dest.writeValue(trackId);
        dest.writeValue(artistName);
        dest.writeValue(collectionName);
        dest.writeValue(trackName);
        dest.writeValue(collectionCensoredName);
        dest.writeValue(trackCensoredName);
        dest.writeValue(artistViewUrl);
        dest.writeValue(collectionViewUrl);
        dest.writeValue(trackViewUrl);
        dest.writeValue(previewUrl);
        dest.writeValue(artworkUrl30);
        dest.writeValue(artworkUrl60);
        dest.writeValue(artworkUrl100);
        dest.writeValue(collectionPrice);
        dest.writeValue(trackPrice);
        dest.writeValue(releaseDate);
        dest.writeValue(collectionExplicitness);
        dest.writeValue(trackExplicitness);
        dest.writeValue(discCount);
        dest.writeValue(discNumber);
        dest.writeValue(trackCount);
        dest.writeValue(trackNumber);
        dest.writeValue(trackTimeMillis);
        dest.writeValue(country);
        dest.writeValue(currency);
        dest.writeValue(primaryGenreName);
        dest.writeValue(isStreamable);
    }

    public int describeContents() {
        return 0;
    }

}