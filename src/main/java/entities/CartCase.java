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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (goodId != null ? goodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CartCase)) {
            return false;
        }
        CartCase other = (CartCase) object;
        if ((this.goodId == null && other.goodId != null) || (this.goodId != null && !this.goodId.equals(other.goodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CartCase[ goodId = " + goodId + " goodName " + goodName + " price " + price + " amount " + amount + " ] ";
    }
}
