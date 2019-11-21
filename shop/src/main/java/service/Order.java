package service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private int customer_id;
    private int product_id;
    private String product_name;
    private int quantity;
    private int order_price;
    private String delivery;
    private boolean status;
    private String datetime;

    @Override
    public String toString() {
        return "id " + id +
                ", customer_id=" + customer_id +
                ", product_id=" + product_id +
                ", " + product_name +
                ", quantity=" + quantity +
                ", order_price=" + order_price + " руб.\n" +
                ", delivery= " + delivery +
                ", isDone= " + status +
                ", " + datetime + '\n';
    }
}
