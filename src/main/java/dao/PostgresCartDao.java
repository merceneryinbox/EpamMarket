package dao;

import entities.Reserve;

import java.util.List;
import java.util.Optional;

public class PostgresCartDao implements CartDAO {
    @Override
    public Optional<List<Reserve>> getReserveListByLogin(String login) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Reserve> gerReserve(String login, Integer goodId) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void setAmountByLoginAndGoodId(String login, Integer goodId, Integer amount) {
        throw new UnsupportedOperationException();
    }
}
