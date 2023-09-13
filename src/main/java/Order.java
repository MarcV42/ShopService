import java.time.Instant;
import java.util.List;

public record Order(
        String id,
        List<Product> products,
        Bestellstatus bestellstatus

) {
    public Order(String id, List<Product> products) {
        this(id, products, Bestellstatus.PROCESSING);
    }

    public Order {
    }

    public Order(String id, List<Product> products, Bestellstatus bestellstatus, Instant now) {
        this(id,
                products,
                Bestellstatus.PROCESSING);
    }


    /*In diesem aktualisierten Record Order wurde eine zusätzliche Methode withStatus
    hinzugefügt, die ein neues Order-Objekt mit dem aktualisierten Bestellstatus zurückgibt.
    Damit können Sie den Bestellstatus einer Bestellung ändern, indem Sie eine neue Instanz erstellen,
    anstatt die ursprüngliche Instanz zu ändern. Dadurch bleibt der Record unveränderlich, was eine
    bewährte Praxis in Java ist.

     */
    public Order withStatus(Bestellstatus newStatus) {
        return new Order(this.id, this.products, newStatus);
    }


    public String orderTime() {
        return null;
    }
}
