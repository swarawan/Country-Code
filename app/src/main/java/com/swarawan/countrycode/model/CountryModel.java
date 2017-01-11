package com.swarawan.countrycode.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rioswarawan on 1/11/17.
 */

public class CountryModel implements Parcelable {

    public String name;
    @SerializedName("alpha-2")
    public String alpha2;
    @SerializedName("alpha-3")
    public String alpha3;
    @SerializedName("country-code")
    public String countryCode;
    @SerializedName("iso_3166-2")
    public String iso;
    public String region;
    @SerializedName("sub-region")
    public String subRegion;
    @SerializedName("region-code")
    public String regionCode;
    @SerializedName("sub-region-code")
    public String subRegionCode;

    protected CountryModel(Parcel in) {
        this.name = in.readString();
        this.alpha2 = in.readString();
        this.alpha3 = in.readString();
        this.countryCode = in.readString();
        this.iso = in.readString();
        this.region = in.readString();
        this.subRegion = in.readString();
        this.regionCode = in.readString();
        this.subRegionCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.alpha2);
        dest.writeString(this.alpha3);
        dest.writeString(this.countryCode);
        dest.writeString(this.iso);
        dest.writeString(this.region);
        dest.writeString(this.subRegion);
        dest.writeString(this.regionCode);
        dest.writeString(this.subRegionCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CountryModel> CREATOR = new Creator<CountryModel>() {
        @Override
        public CountryModel createFromParcel(Parcel in) {
            return new CountryModel(in);
        }

        @Override
        public CountryModel[] newArray(int size) {
            return new CountryModel[size];
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CountryModel that = (CountryModel) obj;

        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (alpha2 != null ? !alpha2.equals(that.alpha2) : that.alpha2 != null)
            return false;
        if (alpha3 != null ? !alpha3.equals(that.alpha3) : that.alpha3 != null)
            return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null)
            return false;
        if (iso != null ? !iso.equals(that.iso) : that.iso != null)
            return false;
        if (region != null ? !region.equals(that.region) : that.region != null)
            return false;
        if (subRegion != null ? !subRegion.equals(that.subRegion) : that.subRegion != null)
            return false;
        if (regionCode != null ? !regionCode.equals(that.regionCode) : that.regionCode != null)
            return false;
        return !(subRegionCode != null ? !subRegionCode.equals(that.subRegionCode) : that.subRegionCode != null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (alpha2 != null ? alpha2.hashCode() : 0);
        result = 31 * result + (alpha3 != null ? alpha3.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (iso != null ? iso.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (subRegion != null ? subRegion.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (regionCode != null ? regionCode.hashCode() : 0);
        result = 31 * result + (subRegionCode != null ? subRegionCode.hashCode() : 0);
        return result;
    }
}
