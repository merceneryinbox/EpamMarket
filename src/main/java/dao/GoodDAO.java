package dao;

import entities.Good;
import java.util.Optional;

public interface GoodDAO {
    Optional<Good> getGoodByName(String name);
    void addGood(Good good);
    void deleteGoodByName(String name);
    void updateGood(Good good);
}
