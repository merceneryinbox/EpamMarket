package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartCase implements Comparable {
    private String goodName;
    private Integer goodId;
    private Integer amount;
    private Double  price;

    @Override
    public int compareTo(Object o) {
        CartCase cartCase = (CartCase) o;
        return this.getGoodName().compareTo(cartCase.getGoodName());
    }
}
