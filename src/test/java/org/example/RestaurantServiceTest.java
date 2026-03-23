package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    RestaurantService service;

    // Refactored: shared setup to avoid repeated code
    @BeforeEach
    void setUp() {
        service = new RestaurantService();
        Restaurant restaurant = service.addRestaurant("Amelie's cafe", "Chennai",
                LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    // Part 2: Test - existing restaurant returns expected object
    @Test
    void searching_for_existing_restaurant_should_return_expected_restaurant_object() {
        Restaurant result = service.findRestaurantByName("Amelie's cafe");
        assertNotNull(result);
        assertEquals("Amelie's cafe", result.getName());
    }

    // Part 2: Test - non-existing restaurant throws exception
    @Test
    void searching_for_non_existing_restaurant_should_throw_exception() {
        assertThrows(restaurantNotFoundException.class,
                () -> service.findRestaurantByName("Pantry d'or"));
    }

    // ---- Admin/Restaurant Owner Tests (already implemented) ----

    @Test
    void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
    }

    @Test
    void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class,
                () -> service.removeRestaurant("Pantry d'or"));
    }

    @Test
    void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales", "Chennai",
                LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
    }

    // Part 3 (TDD): Order total tests

    @Test
    void order_value_should_return_correct_total_for_given_items() {
        int total = service.calculateOrderValue("Amelie's cafe",
                List.of("Sweet corn soup", "Vegetable lasagne"));
        assertEquals(388, total);
    }

    @Test
    void order_value_for_single_item_should_return_correct_price() {
        int total = service.calculateOrderValue("Amelie's cafe",
                List.of("Sweet corn soup"));
        assertEquals(119, total);
    }

    @Test
    void order_value_for_empty_list_should_return_zero() {
        int total = service.calculateOrderValue("Amelie's cafe", List.of());
        assertEquals(0, total);
    }
}
