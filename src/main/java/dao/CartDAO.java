package dao;

import entities.Reserve;

import java.util.List;
import java.util.Optional;

public interface CartDAO {
    Optional<List<Reserve>> getReserveListByLogin(String login);
    Optional<Reserve> gerReserve(String login, Integer goodId);
    void setAmountByLoginAndGoodId(String login, Integer goodId, Integer amount);
}
