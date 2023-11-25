import DaoClass.Product;
import DaoClass.ShoppingCart;
import DaoClass.Order;
import java.util.List;

public class OrderService {
    private final ShoppingCart shoppingCart;
    private final Order orderDao;

    public OrderService(ShoppingCart shoppingCart, Order orderDao) {
        this.shoppingCart = shoppingCart;
        this.orderDao = orderDao;
    }

    public void placeOrder(int userId) {
        // Получаем продукты в корзине пользователя
        List<Product> productsInCart = shoppingCart.getAllProductsInCart(userId);

        if (productsInCart.isEmpty()) {
            System.out.println("Корзина пользователя пуста. Заказ не может быть оформлен.");
            return;
        }

        // Вычисляем общую сумму заказа
        double totalOrderPrice = 0.0;
        for (Product product : productsInCart) {
            totalOrderPrice += product.getPrice();
        }

        // Создаем заказ
        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(7);
        order.setQuantity(productsInCart.size());
        order.setTotalPrice(totalOrderPrice);

        // Сохраняем заказ в базе данных
        orderDao.saveOrder(order);

        // Очищаем корзину пользователя после успешного оформления заказа
        shoppingCart.clearCartById(userId);

        // Выводим информацию о заказе
        System.out.println("Заказ успешно оформлен. Информация о заказе:");
        System.out.println("ID заказа: " + order.getOrderId());
        System.out.println("Общая сумма заказа: " + order.getTotalPrice());
        System.out.println("Количество товаров: " + order.getQuantity());
    }

    public static void main(String[] args) {
        // Инициализация DAO
        ShoppingCart shoppingCartDao = new ShoppingCart();
        Order orderDao = new Order();

        // Инициализация сервиса
        OrderService orderService = new OrderService(shoppingCartDao, orderDao);

        int userId = 12;

        // Пользователь добавляет продукты в корзину
        shoppingCartDao.addProductToCart(userId, "Бекон", 100.99);

        // Пользователь оформляет заказ
        orderService.placeOrder(userId);
    }
}
