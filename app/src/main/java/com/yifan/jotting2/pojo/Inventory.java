package com.yifan.jotting2.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 清单数据类
 *
 * Created by yifan on 2016/9/21.
 */
@Entity
public class Inventory implements Parcelable {

    /**
     * 数据条目ID
     */
    @Id
    private Long id;

    /**
     * 条目名称
     */
    private String name;

    /**
     * 描述说明
     */
    private String description;

    /**
     * 时间
     */
    private long date;

    /**
     * 金额
     */
    private double money;

    /**
     * 数量
     */
    private int count;

    /**
     * 是否已勾选
     */
    private boolean isChecked;

    /**
     * 标签颜色
     */
    private int labelColor;

    public Inventory() {
    }

    protected Inventory(Parcel in) {
        name = in.readString();
        description = in.readString();
        date = in.readLong();
        money = in.readDouble();
        count = in.readInt();
        isChecked = in.readByte() != 0;
        labelColor = in.readInt();
    }

    @Generated(hash = 19924482)
    public Inventory(Long id, String name, String description, long date,
            double money, int count, boolean isChecked, int labelColor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.money = money;
        this.count = count;
        this.isChecked = isChecked;
        this.labelColor = labelColor;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeLong(date);
        dest.writeDouble(money);
        dest.writeInt(count);
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeInt(labelColor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Transient
    public static final Creator<Inventory> CREATOR = new Creator<Inventory>() {
        @Override
        public Inventory createFromParcel(Parcel in) {
            return new Inventory(in);
        }

        @Override
        public Inventory[] newArray(int size) {
            return new Inventory[size];
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
