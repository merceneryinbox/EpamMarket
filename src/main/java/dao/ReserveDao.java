package dao;

import entities.Reserve;

import java.util.List;
import java.util.Optional;

public interface ReserveDao {
    Optional<List<Reserve>> getReserveListByLogin(String login);
    Optional<List<Reserve>> getReserveListByGoodId(Integer goodId);
    Optional<List<Reserve>> getReserveByLoginAndGoodId(String login, Integer GoodId);

    void addReserve(Reserve reserve);
}
