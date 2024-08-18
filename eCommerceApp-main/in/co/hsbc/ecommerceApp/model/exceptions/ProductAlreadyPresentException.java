package in.co.hsbc.ecommerceApp.model.exceptions;

public class ProductAlreadyPresentException extends Exception{
    public ProductAlreadyPresentException() {
    }

    public ProductAlreadyPresentException(String message) {
        super(message);
    }

    public ProductAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAlreadyPresentException(Throwable cause) {
        super(cause);
    }
}
