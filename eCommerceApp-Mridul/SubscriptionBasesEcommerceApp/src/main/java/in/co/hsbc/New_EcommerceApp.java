package in.co.hsbc;

import in.co.hsbc.ecommerceApp.controller.CustomerController;
import in.co.hsbc.ecommerceApp.controller.New_CustomerController;

import java.util.Scanner;
public class New_EcommerceApp {
    public static void main(String[] args) {
        New_CustomerController customerController = new New_CustomerController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register Customer");
            System.out.println("2. Browse Products");
            System.out.println("3. Subscribe to Product");
            System.out.println("4. View Subscriptions");
            System.out.println("5. Cancel Subscription");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter your name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter your email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter your password:");
                    String password = scanner.nextLine();
                    customerController.registerCustomer(name, email, password);
                    break;
                case 2:
                    customerController.browseProducts();
                    break;
                case 3:
                    System.out.println("Enter your customer ID:");
                    int customerId = scanner.nextInt();
                    System.out.println("Enter the product ID you want to subscribe to:");
                    int productId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter your subscription plan (weekly, bi-weekly, monthly, custom):");
                    String plan = scanner.nextLine();
                    System.out.println("Enter the start date (yyyy-mm-dd):");
                    String startDate = scanner.nextLine();
                    System.out.println("Enter the end date (yyyy-mm-dd):");
                    String endDate = scanner.nextLine();
                    customerController.subscribeToProduct(customerId, productId, plan, startDate, endDate);
                    break;
                case 4:
                    System.out.println("Enter your customer ID:");
                    int viewCustomerId = scanner.nextInt();
                    customerController.viewSubscriptions(viewCustomerId);
                    break;
                case 5:
                    System.out.println("Enter your subscription ID:");
                    int subscriptionId = scanner.nextInt();
                    customerController.cancelSubscription(subscriptionId);
                    break;
                case 6:
                    System.out.println("Exiting application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
