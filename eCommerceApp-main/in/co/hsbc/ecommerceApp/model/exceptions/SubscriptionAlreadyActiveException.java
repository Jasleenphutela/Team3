package in.co.hsbc.ecommerceApp.model.exceptions;

public class SubscriptionAlreadyActiveException extends Exception{
    public SubscriptionAlreadyActiveException() {
    }

    public SubscriptionAlreadyActiveException(String message) {
        super(message);
    }

    public SubscriptionAlreadyActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionAlreadyActiveException(Throwable cause) {
        super(cause);
    }
}
