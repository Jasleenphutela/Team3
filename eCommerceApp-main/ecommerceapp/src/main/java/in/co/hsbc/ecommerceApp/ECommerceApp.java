package in.co.hsbc.ecommerceApp;

import in.co.hsbc.ecommerceApp.adminController.AdminController;
import in.co.hsbc.ecommerceApp.beanfactory.BeanFactory;
import in.co.hsbc.ecommerceApp.entity.Admin;
import in.co.hsbc.ecommerceApp.entity.Product;

import java.util.Scanner;

public class ECommerceApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BeanFactory factory = new BeanFactory();
        AdminController controller = factory.getController();
        System.out.println("Enter id of the Admin");
        int id=sc.nextInt();
        String name;
        double price;
        Product p;
        Admin admin=Admin.getInstance(id);
        int choice;
        do {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Insert Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Subscription Menu");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter id name and price of the product");
                    sc=new Scanner(System.in);
                    id=sc.nextInt();
                    name=sc.next();
                    price=sc.nextDouble();

                    controller.insertProduct(admin,id,name,price);
                    break;
                case 2:
                    sc=new Scanner(System.in);
                    System.out.println("Enter product id of the product whose details you want to update");
                    id=sc.nextInt();
                    System.out.println("Enter new product name and price");
                    name= sc.next();
                    price=sc.nextDouble();
                    controller.updateProduct(admin,id,name,price);
                    break;
                case 3:
                    sc=new Scanner(System.in);
                    System.out.println("Enter product id of the product which you want to delete");
                    id=sc.nextInt();
                    controller.deleteProduct(admin,id);
                    break;
                case 4:
                    subscriptionMenu(controller,admin);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }

    private static void subscriptionMenu(AdminController controller,Admin admin) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String type;

        do {
            System.out.println("=== Subscription Menu ===");
            System.out.println("1. Insert Subscription");
            System.out.println("2. Deactivate Subscription Status");
            System.out.println("3. Activate Subscription Status");
            System.out.println("4. View All Subscriptions");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc=new Scanner(System.in);
                    System.out.println("Enter product id for which you want to add subscription");
                    int id=sc.nextInt();
                    System.out.println("Enter validity of subscription you want to add");
                    int validity=sc.nextInt();
                    System.out.println("Enter 1 for Recurring Subscriptions \n 2 for Access Subscriptions");
                    int option=sc.nextInt();
                    controller.insertSubscription(admin,id,validity,option);
                    break;
                case 2:
                    sc=new Scanner(System.in);
                    System.out.println("Enter product id for which you want to deactivate subscription");
                    id=sc.nextInt();
                    System.out.println("Enter type of subscription you want to deactivate (Recurring or Access)");
                    type=sc.next();
                    controller.subChangeSubscriptionStatus(admin,id,type);
                    break;
                case 3:
                    sc=new Scanner(System.in);
                    System.out.println("Enter product id for which you want to activate subscription");
                    id=sc.nextInt();
                    System.out.println("Enter type of subscription you want to activate (Recurring or Access)");
                    type=sc.next();
                    controller.addChangeSubscriptionStatus(admin,id,type);
                    break;
                case 4:
                    controller.viewAllSubscriptions(admin);
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }
}
