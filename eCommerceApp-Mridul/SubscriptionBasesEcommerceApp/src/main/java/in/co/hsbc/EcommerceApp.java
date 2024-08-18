package in.co.hsbc;

import in.co.hsbc.ecommerceApp.controller.CustomerController;

import java.util.Scanner;

public class EcommerceApp {

    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();
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

            switch (choice) {
                case 1:
                    customerController.registerCustomer();
                    break;
                case 2:
                    customerController.browseProducts();
                    break;
                case 3:
                    customerController.subscribeToProduct();
                    break;
                case 4:
                    customerController.viewSubscriptions();
                    break;
                case 5:
                    customerController.cancelSubscription();
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
