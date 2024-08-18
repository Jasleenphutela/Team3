package in.co.hsbc.ecommerceApp.dao;

import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.exception.CustomerNotFoundException;
import in.co.hsbc.ecommerceApp.exception.SubscriptionNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDao {
    void addSubscription(Subscription subscription) throws SQLException;
    Subscription getSubscriptionById(int id) throws SQLException, SubscriptionNotFoundException;
    List<Subscription> getSubscriptionsByCustomerId(int customerId) throws SQLException, CustomerNotFoundException;
    void updateSubscription(Subscription subscription) throws SQLException;
    void cancelSubscription(int id) throws SQLException, SubscriptionNotFoundException;
}
