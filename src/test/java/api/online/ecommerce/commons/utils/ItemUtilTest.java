package api.online.ecommerce.commons.utils;

import api.online.ecommerce.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Comprehensive unit tests for {@link ItemUtil} covering positive and negative scenarios,
 * null handling, and VAT calculations. These tests verify that {@link ItemUtil}
 * methods behave correctly when provided with valid data as well as various edge cases such as
 * null orders/carts, null item lists, lists containing null elements, and negative subtotal or VAT values.
 */
class ItemUtilTest {

    @Nested
    @DisplayName("calculateOrderTotal")
    class CalculateOrdersTotalTests {
        @Test
        @DisplayName("should calculate totals and grand total for valid order with items and positive VAT")
        void calculateOrderTotal_positive() {
            // Arrange
            OrderItem item1 = mock(OrderItem.class);
            OrderItem item2 = mock(OrderItem.class);
            when(item1.getSubTotal()).thenReturn(20.0);
            when(item2.getSubTotal()).thenReturn(10.0);
            doNothing().when(item1).calculateSubtotal();
            doNothing().when(item2).calculateSubtotal();

            Orders order = mock(Orders.class);
            when(order.getOrderItems()).thenReturn(Arrays.asList(item1, item2));
            when(order.getVat()).thenReturn(10.0);
            // Simulate that after setTotalPrice is invoked the getter returns the correct total
            when(order.getTotalPrice()).thenReturn(30.0);

            // Act
            Orders result = ItemUtil.calculateOrderGrandTotal(order);

            // Assert
            assertSame(order, result);
            verify(item1).calculateSubtotal();
            verify(item2).calculateSubtotal();
            verify(order).setTotalPrice(30.0);
            // Final total should include VAT (30 + 10% = 33)
            verify(order).setFinalTotal(33.0);
        }

