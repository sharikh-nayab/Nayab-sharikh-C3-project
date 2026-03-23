package org.example;

public class restaurantNotFoundException extends RuntimeException {
    public restaurantNotFoundException(String restaurantName) {
        super("Restaurant not found: " + restaurantName);
    }
}
