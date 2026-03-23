\# Mini E-Commerce Management System



A console-based Java mini project that demonstrates core Object-Oriented Programming concepts with basic JDBC and MySQL integration.



\## Features



\*\*Seller\*\*

\- Register and login

\- Add products

\- View products



\*\*Customer\*\*

\- Register and login

\- View products

\- Search products

\- Add to cart

\- Remove from cart

\- View cart

\- Checkout

\- Use saved address or enter a new address

\- UPI payment with OTP

\- Card payment with OTP



\*\*Database\*\*

\- Store user credentials

\- Store product details

\- Store saved addresses

\- Persist data across runs



\## Tech Stack

\- Java

\- JDBC

\- MySQL

\- Git

\- GitHub



\## Modules



\### Main Flow

Files: `Main.java`, `ConsoleUI.java`  

Handles application start, menus, and navigation.



\### Authentication

Files: `User.java`, `Customer.java`, `Seller.java`, `AuthSystem.java`, `UserDatabase.java`  

Handles registration, login, role validation, and user data.



\### Seller

File: `SellerPanel.java`  

Handles seller-side operations.



\### Product / Shop

Files: `Product.java`, `Shop.java`  

Handles product creation, listing, search, and stock management.



\### Customer

File: `CustomerPanel.java`  

Handles customer-side flow including search, cart usage, checkout, and address selection.



\### Cart

Files: `CartItem.java`, `Cart.java`  

Handles cart storage, totals, and receipt generation.



\### Payment

Files: `Payment.java`, `UPIPayment.java`, `CardPayment.java`, `PaymentScreen.java`  

Handles payment abstraction, payment selection, and OTP verification.



\### Database

File: `DBConnection.java`  

Handles MySQL connectivity and supports persistence in `UserDatabase.java` and `Shop.java`.



\## File Overview

\- `Main.java` – Entry point

\- `ConsoleUI.java` – Console formatting helper

\- `User.java` – Base user class

\- `Customer.java` – Customer class

\- `Seller.java` – Seller class

\- `AuthSystem.java` – Login and registration logic

\- `UserDatabase.java` – User-related database operations

\- `SellerPanel.java` – Seller interface

\- `Product.java` – Product model

\- `Shop.java` – Product management logic

\- `CustomerPanel.java` – Customer interface

\- `CartItem.java` – Cart item model

\- `Cart.java` – Cart operations

\- `Payment.java` – Payment interface

\- `UPIPayment.java` – UPI payment implementation

\- `CardPayment.java` – Card payment implementation

\- `PaymentScreen.java` – Payment method selector

\- `DBConnection.java` – JDBC connection helper

\- `README.md` – Documentation



\## OOP Concepts Demonstrated



\### Encapsulation

Private data members with controlled access through methods.



Examples:

\- `User`

\- `Product`

\- `CartItem`



\### Inheritance

Specialized classes derived from a base class.



Examples:

\- `Customer extends User`

\- `Seller extends User`



\### Abstraction

Common behavior exposed through an interface.



Example:

\- `Payment`



\### Polymorphism

Different classes providing different implementations of the same method.



Examples:

\- `UPIPayment implements Payment`

\- `CardPayment implements Payment`



\### Composition

Objects built using other objects.



Examples:

\- `Cart` contains `CartItem`

\- `CartItem` contains `Product`



\## Application Flow



\### Seller Flow

1\. Start application

2\. Select seller

3\. Register or login

4\. Add products

5\. View products

6\. Logout



\### Customer Flow

1\. Start application

2\. Select customer

3\. Register or login

4\. View or search products

5\. Add items to cart

6\. View or remove cart items

7\. Checkout

8\. Select saved address or enter a new address

9\. Choose payment method

10\. Verify OTP

11\. Place order



\## Database Tables

\- `users`

\- `products`

\- `user\_addresses`





