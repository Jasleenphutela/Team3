package in.co.hsbc.ecommerceApp.entity;

import java.util.Date;

public class Order {

        private int id;
        private int subscriptionId;
        private Date orderDate;
        private Date deliveryDate;
        private String status;

    public Order(int id, int subscriptionId, Date orderDate, Date deliveryDate, String status) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    // Getters and Setters


    public int getId() {
        return id;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
