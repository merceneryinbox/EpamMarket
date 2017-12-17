package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good {
    private Integer id;
    private String name;
    private Double price;
    private Integer amount;
    private String description;

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    public static Good testGoodForName(String name) {
        return new Good(
                0,
                name,
                (name.hashCode() % 10000000) / 100.0,
                name.hashCode() % 100,
                name + " very full descripton"
        );
    }
}
