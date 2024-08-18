package in.co.hsbc.ecommerceApp.model.exceptions;

public class SubscriptionNotFoundException extends Exception{
    public SubscriptionNotFoundException() {
    }

    public SubscriptionNotFoundException(String message) {
        super(message);
    }

    public SubscriptionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionNotFoundException(Throwable cause) {
        super(cause);
    }
}
