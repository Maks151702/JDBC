package DaoInterface;

import DaoClass.Order;

import java.util.List;

public interface OrderDao {
    void saveOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(int userId);
}
