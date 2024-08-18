package in.co.hsbc.ecommerceApp;

import in.co.hsbc.ecommerceApp.adminController.AdminController;
import in.co.hsbc.ecommerceApp.beanfactory.BeanFactory;
import in.co.hsbc.ecommerceApp.entity.Admin;

import java.util.Scanner;

public class ECommerceApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BeanFactory factory = new BeanFactory();
        AdminController controller = factory.getController();
        System.out.println("Enter id of the Admin");
        int id=sc.nextInt();
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
                    controller.insertProduct(admin);
                    break;
                case 2:
                    controller.updateProduct(admin);
                    break;
                case 3:
                    controller.deleteProduct(admin);
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
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== Subscription Menu ===");
            System.out.println("1. Insert Subscription");
            System.out.println("2. Deactivate Subscription Status");
            System.out.println("3. Activate Subscription Status");
            System.out.println("4. View All Subscriptions");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    controller.insertSubscription(admin);
                    break;
                case 2:
                    controller.subChangeSubscriptionStatus(admin);
                    break;
                case 3:
                    controller.addChangeSubscriptionStatus(admin);
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
