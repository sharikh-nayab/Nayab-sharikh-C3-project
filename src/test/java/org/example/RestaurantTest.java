package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    Restaurant restaurant;

    // Refactored: shared setup to avoid repeated code
    @BeforeEach
    void setUp() {
        restaurant = Mockito.spy(new Restaurant("Amelie's cafe", "Chennai",
                LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00")));
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    // Part 2: Returns true when current time is within open hours
    @Test
    void Is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        Mockito.doReturn(LocalTime.parse("13:00:00")).when(restaurant).getCurrentTime();
        assertTrue(restaurant.isRestaurantOpen());
    }

    // Part 2: Returns false when current time is outside open hours
    @Test
    void Is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        Mockito.doReturn(LocalTime.parse("23:00:00")).when(restaurant).getCurrentTime();
        assertFalse(restaurant.isRestaurantOpen());
    }

    // ---- Admin/Restaurant Owner Tests (already implemented) ----

    @Test
    void adding_item_to_menu_should_increase_menu_size_by_1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
}
