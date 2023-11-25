import DaoClass.*;


import java.util.List;


public class Main {



    public static void main(String[] args) {

        User newUser = new User();
//        newUser.setFirstName("John");
//        newUser.setSecondName("Doe");
//        newUser.setId(5);
//
//
//        newUser.createUser(newUser);
//
//         User retrievedUser = newUser.getUserById(2);
//
//        if (retrievedUser != null) {
//            System.out.println("User found: " + retrievedUser.getFirstName() + " " + retrievedUser.getSecondName());
//        } else {
//            System.out.println("User not found.");
//        }
//
//
//        User userInstance = new User();
//
//    // Получаем список всех пользователей
//
//        List<User> allUsers = userInstance.getAllUsers();
//
//    // Выводим информацию о каждом пользователе
//        for (User user : allUsers) {
//        System.out.println("User ID: " + user.getId() +
//                ", First Name: " + user.getFirstName() +
//                ", Second Name: " + user.getSecondName());
//        }
//        newUser.updateUser(newUser);
//
//        newUser.deleteUser(5);


        UserDetails userDetails1 = new UserDetails();
        
//        userDetails1.setAge(25);
//        userDetails1.setPhoneNumber("123-456-7890");
//        userDetails1.setId(7);
//
//        userDetails1.createUserDetails(userDetails1);


//        UserDetails retrievedUserDetails = userDetails1.getUserDetailsById(1);
//        if (retrievedUserDetails != null) {
//            System.out.println("User found: " + retrievedUserDetails.getAge() + " " + retrievedUserDetails.getPhoneNumber());
//        } else {
//            System.out.println("User not found.");
//        }

//        UserDetails userDetailsInstance = new UserDetails();
//
//    // Получаем список всех пользователей
//
//        List<UserDetails> userDetailsList = userDetailsInstance.getAllUserDetails();
//
//        // Выводим информацию о каждом объекте UserDetails
//        for (UserDetails userDetails : userDetailsList) {
//            System.out.println("UserDetails ID: " + userDetails.getId() +
//                    ", Age: " + userDetails.getAge() +
//                    ", Phone Number: " + userDetails.getPhoneNumber());
//        }


//        userDetails.updateUserDetails(userDetails);

//        userDetails.deleteUserDetails(1);

        Product product1 = new Product();
//        product.setCategory("Счастье");
//        product.setProductName("Виски");
//        product.setPrice(88.95);
//        product.createProduct(product);

//        Product retrievedProduct = product.getProductById(8);
//
//        // Выводим информацию о продукте
//        if (retrievedProduct != null) {
//            System.out.println("Product ID: " + retrievedProduct.getProductId());
//            System.out.println("Product Name: " + retrievedProduct.getProductName());
//            System.out.println("Category: " + retrievedProduct.getCategory());
//            System.out.println("Price: " + retrievedProduct.getPrice());
//        } else {
//            System.out.println("Продукт с указанным ID не найден.");
//        }

//        List<Product> productList = product1.getAllProducts();
//
//        // Выводим информацию о каждом продукте
//        for (Product product : productList) {
//            System.out.println("Product ID: " + product.getProductId());
//            System.out.println("Product Name: " + product.getProductName());
//            System.out.println("Category: " + product.getCategory());
//            System.out.println("Price: " + product.getPrice());
//            System.out.println("---------------");
//        }
//        product1.deleteProduct(8);

//        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
//
//        // Пример добавления продукта в корзину пользователя
//        int userId = 1; // Замените на нужный вам идентификатор пользователя
//        int productId = 1; // Замените на нужный вам идентификатор продукта
//        int quantity = 2; // Замените на нужное вам количество продукта
//
//        shoppingCartItem.addProductToCart(userId, productId, quantity);
//        System.out.println("Продукт добавлен в корзину пользователя.");

        Order order1 = new Order();
//        order.setUserId(1);
//        order.setProductId(1);
//        order.setQuantity(2);
//        order.setTotalPrice(50.0);
//
//        order.saveOrder(order);
//        System.out.println("Заказ сохранен. OrderID: " + order.getOrderId());

//        List<Order> allOrders = order1.getAllOrders();
//
//        // Выводим информацию о каждом заказе
//        for (Order order : allOrders) {
//            System.out.println("OrderID: " + order.getOrderId());
//            System.out.println("UserID: " + order.getUserId());
//            System.out.println("ProductID: " + order.getProductId());
//            System.out.println("Quantity: " + order.getQuantity());
//            System.out.println("TotalPrice: " + order.getTotalPrice());
//            System.out.println("---------------------------");
//        }

//        List<Order> userOrders = order1.getOrdersByUserId(3);
//
//        // Выводим информацию о заказах пользователя
//        for (Order order : userOrders) {
//            System.out.println("OrderID: " + order.getOrderId());
//            System.out.println("UserID: " + order.getUserId());
//            System.out.println("ProductID: " + order.getProductId());
//            System.out.println("Quantity: " + order.getQuantity());
//            System.out.println("TotalPrice: " + order.getTotalPrice());
//            System.out.println("---------------------------");
//        }

        ShoppingCart shoppingCart1 = new ShoppingCart();
//        shoppingCart1.addProductToCart(8,"Вино", 15.49);
//        shoppingCart1.addOtherProduct(9, "Бекон", 100.99);

//        shoppingCart1.removeProductFromCart(9,"Бекон");
//        List<Product> productsInCart = shoppingCart1.getAllProductsInCart(4);
//
//        // Вывод результатов
//        for (Product product : productsInCart) {
//            System.out.println("Product name: " + product.getProductName());
//            System.out.println("Total price: " + product.getPrice());
//            System.out.println("------------------------");
//        }

//        shoppingCart1.clearCartById(8);

    }
}