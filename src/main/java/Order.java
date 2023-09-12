import java.util.List;

public record Order(
        String id,
        List<Product> products,
        Bestellstatus bestellstatus




) {

    public Order(String id, List<Product> products) {
        this(id, products, Bestellstatus.PROCESSING);
    }
}


