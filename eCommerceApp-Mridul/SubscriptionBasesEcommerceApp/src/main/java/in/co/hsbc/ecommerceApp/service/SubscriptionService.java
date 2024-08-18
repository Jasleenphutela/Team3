package in.co.hsbc.ecommerceApp.service;

import in.co.hsbc.ecommerceApp.entity.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionService {
    void addSubscription(Subscription subscription) throws SQLException;
    Subscription getSubscriptionById(int id) throws SQLException;
    List<Subscription> getSubscriptionsByCustomerId(int customerId) throws SQLException;
    void updateSubscription(Subscription subscription) throws SQLException;
    void cancelSubscription(int id) throws SQLException;
}
