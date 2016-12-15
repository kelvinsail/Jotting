package com.yifan.jotting2.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 普通项目同伴类，记录项目中的同行人员数据
 *
 * Created by yifan on 2016/11/6.
 */
@Entity
public class Companion implements Parcelable {

    /**
     * 条目ID
     */
    @Id
    private Long id;

    /**
     * 同伴名字
     */
    private String name;

    /**
     * 手机
     */
    private String phone;

    /**
     * 充值总金额
     */
    private double rechangeMoney;

    /**
     * 活动默认支出者
     */
    private boolean isDefaultPayer;

    /**
     * 所属项目ID
     */
    private Long projectID;

    public Companion() {
    }

    protected Companion(Parcel in) {
        id = in.readLong();
        projectID = in.readLong();
        name = in.readString();
        phone = in.readString();
        rechangeMoney = in.readDouble();
        isDefaultPayer = in.readByte() != 0;
    }

    @Generated(hash = 1210702472)
    public Companion(Long id, String name, String phone, double rechangeMoney,
            boolean isDefaultPayer, Long projectID) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.rechangeMoney = rechangeMoney;
        this.isDefaultPayer = isDefaultPayer;
        this.projectID = projectID;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeDouble(rechangeMoney);
        dest.writeByte((byte) (isDefaultPayer ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Transient
    public static final Creator<Companion> CREATOR = new Creator<Companion>() {
        @Override
        public Companion createFromParcel(Parcel in) {
            return new Companion(in);
        }

        @Override
        public Companion[] newArray(int size) {
            return new Companion[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getRechangeMoney() {
        return rechangeMoney;
    }

    public void setRechangeMoney(double rechangeMoney) {
        this.rechangeMoney = rechangeMoney;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public boolean getIsDefaultPayer() {
        return this.isDefaultPayer;
    }

    public void setIsDefaultPayer(boolean isDefaultPayer) {
        this.isDefaultPayer = isDefaultPayer;
    }
}
