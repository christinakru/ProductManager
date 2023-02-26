package org.manager;

public class Repository {
    private Product[] products;

    public Repository() {
        products = new Product[0];
    }

    public void addProduct(Product product) {
        int size = products.length;
        Product[] newProducts = new Product[size + 1];
        System.arraycopy(products, 0, newProducts, 0, size);
        newProducts[size] = product;
        products = newProducts;
    }

    public Product[] findAll() {
        return products;
    }

    public void removeProduct(int id) {
        int index = -1;
        for (int i = 0; i < products.length; i++) {
            if (products[i].getID() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Product[] newArr = new Product[products.length - 1];
            System.arraycopy(products, 0, newArr, 0, index);
            System.arraycopy(products, index + 1, newArr, index, products.length - index - 1);
            products = newArr;
        }
    }
}
