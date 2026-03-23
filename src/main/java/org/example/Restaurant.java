package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    // Part 2: isRestaurantOpen()
    public boolean isRestaurantOpen() {
        LocalTime currentTime = getCurrentTime();
        return currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);
    }

    // Extracted to allow mocking in tests
    protected LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    // Part 2: getMenu()
    public List<Item> getMenu() {
        return menu;
    }

    public Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) {
        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);
        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant: " + name + "\n"
                + "Location: " + location + "\n"
                + "Opening time: " + openingTime + "\n"
                + "Closing time: " + closingTime + "\n"
                + "Menu: " + "\n" + getMenu());
    }

    public String getName() {
        return name;
    }
}
