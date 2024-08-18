package in.co.hsbc.ecommerceApp.model.exceptions;

public class SubscriptionAlreadyInactiveException extends Exception{
    public SubscriptionAlreadyInactiveException() {
    }

    public SubscriptionAlreadyInactiveException(String message) {
        super(message);
    }

    public SubscriptionAlreadyInactiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionAlreadyInactiveException(Throwable cause) {
        super(cause);
    }
}
