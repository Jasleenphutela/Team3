package in.co.hsbc.ecommerceApp.service.Impl;

import in.co.hsbc.ecommerceApp.dao.SubscriptionDao;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.exception.CustomerNotFoundException;
import in.co.hsbc.ecommerceApp.exception.InvalidSubscriptionException;
import in.co.hsbc.ecommerceApp.exception.SubscriptionNotFoundException;
import in.co.hsbc.ecommerceApp.service.SubscriptionService;
import in.co.hsbc.ecommerceApp.util.BeanFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionDao subscriptionDAO = BeanFactory.getSubscriptionDAO();

    @Override
    public List<Subscription> getSubscriptionsByCustomerId(int customerId) throws SQLException {
        try {
            return subscriptionDAO.getSubscriptionsByCustomerId(customerId);
        } catch (CustomerNotFoundException e) {
           e.printStackTrace();
           return new ArrayList<>();
        }
    }

    @Override
    public Subscription getSubscriptionById(int id) {
        try {
            return subscriptionDAO.getSubscriptionById(id);
        } catch (SQLException e) {
            // Handle SQL exceptions here
            e.printStackTrace();
            return new Subscription();
        } catch (SubscriptionNotFoundException e) {
            // Handle custom exceptions here
            e.printStackTrace();
            return new Subscription();
        }
    }

    @Override
    public void addSubscription(Subscription subscription) {
        try {
            subscriptionDAO.addSubscription(subscription);
        } catch (SQLException e) {
            // Handle SQL exceptions here
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubscription(Subscription subscription) {
        try {
            subscriptionDAO.updateSubscription(subscription);
        } catch (SQLException e) {
            // Handle SQL exceptions here
            e.printStackTrace();
        }
    }

    @Override
    public void cancelSubscription(int id) {
        try {
            subscriptionDAO.cancelSubscription(id);
        } catch (SQLException e) {
            // Handle SQL exceptions here
            e.printStackTrace();
        } catch (SubscriptionNotFoundException e) {
            e.printStackTrace();
        }
    }
}


