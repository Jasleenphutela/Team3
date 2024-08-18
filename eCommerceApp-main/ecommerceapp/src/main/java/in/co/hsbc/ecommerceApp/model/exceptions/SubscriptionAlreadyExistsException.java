package in.co.hsbc.ecommerceApp.model.exceptions;

public class SubscriptionAlreadyExistsException extends Exception{
    public SubscriptionAlreadyExistsException() {
    }

    public SubscriptionAlreadyExistsException(String message) {
        super(message);
    }

    public SubscriptionAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
