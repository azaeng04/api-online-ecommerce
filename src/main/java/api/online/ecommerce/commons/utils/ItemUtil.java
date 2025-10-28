package api.online.ecommerce.commons.utils;

import api.online.ecommerce.models.Cart;
import api.online.ecommerce.models.Item;
import api.online.ecommerce.models.Orders;

import java.util.List;

/**
 * Updated version of {@link ItemUtil} that gracefully handles null inputs,
 * lists containing null elements, and negative values. It also throws
 * informative exceptions when provided with null orders or carts. The VAT
 * calculation has been retained as a simple percentage addition and does not
 * divide by zero.
 */
public class ItemUtil {
    /**
     * Calculates the total and final total for an {@link Orders}. This method will
     * skip over null items in the order and treat a null item list as empty. If
     * the supplied order is null, an {@link IllegalArgumentException} is thrown.
     *
     * @param order the order for which to calculate totals
     * @return the same {@code order} instance with updated totals
     * @throws IllegalArgumentException if {@code order} is {@code null}
     */
    public static Orders calculateOrderGrandTotal(Orders order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        double grandTotal = 0;
        List<? extends Item> items = order.getOrderItems();
        if (items != null) {
            for (Item item : items) {
                if (item != null) {
                    item.calculateSubtotal();
                    grandTotal += item.getSubTotal();
                }
            }
        }
        order.setTotalPrice(grandTotal);
        // Reuse the grandTotal from the order to ensure consistency for VAT calculation
        order.setFinalTotal(calculateVat(order.getVat(), order.getTotalPrice()));
        return order;
    }

    /**
     * Calculates the total for a {@link Cart}. This method skips over null items
     * and treats a null item list as empty. A null cart will result in an
     * {@link IllegalArgumentException} being thrown.
     *
     * @param cart the cart for which to calculate the total
     * @return the same {@code cart} instance with the total updated
     * @throws IllegalArgumentException if {@code cart} is {@code null}
     */
    public static Cart calculateCartTotal(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }
        double total = 0;
        List<? extends Item> items = cart.getCartItems();
        if (items != null) {
            for (Item item : items) {
                if (item != null) {
                    item.calculateSubtotal();
                    total += item.getSubTotal();
                }
            }
        }
        cart.setTotalPrice(total);
        return cart;
    }

    /**
     * Computes the final total including VAT. Negative VAT or subtotal values are
     * supported. Since the formula simply multiplies the subtotal by a
     * percentage, there is no division by zero.
     *
     * @param vat      the VAT rate as a percentage (e.g., 15.0 for 15%)
     * @param subtotal the subtotal before VAT
     * @return the subtotal including VAT
     */
    public static double calculateVat(double vat, double subtotal) {
        return subtotal + (vat / 100.0) * subtotal;
    }
}