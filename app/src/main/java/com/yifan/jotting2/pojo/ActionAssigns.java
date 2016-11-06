package com.yifan.jotting2.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 活动支出分配记录类
 *
 * Created by yifan on 2016/11/6.
 */
@Entity
public class ActionAssigns implements Parcelable {

    /**
     * 条目ID
     */
    @Id
    private Long id;

    /**
     * 所属活动ID
     */
    private Long actionID;

    /**
     * 同伴ID
     */
    private Long companionID;

    /**
     * 实付金额
     */
    private double payMoney;

    /**
     * 同伴姓名
     */
    private String companionName;

    public ActionAssigns() {
    }

    protected ActionAssigns(Parcel in) {
        id = in.readLong();
        actionID = in.readLong();
        companionID = in.readLong();
        payMoney = in.readDouble();
        companionName = in.readString();
    }

    @Generated(hash = 1612190079)
    public ActionAssigns(Long id, Long actionID, Long companionID, double payMoney,
                         String companionName) {
        this.id = id;
        this.actionID = actionID;
        this.companionID = companionID;
        this.payMoney = payMoney;
        this.companionName = companionName;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(actionID);
        dest.writeLong(companionID);
        dest.writeDouble(payMoney);
        dest.writeString(companionName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Transient
    public static final Creator<ActionAssigns> CREATOR = new Creator<ActionAssigns>() {
        @Override
        public ActionAssigns createFromParcel(Parcel in) {
            return new ActionAssigns(in);
        }

        @Override
        public ActionAssigns[] newArray(int size) {
            return new ActionAssigns[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActionID() {
        return actionID;
    }

    public void setActionID(Long actionID) {
        this.actionID = actionID;
    }

    public Long getCompanionID() {
        return companionID;
    }

    public void setCompanionID(Long companionID) {
        this.companionID = companionID;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getCompanionName() {
        return companionName;
    }

    public void setCompanionName(String companionName) {
        this.companionName = companionName;
    }
}
