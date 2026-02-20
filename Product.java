import java.io.*;

public class Product {

    // Private data members (Encapsulation)
    private int id;
    private String name;
    private int quantity;
    private double price;

    // Default Constructor
    public Product() {
        this.id = 0;
        this.name = "";
        this.quantity = 0;
        this.price = 0.0;
    }

    // Parameterized Constructor
    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter Methods
    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Method Overloading – Display Method
    public void display() {
        System.out.println(id + " | " + name + " | " + quantity + " | " + price);
    }

    public void display(boolean detailed) {
        if (detailed) {
            System.out.println("Product ID   : " + id);
            System.out.println("Product Name : " + name);
            System.out.println("Quantity     : " + quantity);
            System.out.println("Price        : " + price);
            System.out.println("----------------------------------");
        } else {
            display();
        }
    }

    // Convert object data to file format
    public String toFileString() {
        return id + "," + name + "," + quantity + "," + price;
    }

    // Convert file line to Product object
    public static Product fromFileString(String line) {
        String[] data = line.split(",");
        return new Product(
                Integer.parseInt(data[0]),
                data[1],
                Integer.parseInt(data[2]),
                Double.parseDouble(data[3])
        );
    }
}
