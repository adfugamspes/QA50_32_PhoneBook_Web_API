package utils;

import dto.User;
import net.datafaker.Faker;

import java.util.Random;

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

    public static User positiveUser() {
        return new User(faker.internet().emailAddress(), "Password!123");
    }

    public static User negativeBlankEmail() {
        return new User("", "Password!123");
    }

    public static User negativeBlankPassword() {
        return new User(faker.internet().emailAddress(), "");
    }

    public static User negativeBlankEmailAndPassword() {
        return new User("", "");
    }

    public static User negativeOnlySpacesEmail() {
        return new User("   ", "Password!123");
    }

    public static User negative2AtSignsInEmail() {
        return new User(faker.name().firstName() + "@@gmail.com", "Password!123");
    }

    public static User negative2AtSignsLettersBetweenEmail() {
        return new User(faker.name().firstName() + "@test@gmail.com", "Password!123");
    }

    public static User negativeNoLettersBeforeAtSignEmail() {
        return new User("@gmail.com", "Password!123");
    }

    public static User negativeNoLettersAfterAtSignEmail() {
        return new User(faker.name().firstName() + "@", "Password!123");
    }

    public static User negativeOnlyCyrillicLettersEmail() {
        int i = new Random().nextInt(2000);
        return new User("такоймейл" + i + "@мейл.ком", "Password!123");
    }

    public static User negativeLatinAndCyrillicLettersEmail() {
        int i = new Random().nextInt(2000);
        return new User("такоймейл" + i + "@gmail.com", "Password!123");
    }

    public static User negativeOnlySpacesPassword() {
        return new User(faker.internet().emailAddress(), "   ");
    }

    public static User negativeNoSpecialCharsPassword() {
        return new User(faker.internet().emailAddress(), "Password123");
    }

    public static User negativeWrongSpecialCharPassword() {
        return new User(faker.internet().emailAddress(), "Password)123");
    }

    public static User negativeCyrillicLettersPassword() {
        return new User(faker.internet().emailAddress(), "Пароль123!");
    }

    public static User negativeCyrillicAndEnglishLettersPassword() {
        return new User(faker.internet().emailAddress(), "Password123!ю");
    }

    public static User negativeLatinWithSerifPassword() {
        return new User(faker.internet().emailAddress(), "Password123!ł");
    }

    public static User negativeNoUppercasePassword() {
        return new User(faker.internet().emailAddress(), "password!123");
    }

    public static User negativeNoLowercasePassword() {
        return new User(faker.internet().emailAddress(), "PASSWORD!123");
    }

    public static User negativeNoNumbersPassword() {
        return new User(faker.internet().emailAddress(), "Password!");
    }

    public static User negative7SymbolsPassword() {
        return new User(faker.internet().emailAddress(), "!123Pas");
    }

    public static User negative16SymbolsPassword() {
        return new User(faker.internet().emailAddress(), "!123PasswordQwertyta");
    }




}
