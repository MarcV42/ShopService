import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Erstellen Sie eine Instanz der ShopService-Klasse
        ShopService shopService = new ShopService();

        // Fügen Sie einige Produkte zur ProductRepo hinzu
        ProductRepo productRepo = shopService.getProductRepo();
        productRepo.addProduct(new Product("1", "Apfel"));
        productRepo.addProduct(new Product("2", "Banane"));
        productRepo.addProduct(new Product("3", "Orange"));

        // Fügen Sie eine Bestellung mit bestimmten Produkt-IDs hinzu
        List<String> productIds = new ArrayList<>();
        productIds.add("1");
        productIds.add("2");

        Order newOrder = shopService.addOrder(productIds);

        // Überprüfen, ob die Bestellung erfolgreich hinzugefügt wurde
        if (newOrder != null) {
            System.out.println("Bestellung hinzugefügt: " + newOrder.id());
        }

        // Suchen Sie nach Bestellungen mit einem bestimmten Bestellstatus
        List<Order> ordersByStatus = shopService.getOrdersByStatus(Bestellstatus.PROCESSING);
        System.out.println("Bestellungen mit Bestellstatus PROCESSING:");
        for (Order order : ordersByStatus) {
            System.out.println("Bestellung: " + order.id() + ", Status: " + order.bestellstatus());
        }
    }
}