        @Test
        @DisplayName("should throw IllegalArgumentException when order is null")
        void calculateOrderTotal_orderNull_throws() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> ItemUtil.calculateOrderGrandTotal(null));
        }

        @Test
        @DisplayName("should handle null item list by setting totals to zero")
        void calculateOrderTotal_nullItemList() {
            // Arrange
            Orders order = mock(Orders.class);
            when(order.getOrderItems()).thenReturn(null);
            when(order.getVat()).thenReturn(15.0);
            // Simulate getTotalPrice returning 0 after setTotalPrice(0) is invoked
            when(order.getTotalPrice()).thenReturn(0.0);

            // Act
            ItemUtil.calculateOrderGrandTotal(order);

            // Assert
            verify(order).setTotalPrice(0.0);
            verify(order).setFinalTotal(0.0);
        }

        @Test
        @DisplayName("should skip null items in the list and calculate totals")
        void calculateOrderTotal_containsNullItem() {
            // Arrange
            OrderItem validItem = mock(OrderItem.class);
            when(validItem.getSubTotal()).thenReturn(25.0);
            doNothing().when(validItem).calculateSubtotal();

            Orders order = mock(Orders.class);
            // List containing a valid item and a null element
            List<OrderItem> items = Arrays.asList(validItem, null);
            when(order.getOrderItems()).thenReturn(items);
            when(order.getVat()).thenReturn(20.0);
            // After setTotalPrice(25.0) the getter should return 25.0
            when(order.getTotalPrice()).thenReturn(25.0);

            // Act
            ItemUtil.calculateOrderGrandTotal(order);

            // Assert
            verify(validItem).calculateSubtotal();
            verify(order).setTotalPrice(25.0);
            // 25 + 20% of 25 = 30.0
            verify(order).setFinalTotal(30.0);
        }

        @Test
        @DisplayName("should correctly handle items with negative subtotals")
        void calculateOrderTotal_negativeSubtotal() {
            // Arrange
            OrderItem item1 = mock(OrderItem.class);
            OrderItem item2 = mock(OrderItem.class);
            when(item1.getSubTotal()).thenReturn(40.0);
            when(item2.getSubTotal()).thenReturn(-10.0);
            doNothing().when(item1).calculateSubtotal();
            doNothing().when(item2).calculateSubtotal();

            Orders order = mock(Orders.class);
            when(order.getOrderItems()).thenReturn(Arrays.asList(item1, item2));
            when(order.getVat()).thenReturn(5.0);
            // Total price should be 30.0 after setTotalPrice
            when(order.getTotalPrice()).thenReturn(30.0);

            // Act
            ItemUtil.calculateOrderGrandTotal(order);

            // Assert
            verify(order).setTotalPrice(30.0);
            // 30 + 5% of 30 = 31.5
            verify(order).setFinalTotal(31.5);
        }
    }

    @Nested
    @DisplayName("calculateCartTotal")
    class CalculateCartTotalTests {
        @Test
        @DisplayName("should calculate total price for valid cart items")
        void calculateCartTotal_positive() {
            // Arrange
            CartItem cartItem1 = mock(CartItem.class);
            CartItem cartItem2 = mock(CartItem.class);
            when(cartItem1.getSubTotal()).thenReturn(15.0);
            when(cartItem2.getSubTotal()).thenReturn(5.0);
            doNothing().when(cartItem1).calculateSubtotal();
            doNothing().when(cartItem2).calculateSubtotal();

            Cart cart = mock(Cart.class);
            when(cart.getCartItems()).thenReturn(Arrays.asList(cartItem1, cartItem2));

            // Act
            Cart result = ItemUtil.calculateCartTotal(cart);

            // Assert
            assertSame(cart, result);
            verify(cartItem1).calculateSubtotal();
            verify(cartItem2).calculateSubtotal();
            verify(cart).setTotalPrice(20.0);
        }

        @Test
        @DisplayName("should throw IllegalArgumentException when cart is null")
        void calculateCartTotal_cartNull_throws() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> ItemUtil.calculateCartTotal(null));
        }

        @Test
        @DisplayName("should handle null cart items list by setting total to zero")
        void calculateCartTotal_nullItemList() {
            // Arrange
            Cart cart = mock(Cart.class);
            when(cart.getCartItems()).thenReturn(null);

            // Act
            ItemUtil.calculateCartTotal(cart);

            // Assert
            verify(cart).setTotalPrice(0.0);
        }

        @Test
        @DisplayName("should skip null items and calculate total")
        void calculateCartTotal_containsNullItem() {
            // Arrange
            CartItem validItem = mock(CartItem.class);
            when(validItem.getSubTotal()).thenReturn(7.5);
            doNothing().when(validItem).calculateSubtotal();

            Cart cart = mock(Cart.class);
            // List with a valid item and a null entry
            List<CartItem> items = Arrays.asList(validItem, null);
            when(cart.getCartItems()).thenReturn(items);

            // Act
            ItemUtil.calculateCartTotal(cart);

            // Assert
            verify(validItem).calculateSubtotal();
            verify(cart).setTotalPrice(7.5);
        }
    }

    @Nested
    @DisplayName("calculateVat")
    class CalculateVatTests {
        @Test
        @DisplayName("should return subtotal for zero VAT")
        void calculateVat_zeroVat() {
            assertEquals(100.0, ItemUtil.calculateVat(0.0, 100.0), 1e-6);
        }

        @Test
        @DisplayName("should calculate VAT correctly for negative VAT rate")
        void calculateVat_negativeVat() {
            // 100 + (-10% * 100) = 90
            assertEquals(90.0, ItemUtil.calculateVat(-10.0, 100.0), 1e-6);
        }

        @Test
        @DisplayName("should calculate VAT correctly for negative subtotal")
        void calculateVat_negativeSubtotal() {
            // -100 + 10% * -100 = -110
            assertEquals(-110.0, ItemUtil.calculateVat(10.0, -100.0), 1e-6);
        }

        @Test
        @DisplayName("should handle zero subtotal without division by zero")
        void calculateVat_zeroSubtotal() {
            assertEquals(0.0, ItemUtil.calculateVat(15.0, 0.0), 1e-6);
        }
    }
}