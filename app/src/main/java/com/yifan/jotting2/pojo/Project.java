package com.yifan.jotting2.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * 项目数据类
 *
 * Created by yifan on 2016/7/19.
 */
@Entity
public class Project implements Parcelable {

    @Id
    private Long id;

    /**
     * 项目类型
     */
    private int projectType;

    /**
     * 项目名字
     */
    private String projectName;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 已花费总金额
     */
    private double totalMoney;

    /**
     * 开始时间
     */
    private long startTime;

    /**
     * 最后的修改时间/结束时间
     */
    private long modifyTime;

    /**
     * 是否已结束
     */
    private boolean isEnded;

    public Project() {
    }

    protected Project(Parcel in) {
        id = in.readLong();
        projectType = in.readInt();
        projectName = in.readString();
        description = in.readString();
        totalMoney = in.readDouble();
        startTime = in.readLong();
        modifyTime = in.readLong();
        isEnded = in.readByte() != 0;
    }

    @Generated(hash = 849533932)
    public Project(Long id, int projectType, String projectName,
            String description, double totalMoney, long startTime, long modifyTime,
            boolean isEnded) {
        this.id = id;
        this.projectType = projectType;
        this.projectName = projectName;
        this.description = description;
        this.totalMoney = totalMoney;
        this.startTime = startTime;
        this.modifyTime = modifyTime;
        this.isEnded = isEnded;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(projectType);
        dest.writeString(projectName);
        dest.writeString(description);
        dest.writeDouble(totalMoney);
        dest.writeLong(startTime);
        dest.writeLong(modifyTime);
        dest.writeByte((byte) (isEnded ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Transient
    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    public double getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringBuilder("Project: [\n")
                .append("id:").append(id).append(",\n")
                .append("projectName:").append(projectName).append(",\n")
                .append("description:").append(description).append(",\n")
                .append("totalMoney:").append(totalMoney).append(",\n")
                .append("startTime:").append(startTime).append(",\n")
                .append("modifyTime:").append(modifyTime).append(",\n")
                .append("isEnded:").append(isEnded).append("\n]")
                .toString();
    }

    public boolean getIsEnded() {
        return this.isEnded;
    }

    public void setIsEnded(boolean isEnded) {
        this.isEnded = isEnded;
    }

    public long getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getProjectType() {
        return this.projectType;
    }

    public void setProjectType(int projectType) {
        this.projectType = projectType;
    }
}
