package in.co.hsbc.ecommerceApp.adminDao;

import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscriptions;
import in.co.hsbc.ecommerceApp.model.exceptions.*;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    public int addProduct(Product p) throws ClassNotFoundException, ProductNotFoundException, ProductAlreadyPresentException;

    public int alterProduct(Product p) throws ClassNotFoundException, ProductNotFoundException;

    public int eradicateProduct(int id) throws ProductNotFoundException, ClassNotFoundException;

    public int deactivateSubscription(String subscriptionType,int id) throws ClassNotFoundException, SubscriptionAlreadyInactiveException, SubscriptionNotFoundException;

    public int activateSubscription(String subscriptionType, int id) throws SubscriptionAlreadyActiveException, SubscriptionNotFoundException, ClassNotFoundException;

    public int insertSubscription(Subscriptions subscriptions, int id) throws ClassNotFoundException, SubscriptionAlreadyExistsException, SubscriptionNotFoundException;

    public List<Subscriptions> listAllSubscriptions() throws ClassNotFoundException, SQLException, SubscriptionNotFoundException;
}
