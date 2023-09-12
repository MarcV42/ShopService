import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductRepoTest {

    @Test
    void getProducts() {
        ProductRepo repo = new ProductRepo();

        assertEquals(repo.getProducts().size(), 1);
        assertEquals(repo.getProducts().get(0), new Product("1", "Apfel"));
    }

    @Test
    void getProductById_existingProduct() {
        ProductRepo repo = new ProductRepo();

        Optional<Product> actual = repo.getProductById("1");

        assertTrue(actual.isPresent());
        assertEquals(actual.get(), new Product("1", "Apfel"));
    }

    @Test
    void getProductById_nonExistingProduct() {
        ProductRepo repo = new ProductRepo();

        Optional<Product> actual = repo.getProductById("999");

        assertFalse(actual.isPresent());
    }

    @Test
    void addProduct() {
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("2", "Banane");

        Product actual = repo.addProduct(newProduct);

        assertEquals(actual, newProduct);
        Optional<Product> retrievedProduct = repo.getProductById("2");
        assertTrue(retrievedProduct.isPresent());
        assertEquals(retrievedProduct.get(), newProduct);
    }

    @Test
    void removeProduct() {
        ProductRepo repo = new ProductRepo();

        repo.removeProduct("1");

        Optional<Product> removedProduct = repo.getProductById("1");
        assertFalse(removedProduct.isPresent());
    }
}
