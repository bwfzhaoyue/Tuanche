package com.bwf.tuanche.ui.mainpager.entity.marrigecar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zengqiang on 2016/8/19.
 * Description:Tuanche
 */
public class MarrigeCarResult implements Parcelable {
    public String adpTitle;
    public String adpLogo;
    public String shareUrl;
    public String sharePic;
    public String shareCtx;
    public String shareSlogan;
    public String isShare;
    public String cardTotal;
    public String offset;
    public String count;
    public List<CarStyleList> carStyleList;

    @Override
    public String toString() {
        return "MarrigeCarResult{" +
                "adpLogo='" + adpLogo + '\'' +
                ", adpTitle='" + adpTitle + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", sharePic='" + sharePic + '\'' +
                ", shareCtx='" + shareCtx + '\'' +
                ", shareSlogan='" + shareSlogan + '\'' +
                ", isShare='" + isShare + '\'' +
                ", cardTotal='" + cardTotal + '\'' +
                ", offset='" + offset + '\'' +
                ", count='" + count + '\'' +
                ", carStyleList=" + carStyleList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.adpTitle);
        dest.writeString(this.adpLogo);
        dest.writeString(this.shareUrl);
        dest.writeString(this.sharePic);
        dest.writeString(this.shareCtx);
        dest.writeString(this.shareSlogan);
        dest.writeString(this.isShare);
        dest.writeString(this.cardTotal);
        dest.writeString(this.offset);
        dest.writeString(this.count);
        dest.writeTypedList(this.carStyleList);
    }

    public MarrigeCarResult() {
    }

    protected MarrigeCarResult(Parcel in) {
        this.adpTitle = in.readString();
        this.adpLogo = in.readString();
        this.shareUrl = in.readString();
        this.sharePic = in.readString();
        this.shareCtx = in.readString();
        this.shareSlogan = in.readString();
        this.isShare = in.readString();
        this.cardTotal = in.readString();
        this.offset = in.readString();
        this.count = in.readString();
        this.carStyleList = in.createTypedArrayList(CarStyleList.CREATOR);
    }

    public static final Parcelable.Creator<MarrigeCarResult> CREATOR = new Parcelable.Creator<MarrigeCarResult>() {
        @Override
        public MarrigeCarResult createFromParcel(Parcel source) {
            return new MarrigeCarResult(source);
        }

        @Override
        public MarrigeCarResult[] newArray(int size) {
            return new MarrigeCarResult[size];
        }
    };
}
