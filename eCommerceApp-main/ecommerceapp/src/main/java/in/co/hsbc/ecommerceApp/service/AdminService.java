package in.co.hsbc.ecommerceApp.service;

import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscriptions;

public interface AdminService {

    public boolean registerNewProduct(Product p);

    public boolean modifyProduct(Product id);

    public boolean removeProduct(int id);

    public boolean registerNewSubscription(Subscriptions subscriptions, int id);

    public boolean removeSubscription(String subscriptionType,int id);

    public boolean addSubscription(String subscriptionType, int id);

    public boolean displayAllSubscriptions();
}
