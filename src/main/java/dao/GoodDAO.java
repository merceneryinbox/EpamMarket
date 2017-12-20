package dao;

import entities.Good;

import java.util.List;
import java.util.Optional;

public interface GoodDAO {
    Optional<Good> getGoodByName(String name);
    List<Good> getAllGoods();
    void addGood(Good good);
    void deleteGoodByName(String name);
    void updateGood(Good good);
}
