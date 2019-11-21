package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String category;
    private int quantity;
    boolean isAvailable;
    private int price;

    @Override
    public String toString() {
        return  "id " + id +
                ", " + name + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", isAvailable=" + isAvailable +
                ", price=" + price + "\n";
    }
}
