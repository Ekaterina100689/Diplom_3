package util;

import com.github.javafaker.Faker;
import model.User;

public final class UserGenerator {
    public static User GenerateUser() {
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(6, 9);
        return new User(name, email, password);
    }
}
