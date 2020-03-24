package hristovski.nikola.product.exception;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(String categoryName) {
        super("Category with name" + categoryName + " not found!");
    }

    public CategoryNotFoundException(String categoryName, Throwable throwable) {
        super("Category with name" + categoryName + " not found!", throwable);
    }
}
