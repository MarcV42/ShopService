import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo{
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Order getOrderById(String id) {
        return orders.get(id);
    }

    @Override
    @Builder
    public Order addOrder(Order newOrder) {
        // Erstellen Sie eine Kopie der neuen Bestellung mit dem aktuellen Zeitpunkt als Bestellzeitpunkt
        Order orderWithTime = new Order(newOrder.id(), newOrder.products(), newOrder.bestellstatus(), Instant.now());
        orders.put(orderWithTime.id(), orderWithTime);
        return orderWithTime;
    }

    @Override
    public void removeOrder(String id) {
        orders.remove(id);
    }

    @Override
    public void updateOrder(Order updatedOrder) {

    }
}
