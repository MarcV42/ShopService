import java.util.List;

public record Order(
        String id,
        List<Product> products,
        Bestellstatus bestellstatus


) {

}


