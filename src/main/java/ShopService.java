import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isPresent()) {
                products.add(productToOrder.get());
            } else {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
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


}
