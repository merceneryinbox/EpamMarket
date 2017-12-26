package entities;

public class EntityGenerator {

    public static Good testGoodForName(String name) {
        return new Good(
                0,
                name,
                (name.hashCode() % 10000000) / 100.0,
                name.hashCode() % 100,
                name + " very full descripton");
    }

    public static User testUserForName(String name) {
        return new User(
                0,
                name,
                name.hashCode() + "",
                name + "@email.com",
                "+7" + (+name.hashCode() + "0000000000").substring(0, 10), "ACTIVE");
    }

    public static Good clone(Good good) {
        return new Good(
                good.getId(),
                good.getName(),
                good.getPrice(),
                good.getAmount(),
                good.getDescription()
        );
    }

    public static User clone(User user) {
        return new User(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.getStatus()
        );
    }


}
