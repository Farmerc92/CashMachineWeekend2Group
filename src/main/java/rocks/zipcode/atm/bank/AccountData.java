package rocks.zipcode.atm.bank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final int pin;
    private final String name;
    private final String email;
    private final int balance;

    @JsonCreator
    AccountData(@JsonProperty("id")int newId, @JsonProperty("pin")int newPin, @JsonProperty("name")String newName, @JsonProperty("email")String newEmail, @JsonProperty("balance")int newBalance) {
        this.id = newId;
        this.pin = newPin;
        this.name = newName;
        this.email = newEmail;
        this.balance = newBalance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    public int getPin() {
        return pin;
    }

    public static int getRandomIdBasicAccount(){
        Random rand = new Random();
        int sum = rand.nextInt(3999) + 1000;
        return sum;
    }

    public static int getRandomIdPremiumAccount(){
        Random rand = new Random();
        int sum = rand.nextInt(4999) + 5000;
        return sum;
    }

    @Override
    public String toString() {
        return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Balance: " + balance;
    }
}
