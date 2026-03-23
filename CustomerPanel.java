import java.util.Scanner;

class CustomerPanel {

    static Scanner sc = new Scanner(System.in);
    static Cart cart = new Cart();
    static User currentCustomer;

    static void setCurrentCustomer(User user) {
        currentCustomer = user;
    }

    static void customerMenu() {

        while (true) {

            ConsoleUI.printLine();
            System.out.println("CUSTOMER PANEL");
            ConsoleUI.printLine();

            System.out.println("1. View Products");
            System.out.println("2. Search Product");
            System.out.println("3. Add To Cart");
            System.out.println("4. Remove From Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Logout");

            ConsoleUI.printLine();
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    Shop.displayProducts();
                    break;

                case 2:
                    searchProduct();
                    break;

                case 3:
                    addToCart();
                    break;

                case 4:
                    removeItem();
                    break;

                case 5:
                    cart.viewCart();
                    break;

                case 6:
                    checkout();
                    break;

                case 7:
                    currentCustomer = null;
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static void searchProduct() {
        System.out.print("\nEnter product name to search: ");
        String keyword = sc.nextLine();
        Shop.searchProduct(keyword);
    }

    static void addToCart() {

        System.out.print("\nEnter Product ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        if (qty <= 0) {
            System.out.println("Quantity must be greater than 0");
            return;
        }

        Product p = Shop.getProductById(id);

        if (p == null) {
            System.out.println("Product not found");
            return;
        }

        if (p.getStock() == 0) {
            System.out.println("Product Out Of Stock");
            return;
        }

        if (qty > p.getStock()) {
            System.out.println("Only " + p.getStock() + " items available");
            return;
        }

        cart.addToCart(p, qty);
        System.out.println("Product added to cart");
    }

    static void removeItem() {
        System.out.print("\nEnter Product ID to remove: ");
        int id = sc.nextInt();
        sc.nextLine();
        cart.removeItem(id);
    }

    static void checkout() {

        double total = cart.getTotalAmount();

        if (total == 0) {
            System.out.println("\nCart is empty");
            return;
        }

        String username = currentCustomer.getUsername();
        String[] addresses = UserDatabase.getAddresses(username);
        String selectedAddress = "";

        if (addresses.length > 0) {

            System.out.println("\nSaved Addresses:");
            for (int i = 0; i < addresses.length; i++) {
                System.out.println((i + 1) + ". " + addresses[i]);
            }
            System.out.println((addresses.length + 1) + ". Enter New Address");

            System.out.print("Choose address option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice >= 1 && choice <= addresses.length) {
                selectedAddress = addresses[choice - 1];
            } else if (choice == addresses.length + 1) {
                System.out.print("Enter New Delivery Address: ");
                selectedAddress = sc.nextLine();

                if (selectedAddress.trim().isEmpty()) {
                    System.out.println("Address cannot be empty");
                    return;
                }

                UserDatabase.addAddress(username, selectedAddress);
                System.out.println("New address saved successfully");
            } else {
                System.out.println("Invalid choice");
                return;
            }

        } else {
            System.out.print("\nEnter Delivery Address: ");
            selectedAddress = sc.nextLine();

            if (selectedAddress.trim().isEmpty()) {
                System.out.println("Address cannot be empty");
                return;
            }

            UserDatabase.addAddress(username, selectedAddress);
            System.out.println("Address saved successfully");
        }

        System.out.println("\nTotal Amount: Rs." + total);

        boolean success = PaymentScreen.processPayment(total);

        if (success) {
            cart.reduceStockAfterOrder();
            cart.printReceipt(selectedAddress);
            System.out.println("\nOrder placed successfully");
            cart = new Cart();
        } else {
            System.out.println("\nPayment cancelled");
        }
    }
}