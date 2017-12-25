package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good implements Comparable {
    private Integer id;
    private String name;
    private Double price;
    private Integer amount;
    private String description;

    public static Good testGoodForName(String name) {
        return new Good(0, name, (name.hashCode() % 10000000) / 100.0, name
                .hashCode() % 100,
                name + " very full descripton");
    }

    @Override
    public int compareTo(Object o) {
        Good good = (Good) o;
        return this.getName().compareTo(good.getName());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Good)) {
            return false;
        }
        Good other = (Good) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Good[ id = " + id + " name " + name + " price " + price + " amount " + amount + " ] ";
    }
}
