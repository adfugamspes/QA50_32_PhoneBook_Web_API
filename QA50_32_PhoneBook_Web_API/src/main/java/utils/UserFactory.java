package utils;

import dto.User;
import net.datafaker.Faker;

public class UserFactory {

//    public static void main(String[] args) {
//        Faker faker = new Faker();
//        String name = faker.name().fullName();
//        String firstName = faker.name().firstName();
//        String lastName = faker.name().lastName();
//        String email = faker.internet().emailAddress();
//
//        System.out.println(name);
//        System.out.println(firstName);
//        System.out.println(lastName);
//        System.out.println(email);
//    }
    static Faker faker = new Faker();
    public static User positiveUser(){
        return new User(faker.internet().emailAddress(), "Password!123");
    }
}
