package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final int pin;
    private final String name;
    private final String email;
    private final int balance;

    AccountData(int newId, int newPin, String newName, String newEmail, int newBalance) {
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

    @Override
    public String toString() {
        return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Balance: " + balance;
    }
}
