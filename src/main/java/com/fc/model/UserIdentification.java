package com.fc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * user_identification
 * @author 
 */
public class UserIdentification implements Serializable {
    private String identify;

    private Long userId;

    private String loginType;

    private String loginInfo;

    private Date bindTime;

    private Date unbindTime;

    private Boolean binded;

    private static final long serialVersionUID = 1L;

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(String loginInfo) {
        this.loginInfo = loginInfo;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Date getUnbindTime() {
        return unbindTime;
    }

    public void setUnbindTime(Date unbindTime) {
        this.unbindTime = unbindTime;
    }

    public Boolean getBinded() {
        return binded;
    }

    public void setBinded(Boolean binded) {
        this.binded = binded;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserIdentification other = (UserIdentification) that;
        return (this.getIdentify() == null ? other.getIdentify() == null : this.getIdentify().equals(other.getIdentify()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLoginType() == null ? other.getLoginType() == null : this.getLoginType().equals(other.getLoginType()))
            && (this.getLoginInfo() == null ? other.getLoginInfo() == null : this.getLoginInfo().equals(other.getLoginInfo()))
            && (this.getBindTime() == null ? other.getBindTime() == null : this.getBindTime().equals(other.getBindTime()))
            && (this.getUnbindTime() == null ? other.getUnbindTime() == null : this.getUnbindTime().equals(other.getUnbindTime()))
            && (this.getBinded() == null ? other.getBinded() == null : this.getBinded().equals(other.getBinded()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdentify() == null) ? 0 : getIdentify().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLoginType() == null) ? 0 : getLoginType().hashCode());
        result = prime * result + ((getLoginInfo() == null) ? 0 : getLoginInfo().hashCode());
        result = prime * result + ((getBindTime() == null) ? 0 : getBindTime().hashCode());
        result = prime * result + ((getUnbindTime() == null) ? 0 : getUnbindTime().hashCode());
        result = prime * result + ((getBinded() == null) ? 0 : getBinded().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", identify=").append(identify);
        sb.append(", userId=").append(userId);
        sb.append(", loginType=").append(loginType);
        sb.append(", loginInfo=").append(loginInfo);
        sb.append(", bindTime=").append(bindTime);
        sb.append(", unbindTime=").append(unbindTime);
        sb.append(", binded=").append(binded);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}