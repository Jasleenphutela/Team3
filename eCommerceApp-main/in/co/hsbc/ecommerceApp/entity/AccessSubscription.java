package in.co.hsbc.ecommerceApp.entity;

public class AccessSubscription extends Subscriptions{
    public AccessSubscription(int subscriptionValidity) {
        super(subscriptionValidity, "Access","active");
    }
}
