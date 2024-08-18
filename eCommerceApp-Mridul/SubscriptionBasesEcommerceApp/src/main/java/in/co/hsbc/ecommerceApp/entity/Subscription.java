package in.co.hsbc.ecommerceApp.entity;

import java.util.Date;

public class Subscription {

        private int id;
        private int customerId;
        private int productId;
        private String plan; // weekly, bi-weekly, monthly, custom
        private Date startDate;
        private Date endDate;
        private boolean isActive;

        // Getters and Setters
        //...

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public String getPlan() {
        return plan;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return isActive;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
