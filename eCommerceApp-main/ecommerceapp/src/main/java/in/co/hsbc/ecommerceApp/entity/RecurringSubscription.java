package in.co.hsbc.ecommerceApp.entity;

public class RecurringSubscription extends Subscriptions{

    public RecurringSubscription(int subscriptionValidity) {
        super(subscriptionValidity, "Recurring","active");
    }
}
