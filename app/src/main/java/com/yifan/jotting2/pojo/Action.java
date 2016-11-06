package com.yifan.jotting2.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * 普通项目活动类，记录每次活动的数据
 *
 * Created by yifan on 2016/11/6.
 */
@Entity
public class Action implements Parcelable {

    /**
     * 数据条目ID
     */
    @Id
    private Long id;

    /**
     * 所属项目ID
     */
    private Long projectID;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 日期时间
     */
    private long date;

    /**
     * 活动总金额
     */
    private double totalMoney;

    /**
     * 金额支付者ID
     */
    private Long payerID;

    public Action() {
    }

    protected Action(Parcel in) {
        id = in.readLong();
        projectID = in.readLong();
        payerID = in.readLong();
        name = in.readString();
        description = in.readString();
        date = in.readLong();
        totalMoney = in.readDouble();
    }

    @Generated(hash = 345354832)
    public Action(Long id, Long projectID, String name, String description,
            long date, double totalMoney, Long payerID) {
        this.id = id;
        this.projectID = projectID;
        this.name = name;
        this.description = description;
        this.date = date;
        this.totalMoney = totalMoney;
        this.payerID = payerID;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(projectID);
        dest.writeLong(payerID);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeLong(date);
        dest.writeDouble(totalMoney);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Transient
    public static final Creator<Action> CREATOR = new Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getPayerID() {
        return payerID;
    }

    public void setPayerID(Long payerID) {
        this.payerID = payerID;
    }
}
