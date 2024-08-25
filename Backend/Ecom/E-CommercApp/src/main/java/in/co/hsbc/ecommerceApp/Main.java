package in.co.hsbc.ecommerceApp;

import in.co.hsbc.ecommerceApp.controller.AdminController;
import in.co.hsbc.ecommerceApp.controller.CustomerController;
import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.service.impl.CustomerServiceImpl;
import in.co.hsbc.ecommerceApp.service.impl.AdminServiceImpl;
import in.co.hsbc.ecommerceApp.dao.impl.CustomerDaoImpl;
import in.co.hsbc.ecommerceApp.dao.impl.AdminDaoImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Setting up DAOs
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        AdminDaoImpl adminDao = new AdminDaoImpl();

        // Setting up Services
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerDao);
        AdminServiceImpl adminService = new AdminServiceImpl(adminDao);

        // Setting up Controllers
        CustomerController customerController = new CustomerController(customerService);
        AdminController adminController = new AdminController(adminService);

        boolean running = true;
        do {
            // Main Menu for Mode Selection
            System.out.println("\nSelect Mode:");
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("3. Exit");

            int modeChoice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            if (modeChoice == 1) {
                // Customer Mode
                boolean customerModeRunning = true;
                do {
                    System.out.println("\nCustomer Operations:");
                    System.out.println("1. Add Customer");
                    System.out.println("2. Get Customer By ID");
                    System.out.println("3. Place Order");
                    System.out.println("4. Cancel Subscription");
                    System.out.println("5. Back to Main Menu");

                    int customerChoice = scanner.nextInt();
                    scanner.nextLine(); // consume the newline

                    switch (customerChoice) {
                        case 1:
                            // Add Customer
                            Customer newCustomer = new Customer();
                            System.out.print("Enter customer name: ");
                            newCustomer.setName(scanner.nextLine());
                            System.out.print("Enter customer email: ");
                            newCustomer.setEmail(scanner.nextLine());
                            System.out.print("Enter customer password: ");
                            newCustomer.setPassword(scanner.nextLine());
                            customerController.addCustomer(newCustomer);
                            System.out.println("Customer added successfully.");
                            break;

                        case 2:
                            // Get Customer By ID
                            System.out.print("Enter customer ID to fetch: ");
                            int customerId = scanner.nextInt();
                            scanner.nextLine(); // consume the newline
                            Customer fetchedCustomer = customerController.getCustomerById(customerId);
                            if (fetchedCustomer != null) {
                                System.out.println("Customer fetched: " + fetchedCustomer.getName() + ", " + fetchedCustomer.getEmail());
                            } else {
                                System.out.println("Customer not found.");
                            }
                            break;

                        case 3:
                            // Place Order
                            System.out.print("Enter subscription ID to place order: ");
                            int subscriptionId = scanner.nextInt();
                            scanner.nextLine(); // consume the newline
                            customerController.placeOrder(subscriptionId);
                            System.out.println("Order placed successfully.");
                            break;

                        case 4:
                            // Cancel Subscription
                            System.out.print("Enter subscription ID to cancel: ");
                            int cancelSubscriptionId = scanner.nextInt();
                            scanner.nextLine(); // consume the newline
                            customerController.cancelSubscription(cancelSubscriptionId);
                            System.out.println("Subscription cancelled successfully.");
                            break;

                        case 5:
                            // Back to Main Menu
                            customerModeRunning = false;
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }

                } while (customerModeRunning);

            } else if (modeChoice == 2) {
                // Admin Mode
                boolean adminModeRunning = true;
                do {
                    System.out.println("\nAdmin Operations:");
                    System.out.println("1. Add Product");
                    System.out.println("2. Get All Products");
                    System.out.println("3. Update Product");
                    System.out.println("4. Delete Product");
                    System.out.println("5. Add Subscription");
                    System.out.println("6. Get All Subscriptions");
                    System.out.println("7. Get All Orders");
                    System.out.println("8. Back to Main Menu");

                    int adminChoice = scanner.nextInt();
                    scanner.nextLine(); // consume the newline

                    switch (adminChoice) {
                        case 1:
                            // Add Product
                            Product newProduct = new Product();
                            System.out.print("Enter product name: ");
                            newProduct.setName(scanner.nextLine());
                            System.out.print("Enter product price: ");
                            newProduct.setPrice(scanner.nextDouble());
                            scanner.nextLine(); // consume the newline
                            System.out.print("Enter product description: ");
                            newProduct.setDescription(scanner.nextLine());
                            adminController.addProduct(newProduct);
                            System.out.println("Product added successfully.");
                            break;

                        case 2:
                            // Get All Products
                            List<Product> products = adminController.getAllProducts();
                            System.out.println("All Products:");
                            for (Product p : products) {
                                System.out.println(p.getProductId() + ": " + p.getName() + " - $" + p.getPrice());
                            }
                            break;

                        case 3:
                            // Update Product
                            System.out.print("Enter product ID to update: ");
                            int productId = scanner.nextInt();
                            scanner.nextLine(); // consume the newline
                            Product updateProduct = new Product();
                            updateProduct.setProductId(productId);
                            System.out.print("Enter new product name: ");
                            updateProduct.setName(scanner.nextLine());
                            System.out.print("Enter new product price: ");
                            updateProduct.setPrice(scanner.nextDouble());
                            scanner.nextLine(); // consume the newline
                            System.out.print("Enter new product description: ");
                            updateProduct.setDescription(scanner.nextLine());
                            adminController.updateProduct(updateProduct);
                            System.out.println("Product updated successfully.");
                            break;

                        case 4:
                            // Delete Product
                            System.out.print("Enter product ID to delete: ");
                            int deleteProductId = scanner.nextInt();
                            scanner.nextLine(); // consume the newline
                            adminController.deleteProduct(deleteProductId);
                            System.out.println("Product deleted successfully.");
                            break;

                        case 5:
                            // Add Subscription
                            Subscription newSubscription = new Subscription();
                            System.out.print("Enter customer ID: ");
                            newSubscription.setCustomerId(scanner.nextInt());
                            scanner.nextLine(); // consume the newline
                            System.out.print("Enter product ID: ");
                            newSubscription.setProductId(scanner.nextInt());
                            scanner.nextLine(); // consume the newline
                            System.out.print("Enter subscription frequency (weekly, bi-weekly, monthly): ");
                            newSubscription.setFrequency(scanner.nextLine());
                            System.out.print("Enter start date (YYYY-MM-DD): ");
                            newSubscription.setStartDate(scanner.nextLine());
                            System.out.print("Enter end date (YYYY-MM-DD): ");
                            newSubscription.setEndDate(scanner.nextLine());
                            adminController.addSubscription(newSubscription);
                            System.out.println("Subscription added successfully.");
                            break;

                        case 6:
                            // Get All Subscriptions
                            List<Subscription> subscriptions = adminController.getAllSubscriptions();
                            System.out.println("All Subscriptions:");
                            for (Subscription s : subscriptions) {
                                System.out.println("Subscription ID: " + s.getSubscriptionId() + ", Customer ID: " + s.getCustomerId() + ", Product ID: " + s.getProductId());
                            }
                            break;

                        case 7:
                            // Get All Orders
                            List<Order> orders = adminController.getAllOrders();
                            System.out.println("All Orders:");
                            for (Order o : orders) {
                                System.out.println("Order ID: " + o.getOrderId() + ", Subscription ID: " + o.getSubscriptionId() + ", Delivery Date: " + o.getDeliveryDate() + ", Status: " + o.getStatus());
                            }
                            break;

                        case 8:
                            // Back to Main Menu
                            adminModeRunning = false;
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }

                } while (adminModeRunning);

            } else if (modeChoice == 3) {
                // Exit
                running = false;
                System.out.println("Exiting the application.");

            } else {
                System.out.println("Invalid choice. Please try again.");
            }

        } while (running);

        scanner.close();
    }
}
