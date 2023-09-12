import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public ShopService(ProductRepo productRepo, OrderMapRepo orderMapRepo) {
    }

    public ShopService() {

    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isPresent()) {
                products.add(productToOrder.get());
            } else {
                throw new IllegalArgumentException("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
        }


        Order newOrder = new Order(UUID.randomUUID().toString(), products);

        return orderRepo.addOrder(newOrder);
    }
    public List<Order> getOrdersByStatus(Bestellstatus status) {
        // Verwenden Sie einen Stream, um alle Bestellungen zu durchlaufen und diejenigen zu filtern,
        // die den gewünschten Bestellstatus haben.
        return orderRepo.getOrders()
                .stream()
                .filter(order -> order.bestellstatus().equals(status))
                .collect(Collectors.toList());
    }

    public void updateOrder(String orderId, Bestellstatus newStatus) {
        Order orderToUpdate = orderRepo.getOrderById(orderId);
        if (orderToUpdate != null) {
            Order updatedOrder = orderToUpdate.withStatus(newStatus);
            orderRepo.updateOrder(updatedOrder); // Vorausgesetzt, es gibt eine Methode zum Aktualisieren in orderRepo
        } else {
            throw new IllegalArgumentException("Bestellung mit der orderId: " + orderId + " wurde nicht gefunden!");
        }
    }


    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void setOrderRepo(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
}
