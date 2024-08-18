package in.co.hsbc.ecommerceApp.entity;

import java.time.LocalDate;

public class Subscriptions {
    private int SubscriptionValidity;
    private String subscriptionType;
    private String status;
    private LocalDate expiryDate;
    private int productId;

    public Subscriptions(int subscriptionValidity, String subscriptionType,String status) {
        SubscriptionValidity = subscriptionValidity;
        this.subscriptionType = subscriptionType;
        this.status=status;
    }

    public Subscriptions(String subscriptionType, String status, LocalDate expiryDate, int productId) {
        this.subscriptionType = subscriptionType;
        this.status = status;
        this.expiryDate = expiryDate;
        this.productId = productId;
    }

    public LocalDate getExpiry(){
        LocalDate date=LocalDate.now();
        return date.plusDays(this.SubscriptionValidity);
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public int getProductId() {
        return productId;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Subscriptions{" +
                "subscriptionType='" + subscriptionType + '\'' +
                ", status='" + status + '\'' +
                ", expiryDate=" + expiryDate +
                ", productId=" + productId +
                '}';
    }
}
