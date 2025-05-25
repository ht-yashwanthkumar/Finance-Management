package com.finance.app.notification.service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {
    /**
     * The pk notification id.
     */
    @Column(name = "pk_notification_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pkNotificationId;

    /**
     * The fk user id.
     */
    @Column(name = "fk_user_id")
    private long fkUserId;

    /**
     * The category
     */
    @Column(name = "category")
    private String category;
    /**
     * The budget amount
     */
    @Column(name = "budget_amount")
    private Double budgetAmount;
    /**
     * The expense description
     */
    @Column(name = "expense_description")
    private String expenseDescription;
    /**
     * The expense amount
     */
    @Column(name = "expense_amount")
    private Double expenseAmount;
    /**
     * Is notification sent
     */
    @Column(name = "notification_sent_status")
    private Boolean isNotificationSent;
    /**
     * The expense date
     */
    @Column(name = "notification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;

    public long getPkNotificationId() {
        return pkNotificationId;
    }

    public void setPkNotificationId(long pkNotificationId) {
        this.pkNotificationId = pkNotificationId;
    }

    public long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Boolean getNotificationSent() {
        return isNotificationSent;
    }

    public void setNotificationSent(Boolean notificationSent) {
        isNotificationSent = notificationSent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
