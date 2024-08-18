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

    public void insertProduct(Object o){
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        System.out.println("Enter id name and price of the product");
        sc=new Scanner(System.in);
        int id=sc.nextInt();
        String name=sc.next();
        double price=sc.nextDouble();
        Product p=new Product(id,name,price);
        if(service.registerNewProduct(p))
            System.out.println("Product Added Successfully !");
        else
            System.out.println("Sorry For The Inconvenience !");
    }

    public void updateProduct(Object o){
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        sc=new Scanner(System.in);
        System.out.println("Enter product id of the product whose details you want to update");
        int id=sc.nextInt();
        System.out.println("Enter new product name and price");
        String name= sc.next();
        double price=sc.nextDouble();
        Product p=new Product(id,name,price);
        if (service.modifyProduct(p)) {
            System.out.println("Updation Successful");
        } else {
            System.out.println("Sorry For The Inconvenience");
        }
    }

    public void deleteProduct(Object o) {
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        sc=new Scanner(System.in);
        System.out.println("Enter product id of the product which you want to delete");
        int id=sc.nextInt();
        if (service.removeProduct(id)) {
            System.out.println("Deletion Successful");
        } else {
            System.out.println("Sorry For The Inconvenience");
        }
    }

    public void insertSubscription(Object o){
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        sc=new Scanner(System.in);
        System.out.println("Enter product id for which you want to add subscription");
        int id=sc.nextInt();
        System.out.println("Enter validity of subscription you want to add");
        int validity=sc.nextInt();
        Subscriptions subscriptions;
        System.out.println("Enter 1 for Recurring Subscriptions \n 2 for Access Subscriptions");
        int option=sc.nextInt();
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

    public void subChangeSubscriptionStatus(Object o){
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        sc=new Scanner(System.in);
        System.out.println("Enter product id for which you want to deactivate subscription");
        int id=sc.nextInt();
        System.out.println("Enter type of subscription you want to deactivate (Recurring or Access)");
        String type=sc.next();
        if(service.removeSubscription(type,id)){
            System.out.println("Subscription Status Changed Successfully !");
        }
        else{
            System.out.println("Sorry For The Inconvenience");
        }
    }

    public void addChangeSubscriptionStatus(Object o){
        if(!(o instanceof Admin)){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        sc=new Scanner(System.in);
        System.out.println("Enter product id for which you want to activate subscription");
        int id=sc.nextInt();
        System.out.println("Enter type of subscription you want to activate (Recurring or Access)");
        String type=sc.next();
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
