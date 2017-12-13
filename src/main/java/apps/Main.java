package apps;

import entities.User;
import lombok.val;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, EPAM!");
        val user = new User();
        user.setId(3);
        System.out.println(user.getId());
    }
}
