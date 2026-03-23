package org.example;

public class itemNotFoundException extends RuntimeException {
    public itemNotFoundException(String itemName) {
        super("Item not found: " + itemName);
    }
}
