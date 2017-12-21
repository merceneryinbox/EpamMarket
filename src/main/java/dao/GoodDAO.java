package dao;

import entities.Good;

import java.util.List;

import java.util.Optional;

public interface GoodDAO {

    void addGood(Good good);

    List<Good> getAllGoods();

    Optional<Good> getGoodById(Integer id);

    Optional<Good> getGoodByName(String name);

    void deleteGoodByName(String name);

    void updateGood(Good good);
}
