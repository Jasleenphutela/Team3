package in.co.hsbc.ecommerceApp.adminController;

import in.co.hsbc.ecommerceApp.entity.*;
import in.co.hsbc.ecommerceApp.service.AdminService;

import java.util.Scanner;

public class AdminController {
    private AdminService service;
    private Scanner sc;
    public AdminController(AdminService service) {
        this.service = service;
    }

    public void insertProduct(Object o,int id,String name,double price){
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        Product p=new Product(id,name,price);
        if(service.registerNewProduct(p))
            System.out.println("Product Added Successfully !");
        else
            System.out.println("Sorry For The Inconvenience !");
    }

    public void updateProduct(Object o,int id,String name,double price){
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        Product p=new Product(id,name,price);
        if (service.modifyProduct(p)) {
            System.out.println("Updation Successful");
        } else {
            System.out.println("Sorry For The Inconvenience");
        }
    }

    public void deleteProduct(Object o,int id) {
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        if (service.removeProduct(id)) {
            System.out.println("Deletion Successful");
        } else {
            System.out.println("Sorry For The Inconvenience");
        }
    }

    public void insertSubscription(Object o,int id,int validity,int option){
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        Subscriptions subscriptions;
        if(option==1)
            subscriptions=new RecurringSubscription(validity);
        else
            subscriptions=new AccessSubscription(validity);
        if(service.registerNewSubscription(subscriptions,id)){
            System.out.println("Subscription Added Successfully");
        }
        else{
            System.out.println("Sorry For The Inconvenience");
        }
    }

    public void subChangeSubscriptionStatus(Object o,int id,String type){
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        if(service.removeSubscription(type,id)){
            System.out.println("Subscription Status Changed Successfully !");
        }
        else{
            System.out.println("Sorry For The Inconvenience");
        }
    }

    public void addChangeSubscriptionStatus(Object o,int id,String type){
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        if(service.addSubscription(type,id)){
            System.out.println("Subscription Status Changed Successfully !");
        }
        else{
            System.out.println("Sorry For The Inconvenience");
        }
    }

    public void viewAllSubscriptions(Object o) {
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        System.out.println("Displaying All Subscriptions");
        if(service.displayAllSubscriptions()){
            System.out.println("Thank You !");
        }
        else
            System.out.println("Sorry For The Inconvenience");
    }
}
