import java.io.*;
import java.util.*;

public class InventoryManagementSystem {

    private static final String FILE_NAME = "inventory_data.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====== INVENTORY MANAGEMENT SYSTEM ======");
            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Calculate Total Stock Value");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    calculateTotalStockValue();
                    break;
                case 5:
                    System.out.println("Exiting Application...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);
    }

    // Add Product to File
    private static void addProduct() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            System.out.print("Enter Product ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Product Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();

            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();

            Product product = new Product(id, name, quantity, price);

            writer.write(product.toFileString());
            writer.newLine();

            System.out.println("Product added successfully!");

        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    // Display All Products
    private static void displayProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            System.out.println("\nID | Name | Quantity | Price");
            System.out.println("-------------------------------------");

            while ((line = reader.readLine()) != null) {
                Product product = Product.fromFileString(line);
                product.display();
            }

        } catch (IOException e) {
            System.out.println("No records found.");
        }
    }

    // Search Product by ID
    private static void searchProduct() {

        System.out.print("Enter Product ID to search: ");
        int searchId = scanner.nextInt();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {
                Product product = Product.fromFileString(line);

                if (product.getId() == searchId) {
                    product.display(true);   // Overloaded method used
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Product not found.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // Calculate Total Stock Value
    private static void calculateTotalStockValue() {

        double totalValue = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {
                Product product = Product.fromFileString(line);
                totalValue += product.getQuantity() * product.getPrice();
            }

            System.out.println("Total Stock Value: " + totalValue);

        } catch (IOException e) {
            System.out.println("Error calculating total value.");
        }
    }
}
