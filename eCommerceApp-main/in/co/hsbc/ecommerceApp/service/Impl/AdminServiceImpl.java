package in.co.hsbc.ecommerceApp.service.Impl;

import in.co.hsbc.ecommerceApp.adminDao.AdminDao;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscriptions;
import in.co.hsbc.ecommerceApp.model.exceptions.*;
import in.co.hsbc.ecommerceApp.service.AdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService{

    private AdminDao adminDao;
    public AdminServiceImpl(AdminDao adminDao) {
       this.adminDao=adminDao;
    }

    public boolean registerNewProduct(Product p){
        try {
            return (adminDao.addProduct(p)) > 0;
        } catch (ClassNotFoundException | ProductNotFoundException e) {
            System.out.println("Product Couldn't Be Added To The Database !");
        } catch (ProductAlreadyPresentException e){
            System.out.println("Product Already Exists With The Given Id !");
        }
        return false;
    }

    @Override
    public boolean modifyProduct(Product p) {
        try {
            return adminDao.alterProduct(p) > 0;
        } catch (ClassNotFoundException | ProductNotFoundException e) {
            System.out.println("Product With The Given Id Does Not Exist In The Database !");
        }
        return false;
    }

    @Override
    public boolean removeProduct(int id) {
        try {
            return adminDao.eradicateProduct(id) > 0;
        } catch (ClassNotFoundException | ProductNotFoundException e) {
            System.out.println("Product Couldn't Be Found In The Database !");
        }
        return false;
    }

    @Override
    public boolean registerNewSubscription(Subscriptions subscriptions, int id) {
        try {
            return adminDao.insertSubscription(subscriptions,id) > 0;
        } catch (ClassNotFoundException | SubscriptionNotFoundException e) {
            System.out.println("Subscription Couldn't Be Inserted !");
        } catch (SubscriptionAlreadyExistsException e) {
            System.out.println("Same Subscription Already Exists For The Given Product ID !");
        }
        return false;
    }

    @Override
    public boolean removeSubscription(String subscriptionType, int id) {
        try {
            return adminDao.deactivateSubscription(subscriptionType,id) > 0;
        } catch (ClassNotFoundException | SubscriptionNotFoundException e) {
            System.out.println("Subscription Couldn't Be Found !");
        } catch (SubscriptionAlreadyInactiveException e) {
            System.out.println("Subscription Is Already In Inactive State !");
        }
        return false;
    }

    @Override
    public boolean addSubscription(String subscriptionType, int id) {
        try {
            return adminDao.activateSubscription(subscriptionType,id) > 0;
        } catch (ClassNotFoundException | SubscriptionNotFoundException e) {
            System.out.println("Subscription Couldn't Be Found !");
        } catch (SubscriptionAlreadyActiveException e) {
            System.out.println("Subscription Is Already In Active State !");
        }
        return false;
    }

    @Override
    public boolean displayAllSubscriptions() {
        try {
            List<Subscriptions>subscriptionsList = adminDao.listAllSubscriptions();
            for (Subscriptions subscriptions : subscriptionsList) {
                System.out.println(subscriptions);
            }
            return true;
        } catch (ClassNotFoundException | SQLException | SubscriptionNotFoundException ex) {
            System.out.println("Subscriptions Can't Be Found !");
        }

        return false;
    }
}
