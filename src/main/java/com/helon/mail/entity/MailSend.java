package com.helon.mail.entity;

import com.helon.mail.config.database.BaseMapper;

import java.io.Serializable;
import java.util.Date;

public class MailSend implements Serializable {
    private String sendId;

    /**
     * 投递邮箱
     */
    private String sendTo;

    /**
     * 投递人
     */
    private String sendUserId;

    /**
     * 投递内容
     */
    private String sendContent;

    /**
     * 投递优先级
     */
    private Long sendPriority;

    /**
     * 投递次数
     */
    private Long sendCount;

    /**
     * 投递状态
     */
    private String sendStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId == null ? null : sendId.trim();
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo == null ? null : sendTo.trim();
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId == null ? null : sendUserId.trim();
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent == null ? null : sendContent.trim();
    }

    public Long getSendPriority() {
        return sendPriority;
    }

    public void setSendPriority(Long sendPriority) {
        this.sendPriority = sendPriority;
    }

    public Long getSendCount() {
        return sendCount;
    }

    public void setSendCount(Long sendCount) {
        this.sendCount = sendCount;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sendId=").append(sendId);
        sb.append(", sendTo=").append(sendTo);
        sb.append(", sendUserId=").append(sendUserId);
        sb.append(", sendContent=").append(sendContent);
        sb.append(", sendPriority=").append(sendPriority);
        sb.append(", sendCount=").append(sendCount);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append(", remark=").append(remark);
        sb.append(", version=").append(version);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}