import org.junit.jupiter.api.Test;
import org.manager.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductManagerTest {
    @Test
    void testAddProduct() {
        Repository repo = new Repository();
        ProductManager manager = new ProductManager(repo);
        Book book = new Book(1, "Гарри Поттер", 100, "J. Roalling");
        Smartphone smartphone = new Smartphone(2, "iPhone 14", 1000, "Apple");

        manager.add(book);
        manager.add(smartphone);

        assertEquals(2, repo.findAll().length);
        assertEquals(book, repo.findAll()[0]);
        assertEquals(smartphone, repo.findAll()[1]);
    }

    @Test
    void testSearchByProductNameSeveralItems() {
        Repository repo = new Repository();
        ProductManager manager = new ProductManager(repo);
        Book book1 = new Book(1, "Гарри Поттер", 100, "J. Roalling");
        Smartphone smartphone = new Smartphone(2, "iPhone 14", 1000, "Apple");
        Book book2 = new Book(3, "Гарри Поттер 2", 100, "J. Roalling");

        repo.addProduct(book1);
        repo.addProduct(book2);
        repo.addProduct(smartphone);

        Product[] act = manager.searchBy("Гарри");
        Product[] exp = new Product[]{book1, book2};

        assertArrayEquals(exp, act);
    }

    @Test
    void testSearchByProductNameOneItem() {
        Repository repo = new Repository();
        ProductManager manager = new ProductManager(repo);
        Book book1 = new Book(1, "Гарри Поттер", 100, "J. Roalling");
        Smartphone smartphone = new Smartphone(2, "iPhone 14", 1000, "Apple");
        Book book2 = new Book(3, "Гарри Поттер 2", 100, "J. Roalling");

        repo.addProduct(book1);
        repo.addProduct(book2);
        repo.addProduct(smartphone);

        Product[] act = manager.searchBy("iPhone");
        Product[] exp = new Product[]{smartphone};

        assertArrayEquals(exp, act);
    }

    @Test
    void testEmptySearchByProduct() {
        Repository repo = new Repository();
        ProductManager manager = new ProductManager(repo);
        Book book1 = new Book(1, "Гарри Поттер", 100, "J. Roalling");
        Smartphone smartphone = new Smartphone(2, "iPhone 14", 1000, "Apple");
        Book book2 = new Book(3, "Гарри Поттер 2", 100, "J. Roalling");

        repo.addProduct(book1);
        repo.addProduct(book2);
        repo.addProduct(smartphone);

        Product[] act = manager.searchBy("iPad");
        Product[] exp = new Product[]{};

        assertArrayEquals(exp, act);
    }

    @Test
    void testMatches() {
        Repository repo = new Repository();
        ProductManager manager = new ProductManager(repo);
        Book book1 = new Book(1, "Гарри Поттер", 100, "J. Roalling");

        assertTrue(manager.matches(book1, "Гарри"));
        assertFalse(manager.matches(book1, "Other"));
    }
}
