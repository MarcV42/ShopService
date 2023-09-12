import lombok.Getter;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public ShopService(ProductRepo productRepo, OrderMapRepo orderMapRepo) {
    }

    public ShopService() {

    }

    public Order addOrder(List<String> productIds) throws IllegalArgumentException {
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
        return orderRepo.getOrders()
                .stream()
                .filter(order -> order.bestellstatus().equals(status))
                .collect(Collectors.toList());
    }

    public void updateOrder(String orderId, Bestellstatus newStatus) {
        Order orderToUpdate = orderRepo.getOrderById(orderId);
        if (orderToUpdate != null) {
            Order updatedOrder = orderToUpdate.withStatus(newStatus);
            orderRepo.updateOrder(updatedOrder);
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
